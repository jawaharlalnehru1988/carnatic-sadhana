package com.example.audio

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
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
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AudioRecorderManager(private val context: Context) {

    private var recorder: MediaRecorder? = null
    private var currentOutputFile: File? = null
    private var recordingStartTime: Long = 0L
    private var totalPausedDuration: Long = 0L
    private var pauseStartTime: Long = 0L

    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording.asStateFlow()

    private val _isPaused = MutableStateFlow(false)
    val isPaused: StateFlow<Boolean> = _isPaused.asStateFlow()

    private val _elapsedSeconds = MutableStateFlow(0)
    val elapsedSeconds: StateFlow<Int> = _elapsedSeconds.asStateFlow()

    private val _amplitude = MutableStateFlow(0f)
    val amplitude: StateFlow<Float> = _amplitude.asStateFlow()

    private var monitorJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default)

    private fun getStorageDir(): File {
        val dir = File(context.filesDir, "carnatic_recordings")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    fun startRecording(prefix: String = "practice"): Boolean {
        try {
            stopRecording(discard = true)

            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val sanitizedPrefix = prefix.replace(Regex("[^a-zA-Z0-9_-]"), "_").take(30)
            val fileName = "${sanitizedPrefix}_$timeStamp.m4a"
            val file = File(getStorageDir(), fileName)
            currentOutputFile = file

            val rec = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MediaRecorder(context)
            } else {
                @Suppress("DEPRECATION")
                MediaRecorder()
            }

            rec.apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setAudioEncodingBitRate(192000)
                setAudioSamplingRate(44100)
                setOutputFile(file.absolutePath)
                prepare()
                start()
            }

            recorder = rec
            recordingStartTime = System.currentTimeMillis()
            totalPausedDuration = 0L
            pauseStartTime = 0L
            _isRecording.value = true
            _isPaused.value = false
            _elapsedSeconds.value = 0

            startMonitoring()
            return true
        } catch (e: Exception) {
            Log.e("AudioRecorderManager", "Failed to start recording", e)
            _isRecording.value = false
            _isPaused.value = false
            return false
        }
    }

    fun pauseRecording() {
        if (_isRecording.value && !_isPaused.value && recorder != null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    recorder?.pause()
                    pauseStartTime = System.currentTimeMillis()
                    _isPaused.value = true
                }
            } catch (e: Exception) {
                Log.e("AudioRecorderManager", "Error pausing recorder", e)
            }
        }
    }

    fun resumeRecording() {
        if (_isRecording.value && _isPaused.value && recorder != null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    recorder?.resume()
                    if (pauseStartTime > 0) {
                        totalPausedDuration += (System.currentTimeMillis() - pauseStartTime)
                        pauseStartTime = 0L
                    }
                    _isPaused.value = false
                }
            } catch (e: Exception) {
                Log.e("AudioRecorderManager", "Error resuming recorder", e)
            }
        }
    }

    fun stopRecording(discard: Boolean = false): File? {
        monitorJob?.cancel()
        monitorJob = null
        _amplitude.value = 0f

        var savedFile: File? = null
        try {
            if (_isRecording.value && recorder != null) {
                recorder?.apply {
                    try {
                        stop()
                    } catch (e: Exception) {
                        Log.w("AudioRecorderManager", "Stop error", e)
                    }
                    release()
                }
                recorder = null

                if (!discard && currentOutputFile != null && currentOutputFile!!.exists() && currentOutputFile!!.length() > 0) {
                    savedFile = currentOutputFile
                } else if (discard && currentOutputFile != null) {
                    currentOutputFile?.delete()
                }
            }
        } catch (e: Exception) {
            Log.e("AudioRecorderManager", "Error stopping recorder", e)
        } finally {
            _isRecording.value = false
            _isPaused.value = false
            recorder = null
        }
        return savedFile
    }

    private fun startMonitoring() {
        monitorJob?.cancel()
        monitorJob = scope.launch {
            var envelope = 0.15f
            while (isActive && _isRecording.value) {
                if (!_isPaused.value) {
                    val activeDuration = System.currentTimeMillis() - recordingStartTime - totalPausedDuration
                    _elapsedSeconds.value = (activeDuration / 1000).toInt()

                    try {
                        val maxAmp = recorder?.maxAmplitude ?: 0
                        // Phone microphones typically return 100 - 6000 for regular singing voice
                        if (maxAmp > 60) {
                            val normalized = (maxAmp.toDouble() / 14000.0).coerceIn(0.0, 1.0)
                            // Perceptual audio power curve to boost softer vocal hums and tones
                            val targetAmp = (Math.pow(normalized, 0.32) * 1.35).coerceIn(0.18, 1.0).toFloat()
                            if (targetAmp > envelope) {
                                // Fast attack when voice enters
                                envelope = targetAmp
                            } else {
                                // Gentle decay
                                envelope = envelope * 0.84f + targetAmp * 0.16f
                            }
                        } else {
                            // Decay towards gentle resting baseline (0.12f)
                            envelope = (envelope * 0.88f).coerceAtLeast(0.14f)
                        }
                        _amplitude.value = envelope
                    } catch (_: Exception) {
                        _amplitude.value = 0.14f
                    }
                }
                delay(30)
            }
        }
    }
}
