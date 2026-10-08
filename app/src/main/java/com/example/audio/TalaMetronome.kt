package com.example.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin

data class TalaAction(
    val beatIndex: Int,
    val label: String, // e.g. "Clap (Samam)", "Pinky", "Ring", "Middle", "Wave"
    val isAccented: Boolean
)

data class TalaDefinition(
    val name: String,
    val beatsCount: Int,
    val description: String,
    val actions: List<TalaAction>
)

class TalaMetronome {

    companion object {
        val ADI_TALA = TalaDefinition(
            name = "Adi Tala",
            beatsCount = 8,
            description = "Chatusra Jati Triputa Tala (I4 + O + O = 8 aksharas)",
            actions = listOf(
                TalaAction(0, "Clap (Samam)", true),
                TalaAction(1, "Pinky", false),
                TalaAction(2, "Ring", false),
                TalaAction(3, "Middle", false),
                TalaAction(4, "Clap", true),
                TalaAction(5, "Wave (Veechu)", false),
                TalaAction(6, "Clap", true),
                TalaAction(7, "Wave (Veechu)", false)
            )
        )

        val RUPAKA_TALA = TalaDefinition(
            name = "Rupaka Tala",
            beatsCount = 6,
            description = "Chatusra Jati Rupaka Tala (O + I4 = 2 + 4 = 6 aksharas)",
            actions = listOf(
                TalaAction(0, "Clap (Dhrutam)", true),
                TalaAction(1, "Wave", false),
                TalaAction(2, "Clap (Laghu)", true),
                TalaAction(3, "Pinky", false),
                TalaAction(4, "Ring", false),
                TalaAction(5, "Middle", false)
            )
        )

        val MISRA_CHAPU = TalaDefinition(
            name = "Misra Chapu",
            beatsCount = 7,
            description = "3 + 2 + 2 = 7 aksharas syncopated rhythm",
            actions = listOf(
                TalaAction(0, "Clap 1", true),
                TalaAction(1, "Pause", false),
                TalaAction(2, "Clap 2", false),
                TalaAction(3, "Clap 3", true),
                TalaAction(4, "Wave", false),
                TalaAction(5, "Clap 4", true),
                TalaAction(6, "Wave", false)
            )
        )

        val KHANDA_CHAPU = TalaDefinition(
            name = "Khanda Chapu",
            beatsCount = 5,
            description = "2 + 3 = 5 aksharas folk and devotional rhythm",
            actions = listOf(
                TalaAction(0, "Clap 1", true),
                TalaAction(1, "Clap 2", false),
                TalaAction(2, "Clap 3", true),
                TalaAction(3, "Wave 1", false),
                TalaAction(4, "Wave 2", false)
            )
        )

        val TISRA_EKA = TalaDefinition(
            name = "Tisra Eka",
            beatsCount = 3,
            description = "Tisra Jati Eka Tala (I3 = 3 aksharas)",
            actions = listOf(
                TalaAction(0, "Clap", true),
                TalaAction(1, "Pinky", false),
                TalaAction(2, "Ring", false)
            )
        )

        val ALL_TALAS = listOf(ADI_TALA, RUPAKA_TALA, MISRA_CHAPU, KHANDA_CHAPU, TISRA_EKA)
    }

    private var clickTrack: AudioTrack? = null
    private var metronomeJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _selectedTala = MutableStateFlow(ADI_TALA)
    val selectedTala: StateFlow<TalaDefinition> = _selectedTala.asStateFlow()

    private val _bpm = MutableStateFlow(72)
    val bpm: StateFlow<Int> = _bpm.asStateFlow()

    private val _currentBeat = MutableStateFlow(0)
    val currentBeat: StateFlow<Int> = _currentBeat.asStateFlow()

    private val _kalam = MutableStateFlow(1) // 1 = 1st Kalam (1x), 2 = 2nd Kalam (2x), 3 = 3rd Kalam (4x)
    val kalam: StateFlow<Int> = _kalam.asStateFlow()

    private val _isSoundEnabled = MutableStateFlow(true)
    val isSoundEnabled: StateFlow<Boolean> = _isSoundEnabled.asStateFlow()

    private val sampleRate = 44100
    private var highClickBuffer: ShortArray? = null
    private var lowClickBuffer: ShortArray? = null

    init {
        generateClickBuffers()
    }

    private fun generateClickBuffers() {
        val clickLength = (sampleRate * 0.035).toInt()
        highClickBuffer = ShortArray(clickLength) { i ->
            val t = i.toDouble() / sampleRate
            val decay = exp(-t * 120.0)
            val freq = 1200.0 // crisp wooden bell / mridangam talam accent
            (sin(2 * PI * freq * t) * decay * 28000).toInt().toShort()
        }
        lowClickBuffer = ShortArray(clickLength) { i ->
            val t = i.toDouble() / sampleRate
            val decay = exp(-t * 100.0)
            val freq = 800.0
            (sin(2 * PI * freq * t) * decay * 22000).toInt().toShort()
        }
    }

    fun setTala(tala: TalaDefinition) {
        _selectedTala.value = tala
        _currentBeat.value = 0
    }

    fun setBpm(newBpm: Int) {
        _bpm.value = newBpm.coerceIn(30, 240)
    }

    fun setKalam(newKalam: Int) {
        _kalam.value = newKalam.coerceIn(1, 4)
    }

    fun toggleSound() {
        _isSoundEnabled.value = !_isSoundEnabled.value
    }

    fun togglePlay() {
        if (_isPlaying.value) {
            stop()
        } else {
            start()
        }
    }

    fun start() {
        if (_isPlaying.value) return
        stop()

        try {
            val minBuf = AudioTrack.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT)
            clickTrack = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(minBuf.coerceAtLeast(4096))
                .setTransferMode(AudioTrack.MODE_STREAM)
                .build()

            clickTrack?.play()
            _isPlaying.value = true

            metronomeJob = scope.launch {
                runMetronomeLoop()
            }
        } catch (e: Exception) {
            Log.e("TalaMetronome", "Failed to start metronome audio track", e)
            _isPlaying.value = false
        }
    }

    private suspend fun runMetronomeLoop() {
        var beat = 0
        while (scope.isActive && _isPlaying.value) {
            val tala = _selectedTala.value
            val currentAction = tala.actions.getOrNull(beat % tala.beatsCount) ?: TalaAction(0, "Beat", false)

            _currentBeat.value = beat % tala.beatsCount

            if (_isSoundEnabled.value && clickTrack != null) {
                val buf = if (currentAction.isAccented) highClickBuffer else lowClickBuffer
                buf?.let {
                    clickTrack?.write(it, 0, it.size)
                }
            }

            // Kalam 1: 1x, Kalam 2: 2x (double speed), Kalam 3: 4x (quadruple speed), Kalam 4: 8x (8th speed)
            val kalamMultiplier = when (_kalam.value) {
                2 -> 2
                3 -> 4
                4 -> 8
                else -> 1
            }
            val intervalMs = (60000L / (_bpm.value * kalamMultiplier)).coerceAtLeast(60L)

            delay(intervalMs)
            beat++
        }
    }

    fun stop() {
        metronomeJob?.cancel()
        metronomeJob = null
        try {
            clickTrack?.apply {
                stop()
                release()
            }
        } catch (_: Exception) {}
        clickTrack = null
        _isPlaying.value = false
        _currentBeat.value = 0
    }
}
