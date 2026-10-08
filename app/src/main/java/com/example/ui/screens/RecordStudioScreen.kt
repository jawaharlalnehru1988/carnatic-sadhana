package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.audio.TanpuraSynthesizer
import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity
import com.example.ui.components.WaveformVisualizer
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordStudioScreen(
    categories: List<CategoryEntity>,
    lessons: List<LessonEntity>,
    selectedLesson: LessonEntity?,
    isRecording: Boolean,
    isPaused: Boolean,
    elapsedSeconds: Int,
    amplitude: Float,
    isTanpuraPlaying: Boolean,
    tanpuraKattai: String,
    isMetronomePlaying: Boolean,
    metronomeBpm: Int,
    metronomeKalam: Int,
    onStartRecording: (prefix: String) -> Unit,
    onPauseRecording: () -> Unit,
    onResumeRecording: () -> Unit,
    onDiscardRecording: () -> Unit,
    onSaveRecording: (
        title: String,
        categoryId: String,
        lessonId: String?,
        practiceType: String,
        pitchKattai: String,
        tala: String,
        notes: String,
        rating: Int
    ) -> Unit,
    onOpenTanpura: () -> Unit,
    onOpenMetronome: () -> Unit,
    onSelectLesson: (LessonEntity?) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var selectedCategoryId by remember(selectedLesson) {
        mutableStateOf(selectedLesson?.categoryId ?: categories.firstOrNull()?.id ?: "sarali")
    }
    var currentLesson by remember(selectedLesson) {
        mutableStateOf(selectedLesson)
    }

    // Practice Style / Sadhana Mode (Swaram, Akaram, Ukaram, Ikaram, etc.)
    var selectedPracticeType by remember { mutableStateOf("Swaram") }
    var selectedPitch by remember { mutableStateOf("1.5 Kattai (C#)") }

    var showSaveDialog by remember { mutableStateOf(false) }
    var saveTitle by remember { mutableStateOf("") }
    var saveNotes by remember { mutableStateOf("") }
    var saveRating by remember { mutableStateOf(4) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val prefix = "${currentLesson?.title ?: "Practice"}_$selectedPracticeType"
            onStartRecording(prefix)
        }
    }

    fun handleRecordClick() {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            val prefix = "${currentLesson?.title ?: "Practice"}_$selectedPracticeType"
            onStartRecording(prefix)
        } else {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    val availableLessons = remember(selectedCategoryId, lessons) {
        lessons.filter { it.categoryId == selectedCategoryId }
    }

    val practiceModes = listOf(
        "Swaram" to "Swaram (Sa Ri Ga Ma)",
        "Akaram (Aa)" to "Akaram (Aa)",
        "Ukaram (Uu)" to "Ukaram (Uu)",
        "Ikaram (Ii)" to "Ikaram (Ii)",
        "Sahitya (Lyrics)" to "Sahitya",
        "All 4 Speeds Drill" to "All 4 Speeds",
        "Teacher Class" to "Guru Class"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Lesson & Category Selector Card
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "Current Practice Session:",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))

                // Category chips
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(categories) { cat ->
                        val isSel = cat.id == selectedCategoryId
                        FilterChip(
                            selected = isSel,
                            onClick = {
                                selectedCategoryId = cat.id
                                currentLesson = lessons.firstOrNull { it.categoryId == cat.id }
                                onSelectLesson(currentLesson)
                            },
                            label = { Text(cat.name, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Lesson Selector row
                if (availableLessons.isNotEmpty()) {
                    var lessonMenuExpanded by remember { mutableStateOf(false) }
                    ExposedDropdownMenuBox(
                        expanded = lessonMenuExpanded,
                        onExpandedChange = { lessonMenuExpanded = !lessonMenuExpanded }
                    ) {
                        OutlinedTextField(
                            value = currentLesson?.title ?: "Select Lesson",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Lesson") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = lessonMenuExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = lessonMenuExpanded,
                            onDismissRequest = { lessonMenuExpanded = false }
                        ) {
                            availableLessons.forEach { les ->
                                DropdownMenuItem(
                                    text = { Text(les.title) },
                                    onClick = {
                                        currentLesson = les
                                        onSelectLesson(les)
                                        lessonMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                // Swaras snippet for quick reference during practice
                currentLesson?.let { les ->
                    if (les.swaras.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ) {
                            Text(
                                text = les.swaras.lines().take(4).joinToString("\n"),
                                modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 11.sp,
                                maxLines = 4
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Accompaniment Assistant Toolbar: Tanpura & Tala Metronome
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Tanpura Card
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onOpenTanpura() }
                    .testTag("card_tanpura_companion"),
                colors = CardDefaults.cardColors(
                    containerColor = if (isTanpuraPlaying) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.GraphicEq,
                        contentDescription = null,
                        tint = if (isTanpuraPlaying) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Tanpura Drone",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isTanpuraPlaying) "Active ($tanpuraKattai)" else "Tap to tune / upload audio",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "Upload",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            // Metronome Card
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onOpenMetronome() }
                    .testTag("card_metronome_companion"),
                colors = CardDefaults.cardColors(
                    containerColor = if (isMetronomePlaying) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = if (isMetronomePlaying) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Tala Metronome",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isMetronomePlaying) "$metronomeBpm BPM (${metronomeKalam}x)" else "Tap to set",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Practice Instruction: Sing in all 4 speeds
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            )
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Speed,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Sing in all 4 Speeds (Kalams):",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "1st Speed (1 note/beat) • 2nd Speed (2 notes/beat) • 3rd Speed (4 notes/beat) • 4th Speed (8 notes/beat)",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Multiple Practice Recording Modes (Swaram, Akaram, Ukaram, Ikaram, Sahitya...)
        Text(
            text = "Practice Style / Sadhana Mode:",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(practiceModes) { (key, label) ->
                val isSel = selectedPracticeType == key
                FilterChip(
                    selected = isSel,
                    onClick = { selectedPracticeType = key },
                    label = { Text(label, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                        selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Center Recording Console with Live Dynamic Waveform & Timer
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Timer
                val min = elapsedSeconds / 60
                val sec = elapsedSeconds % 60
                val timerString = String.format(Locale.getDefault(), "%02d:%02d", min, sec)

                Text(
                    text = timerString,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = if (isRecording && !isPaused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = when {
                        !isRecording -> "Ready to Record ($selectedPracticeType)"
                        isPaused -> "Paused"
                        else -> "Recording: $selectedPracticeType"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isRecording && !isPaused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Live Dynamic Audio Waveform Visualizer (Moves with vocal tone!)
                WaveformVisualizer(
                    amplitude = amplitude,
                    isRecording = isRecording,
                    isPaused = isPaused,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Control Buttons (BIG ICONS & BIG TOUCH TARGETS!)
                if (!isRecording) {
                    // Big Start Record Button
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = { handleRecordClick() },
                            modifier = Modifier
                                .size(88.dp)
                                .testTag("btn_start_recording"),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Start Recording",
                                modifier = Modifier.size(46.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Tap to Record",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                } else {
                    // Active Recording Controls with LARGE ICONS & BUTTONS:
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 1. Discard Button (68dp)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = onDiscardRecording,
                                modifier = Modifier
                                    .size(68.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.errorContainer)
                                    .testTag("btn_discard_recording")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Discard",
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Discard",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        // 2. BIG PAUSE / RESUME BUTTON (88dp with 48dp icon!)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(
                                onClick = {
                                    if (isPaused) onResumeRecording() else onPauseRecording()
                                },
                                modifier = Modifier
                                    .size(88.dp)
                                    .testTag("btn_pause_resume_recording"),
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                    contentColor = MaterialTheme.colorScheme.onSecondary
                                )
                            ) {
                                Icon(
                                    imageVector = if (isPaused) Icons.Default.PlayArrow else Icons.Default.Pause,
                                    contentDescription = if (isPaused) "Resume" else "Pause",
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = if (isPaused) "Resume" else "Pause",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }

                        // 3. BIG STOP & SAVE BUTTON (88dp with 48dp icon!)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(
                                onClick = {
                                    saveTitle = "${currentLesson?.title ?: "Practice"} - $selectedPracticeType"
                                    showSaveDialog = true
                                },
                                modifier = Modifier
                                    .size(88.dp)
                                    .testTag("btn_stop_and_save_recording"),
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Stop,
                                    contentDescription = "Stop and Save",
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Stop & Save",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }

    // Save Recording Dialog
    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            title = {
                Text("Save Practice Recording", fontWeight = FontWeight.Bold)
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = saveTitle,
                        onValueChange = { saveTitle = it },
                        label = { Text("Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_save_recording_title"),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Lesson: ${currentLesson?.title ?: "General Practice"}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Practice Type: $selectedPracticeType",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Category: ${categories.find { it.id == selectedCategoryId }?.name}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Self Rating (1-5 stars)
                    Text(
                        text = "Self Assessment / Shruti Accuracy:",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Row {
                        for (i in 1..5) {
                            IconButton(onClick = { saveRating = i }) {
                                Icon(
                                    imageVector = if (i <= saveRating) Icons.Filled.Star else Icons.Outlined.Star,
                                    contentDescription = "$i stars",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = saveNotes,
                        onValueChange = { saveNotes = it },
                        label = { Text("Self Notes / Teacher Feedback") },
                        placeholder = { Text("e.g. Swaram alignment stable in 3rd & 4th speed.") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 3
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSaveDialog = false
                        onSaveRecording(
                            saveTitle,
                            selectedCategoryId,
                            currentLesson?.id,
                            selectedPracticeType,
                            selectedPitch,
                            currentLesson?.tala ?: "Adi Tala",
                            saveNotes,
                            saveRating
                        )
                    },
                    modifier = Modifier.testTag("btn_confirm_save_recording")
                ) {
                    Text("Save Recording")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showSaveDialog = false
                        onDiscardRecording()
                    }
                ) {
                    Text("Discard")
                }
            }
        )
    }
}
