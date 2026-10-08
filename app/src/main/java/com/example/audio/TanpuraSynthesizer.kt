package com.example.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.File
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin

class TanpuraSynthesizer {

    private val sampleRate = 44100
    private var audioTrack: AudioTrack? = null
    private var customMediaPlayer: MediaPlayer? = null
    private var synthJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _kattai = MutableStateFlow("1.5 Kattai (C#)")
    val kattai: StateFlow<String> = _kattai.asStateFlow()

    private val _tuning = MutableStateFlow("Pa") // "Pa", "Ma", "Ni"
    val tuning: StateFlow<String> = _tuning.asStateFlow()

    private val _volume = MutableStateFlow(0.85f)
    val volume: StateFlow<Float> = _volume.asStateFlow()

    private val _activeTrackTitle = MutableStateFlow<String?>("Synthesized Drone")
    val activeTrackTitle: StateFlow<String?> = _activeTrackTitle.asStateFlow()

    private val _isCustomTrackActive = MutableStateFlow(false)
    val isCustomTrackActive: StateFlow<Boolean> = _isCustomTrackActive.asStateFlow()

    private var currentCustomAudioPath: String? = null

    companion object {
        val KATTAI_MAP = mapOf(
            "1 Kattai (C)" to 261.63,
            "1.5 Kattai (C#)" to 277.18,
            "2 Kattai (D)" to 293.66,
            "2.5 Kattai (D#)" to 311.13,
            "3 Kattai (E)" to 329.63,
            "4 Kattai (F)" to 349.23,
            "4.5 Kattai (F#)" to 369.99,
            "5 Kattai (G)" to 392.00,
            "5.5 Kattai (G#)" to 415.30,
            "6 Kattai (A)" to 440.00,
            "6.5 Kattai (A#)" to 466.16,
            "7 Kattai (B)" to 493.88
        )
    }

    fun setKattai(newKattai: String) {
        _kattai.value = newKattai
        if (!_isCustomTrackActive.value && _isPlaying.value) {
            startSynth()
        }
    }

    fun setTuning(newTuning: String) {
        _tuning.value = newTuning
        if (!_isCustomTrackActive.value && _isPlaying.value) {
            startSynth()
        }
    }

    fun setVolume(vol: Float) {
        _volume.value = vol.coerceIn(0f, 1f)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                audioTrack?.setVolume(_volume.value)
            }
            customMediaPlayer?.setVolume(_volume.value, _volume.value)
        } catch (_: Exception) {}
    }

    fun playCustomAudio(filePath: String, title: String) {
        stop()
        try {
            val file = File(filePath)
            if (!file.exists()) {
                Log.e("TanpuraSynthesizer", "Custom tanpura file not found: $filePath")
                return
            }

            val player = MediaPlayer().apply {
                setDataSource(filePath)
                isLooping = true
                setVolume(_volume.value, _volume.value)
                prepare()
                start()
            }
            customMediaPlayer = player
            currentCustomAudioPath = filePath
            _activeTrackTitle.value = title
            _isCustomTrackActive.value = true
            _isPlaying.value = true
        } catch (e: Exception) {
            Log.e("TanpuraSynthesizer", "Failed to play custom tanpura audio", e)
            stop()
        }
    }

    fun playSynth() {
        stop()
        _isCustomTrackActive.value = false
        _activeTrackTitle.value = "Synthesized Drone (${_kattai.value})"
        startSynth()
    }

    fun togglePlay() {
        if (_isPlaying.value) {
            stop()
        } else {
            if (_isCustomTrackActive.value && currentCustomAudioPath != null) {
                currentCustomAudioPath?.let { path ->
                    playCustomAudio(path, _activeTrackTitle.value ?: "Custom Tanpura")
                }
            } else {
                playSynth()
            }
        }
    }

    private fun startSynth() {
        stopSynthOnly()
        try {
            val minBufferSize = AudioTrack.getMinBufferSize(
                sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT
            )
            val bufferSize = (minBufferSize * 2).coerceAtLeast(sampleRate / 4)

            audioTrack = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(bufferSize)
                .setTransferMode(AudioTrack.MODE_STREAM)
                .build()

            audioTrack?.play()
            _isPlaying.value = true

            synthJob = scope.launch {
                runTanpuraSynthesis()
            }
        } catch (e: Exception) {
            Log.e("TanpuraSynthesizer", "Failed to start Tanpura AudioTrack", e)
            _isPlaying.value = false
        }
    }

    private suspend fun runTanpuraSynthesis() {
        val cycleSec = 4.0
        val pluckInterval = 1.0
        val chunkSize = 2048
        val shortBuffer = ShortArray(chunkSize)
        var sampleIndex = 0L

        while (scope.isActive && _isPlaying.value && !_isCustomTrackActive.value) {
            val baseSa = KATTAI_MAP[_kattai.value] ?: 277.18
            val firstStringRatio = when (_tuning.value) {
                "Ma" -> 4.0 / 3.0
                "Ni" -> 15.0 / 16.0
                else -> 1.5
            }

            val stringFrequencies = doubleArrayOf(
                baseSa * firstStringRatio,
                baseSa,
                baseSa,
                baseSa * 0.5
            )

            for (i in 0 until chunkSize) {
                val t = (sampleIndex % (sampleRate * cycleSec).toLong()).toDouble() / sampleRate
                var compositeSample = 0.0

                for (s in 0 until 4) {
                    val pluckTime = s * pluckInterval
                    val age = if (t >= pluckTime) {
                        t - pluckTime
                    } else {
                        (t + cycleSec) - pluckTime
                    }

                    if (age >= 0 && age < 3.2) {
                        val freq = stringFrequencies[s]
                        val decay = exp(-age * 1.35)
                        val fundamental = sin(2.0 * PI * freq * age)
                        val h2 = 0.65 * sin(2.0 * PI * (freq * 2.0) * age) * exp(-age * 1.5)
                        val h3 = 0.40 * sin(2.0 * PI * (freq * 3.0) * age) * exp(-age * 2.0)
                        val h4 = 0.25 * sin(2.0 * PI * (freq * 4.0) * age) * exp(-age * 2.5)
                        val h5 = 0.15 * sin(2.0 * PI * (freq * 5.0) * age) * exp(-age * 3.0)
                        val jawari = 0.10 * sin(2.0 * PI * (freq * 2.01) * age)

                        val stringAmp = (fundamental + h2 + h3 + h4 + h5 + jawari) * decay
                        compositeSample += stringAmp
                    }
                }

                compositeSample *= (_volume.value * 0.45)
                val clamped = (compositeSample * 32767.0).coerceIn(-32767.0, 32767.0).toInt().toShort()
                shortBuffer[i] = clamped
                sampleIndex++
            }

            audioTrack?.write(shortBuffer, 0, chunkSize)
        }
    }

    private fun stopSynthOnly() {
        synthJob?.cancel()
        synthJob = null
        try {
            audioTrack?.apply {
                stop()
                release()
            }
        } catch (_: Exception) {}
        audioTrack = null
    }

    private fun stopCustomOnly() {
        try {
            customMediaPlayer?.apply {
                if (isPlaying) {
                    stop()
                }
                release()
            }
        } catch (_: Exception) {}
        customMediaPlayer = null
    }

    fun stop() {
        stopSynthOnly()
        stopCustomOnly()
        _isPlaying.value = false
    }
}
