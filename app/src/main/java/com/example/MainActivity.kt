package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.outlined.AudioFile
import androidx.compose.material.icons.outlined.GraphicEq
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity
import com.example.data.model.RecordingEntity
import com.example.ui.components.AddLessonDialog
import com.example.ui.components.AudioConvertImportDialog
import com.example.ui.components.AudioPlayerBar
import com.example.ui.components.EditCategoryDialog
import com.example.ui.components.EditLessonDialog
import com.example.ui.components.MoveCategoryDialog
import com.example.ui.components.TalaMetronomeSheet
import com.example.ui.components.TanpuraDroneSheet
import com.example.ui.components.YouTubeEmbedDialog
import com.example.ui.screens.LessonsScreen
import com.example.ui.screens.ProgressTrackerScreen
import com.example.ui.screens.RecordStudioScreen
import com.example.ui.screens.RecordingsVaultScreen
import com.example.ui.screens.YouTubeReferenceScreen
import com.example.ui.theme.CarnaticSadhanaTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.CarnaticViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: CarnaticViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CarnaticSadhanaTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val snackbarHostState = remember { SnackbarHostState() }

                // Sheet & Dialog states
                var showTanpuraSheet by remember { mutableStateOf(false) }
                var showMetronomeSheet by remember { mutableStateOf(false) }
                var showImportDialog by remember { mutableStateOf(false) }
                var showAddLessonDialog by remember { mutableStateOf(false) }
                var editingCategory by remember { mutableStateOf<CategoryEntity?>(null) }
                var editingLesson by remember { mutableStateOf<LessonEntity?>(null) }
                var movingRecording by remember { mutableStateOf<RecordingEntity?>(null) }
                var youtubeLessonToWatch by remember { mutableStateOf<LessonEntity?>(null) }

                // Handle Back button
                BackHandler(enabled = uiState.currentScreen != AppScreen.LESSONS) {
                    viewModel.navigateTo(AppScreen.LESSONS)
                }

                // Show Snackbars
                LaunchedEffect(uiState.messageSnackbar) {
                    uiState.messageSnackbar?.let { msg ->
                        snackbarHostState.showSnackbar(msg)
                        viewModel.clearSnackbar()
                    }
                }

                // Currently active playing recording object (local or guru reference)
                val activePlayingRecording = uiState.currentPlayingRecording ?: remember(uiState.activePlayingRecordingId, uiState.recordings) {
                    uiState.recordings.find { it.id == uiState.activePlayingRecordingId }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Column {
                                    Text(
                                        text = "Carnatic Sadhana",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = when (uiState.currentScreen) {
                                            AppScreen.LESSONS -> "Lesson Library & Swaras"
                                            AppScreen.RECORD_STUDIO -> "Voice Recording Studio"
                                            AppScreen.RECORDINGS_VAULT -> "Audio Vault & Review (${uiState.recordings.size})"
                                            AppScreen.YOUTUBE_REFERENCE -> "YouTube Reference & MP3"
                                            AppScreen.PROGRESS_TRACKER -> "Daily Sadhana & Progress"
                                        },
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f),
                                        fontSize = 11.sp
                                    )
                                }
                            },
                            actions = {
                                // Tanpura Drone quick action with active badge
                                IconButton(
                                    onClick = { showTanpuraSheet = true },
                                    modifier = Modifier.testTag("topbar_btn_tanpura")
                                ) {
                                    BadgedBox(
                                        badge = {
                                            if (uiState.isTanpuraPlaying) {
                                                Badge(containerColor = MaterialTheme.colorScheme.secondary) {
                                                    Text("On", fontSize = 9.sp)
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (uiState.isTanpuraPlaying) Icons.Filled.GraphicEq else Icons.Outlined.GraphicEq,
                                            contentDescription = "Tanpura Drone",
                                            tint = if (uiState.isTanpuraPlaying) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                }

                                // Tala Metronome quick action with active badge
                                IconButton(
                                    onClick = { showMetronomeSheet = true },
                                    modifier = Modifier.testTag("topbar_btn_metronome")
                                ) {
                                    BadgedBox(
                                        badge = {
                                            if (uiState.isMetronomePlaying) {
                                                Badge(containerColor = MaterialTheme.colorScheme.secondary) {
                                                    Text("${uiState.metronomeBeat + 1}", fontSize = 9.sp)
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (uiState.isMetronomePlaying) Icons.Filled.Timer else Icons.Outlined.Timer,
                                            contentDescription = "Tala Metronome",
                                            tint = if (uiState.isMetronomePlaying) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                }

                                // Import Audio Quick Action
                                IconButton(
                                    onClick = { showImportDialog = true },
                                    modifier = Modifier.testTag("topbar_btn_import_audio")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.UploadFile,
                                        contentDescription = "Import Audio",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    },
                    bottomBar = {
                        Column {
                            // Docked Audio Player Bar (only visible when audio is playing or active)
                            AnimatedVisibility(
                                visible = activePlayingRecording != null,
                                enter = slideInVertically { it },
                                exit = slideOutVertically { it }
                            ) {
                                AudioPlayerBar(
                                    recording = activePlayingRecording,
                                    isPlaying = uiState.isPlayingAudio,
                                    progressMs = uiState.audioProgressMs,
                                    durationMs = uiState.audioDurationMs,
                                    speed = uiState.audioPlaybackSpeed,
                                    isLooping = uiState.isAudioLooping,
                                    onTogglePlayPause = {
                                        activePlayingRecording?.let { viewModel.playRecording(it) }
                                    },
                                    onStop = { viewModel.playerManager.stop() },
                                    onSeek = { viewModel.seekPlayback(it) },
                                    onSpeedChange = { viewModel.setPlaybackSpeed(it) },
                                    onToggleLoop = { viewModel.togglePlaybackLoop() },
                                    onClose = { viewModel.playerManager.stop() }
                                )
                            }

                            // Material 3 Navigation Bar
                            NavigationBar(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .windowInsetsPadding(WindowInsets.navigationBars)
                                    .testTag("main_navigation_bar"),
                                containerColor = MaterialTheme.colorScheme.surface,
                                tonalElevation = 3.dp
                            ) {
                                // 1. Lessons
                                NavigationBarItem(
                                    selected = uiState.currentScreen == AppScreen.LESSONS,
                                    onClick = { viewModel.navigateTo(AppScreen.LESSONS) },
                                    icon = {
                                        Icon(
                                            imageVector = if (uiState.currentScreen == AppScreen.LESSONS) Icons.Filled.LibraryMusic else Icons.Outlined.LibraryMusic,
                                            contentDescription = "Lessons"
                                        )
                                    },
                                    label = { Text("Lessons", fontSize = 11.sp) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                        indicatorColor = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier.testTag("nav_item_lessons")
                                )

                                // 2. Record Studio
                                NavigationBarItem(
                                    selected = uiState.currentScreen == AppScreen.RECORD_STUDIO,
                                    onClick = { viewModel.navigateTo(AppScreen.RECORD_STUDIO) },
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                if (uiState.isRecording) {
                                                    Badge(containerColor = MaterialTheme.colorScheme.error)
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (uiState.currentScreen == AppScreen.RECORD_STUDIO) Icons.Filled.Mic else Icons.Outlined.Mic,
                                                contentDescription = "Record"
                                            )
                                        }
                                    },
                                    label = { Text("Studio", fontSize = 11.sp) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                        indicatorColor = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier.testTag("nav_item_record_studio")
                                )

                                // 3. Recordings Vault
                                NavigationBarItem(
                                    selected = uiState.currentScreen == AppScreen.RECORDINGS_VAULT,
                                    onClick = { viewModel.navigateTo(AppScreen.RECORDINGS_VAULT) },
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                if (uiState.recordings.isNotEmpty()) {
                                                    Badge(containerColor = MaterialTheme.colorScheme.secondary) {
                                                        Text("${uiState.recordings.size}")
                                                    }
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (uiState.currentScreen == AppScreen.RECORDINGS_VAULT) Icons.Filled.AudioFile else Icons.Outlined.AudioFile,
                                                contentDescription = "My Audios"
                                            )
                                        }
                                    },
                                    label = { Text("My Vault", fontSize = 11.sp) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                        indicatorColor = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier.testTag("nav_item_recordings_vault")
                                )

                                // 4. YouTube Reference & MP3
                                NavigationBarItem(
                                    selected = uiState.currentScreen == AppScreen.YOUTUBE_REFERENCE,
                                    onClick = { viewModel.navigateTo(AppScreen.YOUTUBE_REFERENCE) },
                                    icon = {
                                        Icon(
                                            imageVector = if (uiState.currentScreen == AppScreen.YOUTUBE_REFERENCE) Icons.Filled.OndemandVideo else Icons.Outlined.OndemandVideo,
                                            contentDescription = "YouTube"
                                        )
                                    },
                                    label = { Text("YouTube/MP3", fontSize = 11.sp) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                        indicatorColor = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier.testTag("nav_item_youtube_reference")
                                )

                                // 5. Progress Tracker
                                NavigationBarItem(
                                    selected = uiState.currentScreen == AppScreen.PROGRESS_TRACKER,
                                    onClick = { viewModel.navigateTo(AppScreen.PROGRESS_TRACKER) },
                                    icon = {
                                        Icon(
                                            imageVector = if (uiState.currentScreen == AppScreen.PROGRESS_TRACKER) Icons.Filled.Insights else Icons.Outlined.Insights,
                                            contentDescription = "Progress"
                                        )
                                    },
                                    label = { Text("Progress", fontSize = 11.sp) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                        indicatorColor = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier.testTag("nav_item_progress_tracker")
                                )
                            }
                        }
                    },
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (uiState.currentScreen) {
                            AppScreen.LESSONS -> {
                                LessonsScreen(
                                    categories = uiState.categories,
                                    lessons = uiState.lessons,
                                    recordings = uiState.recordings,
                                    selectedCategoryId = uiState.selectedCategoryId,
                                    activePlayingRecordingId = uiState.activePlayingRecordingId,
                                    isPlayingAudio = uiState.isPlayingAudio,
                                    onPlayRecording = { viewModel.playRecording(it) },
                                    onSelectCategory = { viewModel.selectCategory(it) },
                                    onStartRecordingForLesson = { lesson ->
                                        viewModel.selectLesson(lesson)
                                        viewModel.navigateTo(AppScreen.RECORD_STUDIO)
                                    },
                                    onWatchYouTubeForLesson = { lesson ->
                                        youtubeLessonToWatch = lesson
                                    },
                                    onAddNewLesson = { showAddLessonDialog = true },
                                    onEditCategory = { cat -> editingCategory = cat },
                                    onDeleteCategory = { catId -> viewModel.deleteCategory(catId) },
                                    onEditLesson = { les -> editingLesson = les },
                                    onDeleteLesson = { lesId -> viewModel.deleteLesson(lesId) }
                                )
                            }

                            AppScreen.RECORD_STUDIO -> {
                                RecordStudioScreen(
                                    categories = uiState.categories,
                                    lessons = uiState.lessons,
                                    selectedLesson = uiState.selectedLesson,
                                    isRecording = uiState.isRecording,
                                    isPaused = uiState.isRecordingPaused,
                                    elapsedSeconds = uiState.recordingDurationSec,
                                    amplitude = uiState.recordingAmplitude,
                                    isTanpuraPlaying = uiState.isTanpuraPlaying,
                                    tanpuraKattai = uiState.tanpuraKattai,
                                    isMetronomePlaying = uiState.isMetronomePlaying,
                                    metronomeBpm = uiState.metronomeBpm,
                                    metronomeKalam = uiState.metronomeKalam,
                                    onStartRecording = { prefix -> viewModel.startStudioRecording(prefix) },
                                    onPauseRecording = { viewModel.pauseStudioRecording() },
                                    onResumeRecording = { viewModel.resumeStudioRecording() },
                                    onDiscardRecording = { viewModel.discardStudioRecording() },
                                    onSaveRecording = { title, catId, lesId, practiceType, pitch, tala, notes, rating ->
                                        viewModel.saveStudioRecording(
                                            title = title,
                                            categoryId = catId,
                                            lessonId = lesId,
                                            practiceType = practiceType,
                                            pitchKattai = pitch,
                                            tala = tala,
                                            notes = notes,
                                            rating = rating
                                        )
                                    },
                                    onOpenTanpura = { showTanpuraSheet = true },
                                    onOpenMetronome = { showMetronomeSheet = true },
                                    onSelectLesson = { viewModel.selectLesson(it) }
                                )
                            }

                            AppScreen.RECORDINGS_VAULT -> {
                                RecordingsVaultScreen(
                                    recordings = uiState.recordings,
                                    categories = uiState.categories,
                                    lessons = uiState.lessons,
                                    activePlayingRecordingId = uiState.activePlayingRecordingId,
                                    isPlayingAudio = uiState.isPlayingAudio,
                                    onPlayRecording = { viewModel.playRecording(it) },
                                    onMoveCategoryClick = { rec -> movingRecording = rec },
                                    onDeleteRecording = { viewModel.deleteRecording(it) },
                                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                                    onOpenImportDialog = { showImportDialog = true }
                                )
                            }

                            AppScreen.YOUTUBE_REFERENCE -> {
                                YouTubeReferenceScreen(
                                    categories = uiState.categories,
                                    lessons = uiState.lessons,
                                    onAttachVideoToLesson = { lesId, url ->
                                        viewModel.updateLessonYoutube(lesId, url)
                                    },
                                    onOpenAudioConverter = { showImportDialog = true }
                                )
                            }

                            AppScreen.PROGRESS_TRACKER -> {
                                ProgressTrackerScreen(
                                    recordings = uiState.recordings,
                                    practiceLogs = uiState.practiceLogs,
                                    categories = uiState.categories,
                                    lessons = uiState.lessons,
                                    activePlayingRecordingId = uiState.activePlayingRecordingId,
                                    isPlayingAudio = uiState.isPlayingAudio,
                                    compareRecordingA = uiState.compareRecordingA,
                                    compareRecordingB = uiState.compareRecordingB,
                                    onSetCompareA = { viewModel.setCompareRecordingA(it) },
                                    onSetCompareB = { viewModel.setCompareRecordingB(it) },
                                    onPlayRecording = { viewModel.playRecording(it) }
                                )
                            }
                        }
                    }
                }

                // --- Modal Bottom Sheets & Dialogs ---

                // 1. Tanpura Drone Sheet
                if (showTanpuraSheet) {
                    TanpuraDroneSheet(
                        isPlaying = uiState.isTanpuraPlaying,
                        kattai = uiState.tanpuraKattai,
                        tuning = uiState.tanpuraTuning,
                        volume = uiState.tanpuraVolume,
                        activeTrackTitle = uiState.activeTanpuraTitle,
                        isCustomTrackActive = uiState.isCustomTanpuraActive,
                        customTanpuras = uiState.customTanpuras,
                        onTogglePlay = { viewModel.tanpuraSynthesizer.togglePlay() },
                        onSelectKattai = { viewModel.tanpuraSynthesizer.setKattai(it) },
                        onSelectTuning = { viewModel.tanpuraSynthesizer.setTuning(it) },
                        onVolumeChange = { viewModel.tanpuraSynthesizer.setVolume(it) },
                        onUploadCustomTanpura = { uri, title, pitch ->
                            viewModel.uploadCustomTanpura(uri, title, pitch)
                        },
                        onPlayCustomTanpura = { viewModel.playCustomTanpura(it) },
                        onPlaySynthTanpura = { viewModel.playSynthTanpura() },
                        onDeleteCustomTanpura = { viewModel.deleteCustomTanpura(it) },
                        onDismiss = { showTanpuraSheet = false }
                    )
                }

                // 2. Tala Metronome Sheet
                if (showMetronomeSheet) {
                    TalaMetronomeSheet(
                        isPlaying = uiState.isMetronomePlaying,
                        selectedTalaName = uiState.selectedTalaName,
                        bpm = uiState.metronomeBpm,
                        currentBeat = uiState.metronomeBeat,
                        kalam = uiState.metronomeKalam,
                        onTogglePlay = { viewModel.talaMetronome.togglePlay() },
                        onSelectTala = { viewModel.talaMetronome.setTala(it) },
                        onBpmChange = { viewModel.talaMetronome.setBpm(it) },
                        onKalamChange = { viewModel.talaMetronome.setKalam(it) },
                        onDismiss = { showMetronomeSheet = false }
                    )
                }

                // 3. Move Category Dialog
                movingRecording?.let { rec ->
                    MoveCategoryDialog(
                        recording = rec,
                        categories = uiState.categories,
                        lessons = uiState.lessons,
                        onDismiss = { movingRecording = null },
                        onConfirmMove = { newCategoryId, newLessonId ->
                            viewModel.moveRecordingToCategory(rec.id, newCategoryId, newLessonId)
                            movingRecording = null
                        }
                    )
                }

                // 4. Audio Convert / Native Import Dialog
                if (showImportDialog) {
                    AudioConvertImportDialog(
                        categories = uiState.categories,
                        lessons = uiState.lessons,
                        initialCategoryId = uiState.selectedCategoryId,
                        onDismiss = { showImportDialog = false },
                        onConfirmImport = { uri, title, catId, lesId, isTeacher, kalam, pitch, tala, notes ->
                            viewModel.importAudioFromUri(
                                uri = uri,
                                title = title,
                                categoryId = catId,
                                lessonId = lesId,
                                isTeacherRecording = isTeacher,
                                speedKalam = kalam,
                                pitchKattai = pitch,
                                tala = tala,
                                notes = notes
                            )
                            showImportDialog = false
                        }
                    )
                }

                // 5. Add Lesson / Category Dialog
                if (showAddLessonDialog) {
                    AddLessonDialog(
                        categories = uiState.categories,
                        initialCategoryId = uiState.selectedCategoryId,
                        onDismiss = { showAddLessonDialog = false },
                        onAddLesson = { catId, title, raga, tala, swaras, sahitya, yt ->
                            viewModel.addCustomLesson(catId, title, raga, tala, swaras, sahitya, yt)
                        },
                        onAddCategory = { name, desc ->
                            viewModel.addCustomCategory(name, desc)
                        }
                    )
                }

                // 6. YouTube Watch Dialog
                youtubeLessonToWatch?.let { lesson ->
                    YouTubeEmbedDialog(
                        videoUrlOrId = lesson.youtubeUrl ?: "kYJv8ZqjS_k",
                        title = "${lesson.title} - Video Reference",
                        onDismiss = { youtubeLessonToWatch = null }
                    )
                }

                // 7. Edit Category Dialog
                editingCategory?.let { cat ->
                    EditCategoryDialog(
                        category = cat,
                        onDismiss = { editingCategory = null },
                        onUpdateCategory = { updatedCat ->
                            viewModel.updateCategory(updatedCat)
                            editingCategory = null
                        },
                        onDeleteCategory = { catId ->
                            viewModel.deleteCategory(catId)
                            editingCategory = null
                        }
                    )
                }

                // 8. Edit Lesson Dialog
                editingLesson?.let { les ->
                    EditLessonDialog(
                        lesson = les,
                        categories = uiState.categories,
                        onDismiss = { editingLesson = null },
                        onUpdateLesson = { updatedLes ->
                            viewModel.updateLesson(updatedLes)
                            editingLesson = null
                        },
                        onDeleteLesson = { lesId ->
                            viewModel.deleteLesson(lesId)
                            editingLesson = null
                        }
                    )
                }
            }
        }
    }
}
