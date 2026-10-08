package com.example.ui.viewmodel

import com.example.data.model.CategoryEntity
import com.example.data.model.CustomTanpuraEntity
import com.example.data.model.LessonEntity
import com.example.data.model.PracticeLogEntity
import com.example.data.model.RecordingEntity

enum class AppScreen {
    LESSONS,
    RECORD_STUDIO,
    RECORDINGS_VAULT,
    YOUTUBE_REFERENCE,
    PROGRESS_TRACKER
}

data class CarnaticUiState(
    val currentScreen: AppScreen = AppScreen.LESSONS,
    val categories: List<CategoryEntity> = emptyList(),
    val lessons: List<LessonEntity> = emptyList(),
    val recordings: List<RecordingEntity> = emptyList(),
    val practiceLogs: List<PracticeLogEntity> = emptyList(),
    val customTanpuras: List<CustomTanpuraEntity> = emptyList(),
    val selectedCategoryId: String? = "sarali",
    val selectedLesson: LessonEntity? = null,
    val isRecording: Boolean = false,
    val isRecordingPaused: Boolean = false,
    val recordingDurationSec: Int = 0,
    val recordingAmplitude: Float = 0f,
    val selectedPracticeType: String = "Swaram", // Swaram, Akaram (Aa), Ukaram (Uu), Ikaram (Ii), Sahitya (Lyrics), All 4 Speeds Drill, Teacher Audio
    val activePlayingRecordingId: Long? = null,
    val currentPlayingRecording: RecordingEntity? = null,
    val isPlayingAudio: Boolean = false,
    val audioProgressMs: Int = 0,
    val audioDurationMs: Int = 0,
    val audioPlaybackSpeed: Float = 1.0f,
    val isAudioLooping: Boolean = false,
    val isTanpuraPlaying: Boolean = false,
    val tanpuraKattai: String = "1.5 Kattai (C#)",
    val tanpuraTuning: String = "Pa",
    val tanpuraVolume: Float = 0.85f,
    val activeTanpuraTitle: String? = "Synthesized Drone",
    val isCustomTanpuraActive: Boolean = false,
    val isMetronomePlaying: Boolean = false,
    val metronomeBpm: Int = 72,
    val metronomeBeat: Int = 0,
    val metronomeKalam: Int = 1,
    val selectedTalaName: String = "Adi Tala",
    val searchQuery: String = "",
    val recordingSourceFilter: String = "ALL", // ALL, STUDENT_MIC, TEACHER_RECORDING, IMPORTED_AUDIO
    val messageSnackbar: String? = null,
    // Compare recordings state
    val compareRecordingA: RecordingEntity? = null,
    val compareRecordingB: RecordingEntity? = null
)
