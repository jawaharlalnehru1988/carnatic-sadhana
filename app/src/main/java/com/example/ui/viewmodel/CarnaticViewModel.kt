package com.example.ui.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.AudioConverterUtil
import com.example.audio.AudioPlayerManager
import com.example.audio.AudioRecorderManager
import com.example.audio.TalaMetronome
import com.example.audio.TanpuraSynthesizer
import com.example.data.local.CarnaticDatabase
import android.media.MediaMetadataRetriever
import com.example.data.model.CategoryEntity
import com.example.data.model.CustomTanpuraEntity
import com.example.data.model.LessonEntity
import com.example.data.model.PracticeLogEntity
import com.example.data.model.RecordingEntity
import com.example.data.repository.CarnaticRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CarnaticViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarnaticRepository
    val recorderManager: AudioRecorderManager = AudioRecorderManager(application)
    val playerManager: AudioPlayerManager = AudioPlayerManager(application)
    val tanpuraSynthesizer: TanpuraSynthesizer = TanpuraSynthesizer()
    val talaMetronome: TalaMetronome = TalaMetronome()

    private val _uiState = MutableStateFlow(CarnaticUiState())
    val uiState: StateFlow<CarnaticUiState> = _uiState.asStateFlow()

    init {
        val db = CarnaticDatabase.getDatabase(application, viewModelScope)
        repository = CarnaticRepository(db.carnaticDao())

        // Ensure default data
        viewModelScope.launch {
            repository.ensureDefaultData()
        }

        // Observe repository flows
        viewModelScope.launch {
            repository.categories.collect { cats ->
                _uiState.value = _uiState.value.copy(categories = cats)
            }
        }
        viewModelScope.launch {
            repository.lessons.collect { les ->
                _uiState.value = _uiState.value.copy(lessons = les)
            }
        }
        viewModelScope.launch {
            repository.recordings.collect { recs ->
                _uiState.value = _uiState.value.copy(recordings = recs)
            }
        }
        viewModelScope.launch {
            repository.practiceLogs.collect { logs ->
                _uiState.value = _uiState.value.copy(practiceLogs = logs)
            }
        }
        viewModelScope.launch {
            repository.customTanpuras.collect { tanpuras ->
                _uiState.value = _uiState.value.copy(customTanpuras = tanpuras)
            }
        }
        viewModelScope.launch {
            tanpuraSynthesizer.activeTrackTitle.collect { title ->
                _uiState.value = _uiState.value.copy(activeTanpuraTitle = title)
            }
        }
        viewModelScope.launch {
            tanpuraSynthesizer.isCustomTrackActive.collect { isCustom ->
                _uiState.value = _uiState.value.copy(isCustomTanpuraActive = isCustom)
            }
        }

        // Observe recorder state
        viewModelScope.launch {
            recorderManager.isRecording.collect { rec ->
                _uiState.value = _uiState.value.copy(isRecording = rec)
            }
        }
        viewModelScope.launch {
            recorderManager.isPaused.collect { paused ->
                _uiState.value = _uiState.value.copy(isRecordingPaused = paused)
            }
        }
        viewModelScope.launch {
            recorderManager.elapsedSeconds.collect { sec ->
                _uiState.value = _uiState.value.copy(recordingDurationSec = sec)
            }
        }
        viewModelScope.launch {
            recorderManager.amplitude.collect { amp ->
                _uiState.value = _uiState.value.copy(recordingAmplitude = amp)
            }
        }

        // Observe player state
        viewModelScope.launch {
            playerManager.isPlaying.collect { playing ->
                _uiState.value = _uiState.value.copy(isPlayingAudio = playing)
            }
        }
        viewModelScope.launch {
            playerManager.activeRecordingId.collect { id ->
                _uiState.value = _uiState.value.copy(
                    activePlayingRecordingId = id,
                    currentPlayingRecording = if (id == null) null else _uiState.value.currentPlayingRecording
                )
            }
        }
        viewModelScope.launch {
            playerManager.currentPositionMs.collect { pos ->
                _uiState.value = _uiState.value.copy(audioProgressMs = pos)
            }
        }
        viewModelScope.launch {
            playerManager.durationMs.collect { dur ->
                _uiState.value = _uiState.value.copy(audioDurationMs = dur)
            }
        }
        viewModelScope.launch {
            playerManager.playbackSpeed.collect { spd ->
                _uiState.value = _uiState.value.copy(audioPlaybackSpeed = spd)
            }
        }
        viewModelScope.launch {
            playerManager.isLooping.collect { loop ->
                _uiState.value = _uiState.value.copy(isAudioLooping = loop)
            }
        }

        // Observe Tanpura & Metronome
        viewModelScope.launch {
            tanpuraSynthesizer.isPlaying.collect { isPlaying ->
                _uiState.value = _uiState.value.copy(isTanpuraPlaying = isPlaying)
            }
        }
        viewModelScope.launch {
            tanpuraSynthesizer.kattai.collect { k ->
                _uiState.value = _uiState.value.copy(tanpuraKattai = k)
            }
        }
        viewModelScope.launch {
            tanpuraSynthesizer.tuning.collect { t ->
                _uiState.value = _uiState.value.copy(tanpuraTuning = t)
            }
        }
        viewModelScope.launch {
            tanpuraSynthesizer.volume.collect { v ->
                _uiState.value = _uiState.value.copy(tanpuraVolume = v)
            }
        }
        viewModelScope.launch {
            talaMetronome.isPlaying.collect { isPlaying ->
                _uiState.value = _uiState.value.copy(isMetronomePlaying = isPlaying)
            }
        }
        viewModelScope.launch {
            talaMetronome.bpm.collect { bpm ->
                _uiState.value = _uiState.value.copy(metronomeBpm = bpm)
            }
        }
        viewModelScope.launch {
            talaMetronome.currentBeat.collect { beat ->
                _uiState.value = _uiState.value.copy(metronomeBeat = beat)
            }
        }
        viewModelScope.launch {
            talaMetronome.kalam.collect { kal ->
                _uiState.value = _uiState.value.copy(metronomeKalam = kal)
            }
        }
        viewModelScope.launch {
            talaMetronome.selectedTala.collect { tala ->
                _uiState.value = _uiState.value.copy(selectedTalaName = tala.name)
            }
        }
    }

    fun navigateTo(screen: AppScreen) {
        _uiState.value = _uiState.value.copy(currentScreen = screen)
    }

    fun selectCategory(categoryId: String?) {
        _uiState.value = _uiState.value.copy(selectedCategoryId = categoryId)
    }

    fun selectLesson(lesson: LessonEntity?) {
        _uiState.value = _uiState.value.copy(selectedLesson = lesson)
    }

    fun setSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun setRecordingSourceFilter(filter: String) {
        _uiState.value = _uiState.value.copy(recordingSourceFilter = filter)
    }

    fun clearSnackbar() {
        _uiState.value = _uiState.value.copy(messageSnackbar = null)
    }

    fun showMessage(msg: String) {
        _uiState.value = _uiState.value.copy(messageSnackbar = msg)
    }

    // --- Recording Studio Actions ---
    fun startStudioRecording(prefix: String) {
        val success = recorderManager.startRecording(prefix)
        if (!success) {
            showMessage("Microphone permission or audio error")
        }
    }

    fun pauseStudioRecording() {
        recorderManager.pauseRecording()
    }

    fun resumeStudioRecording() {
        recorderManager.resumeRecording()
    }

    fun discardStudioRecording() {
        recorderManager.stopRecording(discard = true)
        showMessage("Recording discarded")
    }

    fun setSelectedPracticeType(type: String) {
        _uiState.value = _uiState.value.copy(selectedPracticeType = type)
    }

    fun saveStudioRecording(
        title: String,
        categoryId: String,
        lessonId: String?,
        speedKalam: Int = 1,
        practiceType: String = "Swaram",
        pitchKattai: String,
        tala: String,
        notes: String,
        source: String = "STUDENT_MIC",
        rating: Int = 0
    ) {
        val savedFile = recorderManager.stopRecording(discard = false)
        if (savedFile != null && savedFile.exists()) {
            val durationMs = _uiState.value.recordingDurationSec * 1000L
            val rec = RecordingEntity(
                title = title.ifBlank { savedFile.nameWithoutExtension },
                categoryId = categoryId,
                lessonId = lessonId,
                filePath = savedFile.absolutePath,
                fileName = savedFile.name,
                durationMs = durationMs,
                speedKalam = speedKalam,
                practiceType = practiceType,
                pitchKattai = pitchKattai,
                tala = tala,
                notes = notes,
                source = source,
                rating = rating
            )
            viewModelScope.launch {
                repository.saveRecording(rec)

                // Log practice session
                val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                val categoryName = _uiState.value.categories.find { it.id == categoryId }?.name ?: "Practice"
                val lessonTitle = _uiState.value.lessons.find { it.id == lessonId }?.title ?: title
                repository.logPracticeSession(
                    PracticeLogEntity(
                        dateStr = todayStr,
                        durationSeconds = _uiState.value.recordingDurationSec,
                        lessonTitle = "$lessonTitle ($practiceType)",
                        categoryName = categoryName,
                        speedKalam = speedKalam
                    )
                )
                showMessage("Practice take ($practiceType) saved to $categoryName!")
            }
        } else {
            showMessage("Recording could not be saved")
        }
    }

    // --- Move Category Feature ---
    fun moveRecordingToCategory(recordingId: Long, newCategoryId: String, newLessonId: String? = null) {
        viewModelScope.launch {
            repository.moveRecordingCategory(recordingId, newCategoryId, newLessonId)
            val catName = _uiState.value.categories.find { it.id == newCategoryId }?.name ?: "New Category"
            showMessage("Recording moved to $catName")
        }
    }

    // --- Delete & Update Recording ---
    fun deleteRecording(recording: RecordingEntity) {
        viewModelScope.launch {
            if (_uiState.value.activePlayingRecordingId == recording.id) {
                playerManager.stop()
            }
            repository.deleteRecording(recording.id)
            try {
                val f = File(recording.filePath)
                if (f.exists()) f.delete()
            } catch (_: Exception) {}
            showMessage("Recording deleted")
        }
    }

    fun toggleFavorite(recording: RecordingEntity) {
        viewModelScope.launch {
            repository.toggleFavorite(recording.id, !recording.isFavorite)
        }
    }

    fun updateRecording(recording: RecordingEntity) {
        viewModelScope.launch {
            repository.updateRecording(recording)
            showMessage("Recording updated")
        }
    }

    // --- Player Actions ---
    fun playRecording(recording: RecordingEntity) {
        _uiState.value = _uiState.value.copy(currentPlayingRecording = recording)
        playerManager.togglePlayPause(recording.filePath, recording.id)
    }

    fun pausePlayback() {
        playerManager.pause()
    }

    fun resumePlayback() {
        playerManager.resume()
    }

    fun seekPlayback(posMs: Int) {
        playerManager.seekTo(posMs)
    }

    fun setPlaybackSpeed(speed: Float) {
        playerManager.setSpeed(speed)
    }

    fun togglePlaybackLoop() {
        playerManager.toggleLoop()
    }

    // --- Audio Import & Converter Feature ---
    fun importAudioFromUri(
        uri: Uri,
        title: String,
        categoryId: String,
        lessonId: String?,
        isTeacherRecording: Boolean,
        speedKalam: Int,
        pitchKattai: String,
        tala: String,
        notes: String
    ) {
        viewModelScope.launch {
            showMessage("Importing and standardizing audio...")
            val result = AudioConverterUtil.importAndConvertAudio(
                context = getApplication(),
                uri = uri,
                customTitle = title,
                forceStandardize = true
            )
            if (result.success && result.outputFile != null) {
                val source = if (isTeacherRecording) "TEACHER_RECORDING" else "IMPORTED_AUDIO"
                val rec = RecordingEntity(
                    title = title.ifBlank { result.fileName.substringBeforeLast('.') },
                    categoryId = categoryId,
                    lessonId = lessonId,
                    filePath = result.outputFile.absolutePath,
                    fileName = result.fileName,
                    durationMs = result.durationMs,
                    speedKalam = speedKalam,
                    pitchKattai = pitchKattai,
                    tala = tala,
                    notes = notes,
                    source = source
                )
                repository.saveRecording(rec)
                val catName = _uiState.value.categories.find { it.id == categoryId }?.name ?: "Category"
                showMessage("Imported successfully into $catName!")
            } else {
                showMessage("Import failed: ${result.errorMessage ?: "Unknown error"}")
            }
        }
    }

    // --- Add Custom Lesson & Category ---
    fun addCustomCategory(name: String, description: String) {
        viewModelScope.launch {
            val id = "custom_${System.currentTimeMillis()}"
            val newCat = CategoryEntity(
                id = id,
                name = name,
                description = description,
                iconKey = "folder",
                orderIndex = (_uiState.value.categories.maxOfOrNull { it.orderIndex } ?: 0) + 1,
                isCustom = true
            )
            repository.insertCategory(newCat)
            showMessage("Category '$name' created")
        }
    }

    fun addCustomLesson(
        categoryId: String,
        title: String,
        raga: String,
        tala: String,
        swaras: String,
        sahitya: String,
        youtubeUrl: String?
    ) {
        viewModelScope.launch {
            val id = "lesson_${System.currentTimeMillis()}"
            val newLesson = LessonEntity(
                id = id,
                categoryId = categoryId,
                title = title,
                raga = raga.ifBlank { "Mayamalavagowla" },
                tala = tala.ifBlank { "Adi Tala" },
                swaras = swaras,
                sahitya = sahitya,
                youtubeUrl = youtubeUrl?.ifBlank { null },
                orderIndex = (_uiState.value.lessons.filter { it.categoryId == categoryId }.maxOfOrNull { it.orderIndex } ?: 0) + 1,
                isCustom = true
            )
            repository.insertLesson(newLesson)
            showMessage("Lesson '$title' added")
        }
    }

    fun updateCategory(category: CategoryEntity) {
        viewModelScope.launch {
            repository.updateCategory(category)
            showMessage("Category '${category.name}' updated")
        }
    }

    fun deleteCategory(categoryId: String) {
        viewModelScope.launch {
            val catName = _uiState.value.categories.find { it.id == categoryId }?.name ?: "Category"
            repository.deleteCategory(categoryId)
            if (_uiState.value.selectedCategoryId == categoryId) {
                _uiState.value = _uiState.value.copy(selectedCategoryId = null, selectedLesson = null)
            }
            showMessage("Category '$catName' deleted")
        }
    }

    fun updateLesson(lesson: LessonEntity) {
        viewModelScope.launch {
            repository.updateLesson(lesson)
            if (_uiState.value.selectedLesson?.id == lesson.id) {
                _uiState.value = _uiState.value.copy(selectedLesson = lesson)
            }
            showMessage("Lesson '${lesson.title}' updated")
        }
    }

    fun deleteLesson(lessonId: String) {
        viewModelScope.launch {
            val lessonTitle = _uiState.value.lessons.find { it.id == lessonId }?.title ?: "Lesson"
            repository.deleteLesson(lessonId)
            if (_uiState.value.selectedLesson?.id == lessonId) {
                _uiState.value = _uiState.value.copy(selectedLesson = null)
            }
            showMessage("Lesson '$lessonTitle' deleted")
        }
    }

    fun updateLessonYoutube(lessonId: String, youtubeUrl: String) {
        viewModelScope.launch {
            repository.updateLessonYoutube(lessonId, youtubeUrl)
            showMessage("YouTube reference updated")
        }
    }

    // --- Custom Tanpura Upload & Management ---
    fun uploadCustomTanpura(uri: Uri, title: String, pitchKattai: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val context = getApplication<Application>()
                val dir = File(context.filesDir, "custom_tanpuras")
                if (!dir.exists()) dir.mkdirs()

                val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
                val sanitized = title.replace(Regex("[^a-zA-Z0-9_-]"), "_").take(30)
                val destFile = File(dir, "tanpura_${sanitized}_$timeStamp.mp3")

                context.contentResolver.openInputStream(uri)?.use { input ->
                    FileOutputStream(destFile).use { output ->
                        input.copyTo(output)
                    }
                } ?: run {
                    showMessage("Could not read uploaded audio file")
                    return@launch
                }

                val retriever = MediaMetadataRetriever()
                var dur = 0L
                try {
                    retriever.setDataSource(destFile.absolutePath)
                    dur = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull() ?: 0L
                } catch (_: Exception) {} finally {
                    retriever.release()
                }

                val entity = CustomTanpuraEntity(
                    title = title.ifBlank { "Custom Tanpura" },
                    pitchKattai = pitchKattai,
                    filePath = destFile.absolutePath,
                    fileName = destFile.name,
                    durationMs = dur
                )
                repository.saveCustomTanpura(entity)
                tanpuraSynthesizer.playCustomAudio(entity.filePath, entity.title)
                showMessage("Custom Tanpura loaded & playing!")
            } catch (e: Exception) {
                Log.e("CarnaticViewModel", "Error uploading tanpura", e)
                showMessage("Failed to load Tanpura: ${e.localizedMessage}")
            }
        }
    }

    fun playCustomTanpura(tanpura: CustomTanpuraEntity) {
        tanpuraSynthesizer.playCustomAudio(tanpura.filePath, tanpura.title)
    }

    fun playSynthTanpura() {
        tanpuraSynthesizer.playSynth()
    }

    fun deleteCustomTanpura(tanpura: CustomTanpuraEntity) {
        viewModelScope.launch {
            if (_uiState.value.activeTanpuraTitle == tanpura.title) {
                tanpuraSynthesizer.stop()
            }
            repository.deleteCustomTanpura(tanpura.id)
            try {
                val f = File(tanpura.filePath)
                if (f.exists()) f.delete()
            } catch (_: Exception) {}
            showMessage("Custom Tanpura removed")
        }
    }

    // --- Compare Recordings ---
    fun setCompareRecordingA(rec: RecordingEntity?) {
        _uiState.value = _uiState.value.copy(compareRecordingA = rec)
    }

    fun setCompareRecordingB(rec: RecordingEntity?) {
        _uiState.value = _uiState.value.copy(compareRecordingB = rec)
    }

    override fun onCleared() {
        super.onCleared()
        recorderManager.stopRecording(discard = true)
        playerManager.stop()
        tanpuraSynthesizer.stop()
        talaMetronome.stop()
    }
}
