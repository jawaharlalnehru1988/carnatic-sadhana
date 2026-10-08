package com.example.audio

import android.content.Context
import android.media.MediaPlayer
import android.media.PlaybackParams
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

class AudioPlayerManager(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    private var progressJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _activeRecordingId = MutableStateFlow<Long?>(null)
    val activeRecordingId: StateFlow<Long?> = _activeRecordingId.asStateFlow()

    private val _currentPositionMs = MutableStateFlow(0)
    val currentPositionMs: StateFlow<Int> = _currentPositionMs.asStateFlow()

    private val _durationMs = MutableStateFlow(0)
    val durationMs: StateFlow<Int> = _durationMs.asStateFlow()

    private val _playbackSpeed = MutableStateFlow(1.0f)
    val playbackSpeed: StateFlow<Float> = _playbackSpeed.asStateFlow()

    private val _isLooping = MutableStateFlow(false)
    val isLooping: StateFlow<Boolean> = _isLooping.asStateFlow()

    private var currentPlayingFilePath: String? = null

    fun play(filePath: String, recordingId: Long? = null) {
        try {
            val isRemote = filePath.startsWith("http://") || filePath.startsWith("https://")
            if (!isRemote) {
                val file = File(filePath)
                if (!file.exists()) {
                    Log.e("AudioPlayerManager", "File not found: $filePath")
                    return
                }
            }

            // If same file is paused, just resume
            if (currentPlayingFilePath == filePath && mediaPlayer != null && !_isPlaying.value) {
                mediaPlayer?.start()
                _isPlaying.value = true
                startProgressTracker()
                return
            }

            stop()

            val player = MediaPlayer().apply {
                setDataSource(filePath)
                prepare()
                isLooping = _isLooping.value
                setOnCompletionListener {
                    if (!_isLooping.value) {
                        _isPlaying.value = false
                        _currentPositionMs.value = 0
                        progressJob?.cancel()
                    }
                }
            }

            // Apply playback speed
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    val params = PlaybackParams()
                    params.speed = _playbackSpeed.value
                    player.playbackParams = params
                } catch (e: Exception) {
                    Log.w("AudioPlayerManager", "PlaybackParams error", e)
                }
            }

            player.start()
            mediaPlayer = player
            currentPlayingFilePath = filePath
            _activeRecordingId.value = recordingId
            _isPlaying.value = true
            _durationMs.value = player.duration
            _currentPositionMs.value = 0

            startProgressTracker()
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Error playing file: $filePath", e)
            stop()
        }
    }

    fun pause() {
        try {
            if (_isPlaying.value && mediaPlayer != null) {
                mediaPlayer?.pause()
                _isPlaying.value = false
                progressJob?.cancel()
            }
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Pause error", e)
        }
    }

    fun resume() {
        try {
            if (!_isPlaying.value && mediaPlayer != null) {
                mediaPlayer?.start()
                _isPlaying.value = true
                startProgressTracker()
            }
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Resume error", e)
        }
    }

    fun togglePlayPause(filePath: String, recordingId: Long? = null) {
        if (currentPlayingFilePath == filePath && _isPlaying.value) {
            pause()
        } else {
            play(filePath, recordingId)
        }
    }

    fun seekTo(positionMs: Int) {
        try {
            mediaPlayer?.seekTo(positionMs)
            _currentPositionMs.value = positionMs
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Seek error", e)
        }
    }

    fun setSpeed(speed: Float) {
        _playbackSpeed.value = speed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mediaPlayer != null) {
            try {
                val wasPlaying = mediaPlayer?.isPlaying == true
                val params = PlaybackParams()
                params.speed = speed
                mediaPlayer?.playbackParams = params
                if (!wasPlaying) {
                    mediaPlayer?.pause()
                }
            } catch (e: Exception) {
                Log.w("AudioPlayerManager", "Error updating speed", e)
            }
        }
    }

    fun toggleLoop() {
        val newLoop = !_isLooping.value
        _isLooping.value = newLoop
        mediaPlayer?.isLooping = newLoop
    }

    fun stop() {
        progressJob?.cancel()
        progressJob = null
        try {
            mediaPlayer?.apply {
                if (isPlaying) {
                    stop()
                }
                release()
            }
        } catch (e: Exception) {
            Log.w("AudioPlayerManager", "Stop error", e)
        } finally {
            mediaPlayer = null
            _isPlaying.value = false
            currentPlayingFilePath = null
            _activeRecordingId.value = null
            _currentPositionMs.value = 0
            _durationMs.value = 0
        }
    }

    private fun startProgressTracker() {
        progressJob?.cancel()
        progressJob = scope.launch {
            while (isActive && _isPlaying.value) {
                try {
                    mediaPlayer?.let { player ->
                        if (player.isPlaying) {
                            _currentPositionMs.value = player.currentPosition
                        }
                    }
                } catch (_: Exception) { }
                delay(200)
            }
        }
    }
}
