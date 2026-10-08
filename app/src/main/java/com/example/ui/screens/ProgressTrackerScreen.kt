package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity
import com.example.data.model.PracticeLogEntity
import com.example.data.model.RecordingEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressTrackerScreen(
    recordings: List<RecordingEntity>,
    practiceLogs: List<PracticeLogEntity>,
    categories: List<CategoryEntity>,
    lessons: List<LessonEntity>,
    activePlayingRecordingId: Long?,
    isPlayingAudio: Boolean,
    compareRecordingA: RecordingEntity?,
    compareRecordingB: RecordingEntity?,
    onSetCompareA: (RecordingEntity?) -> Unit,
    onSetCompareB: (RecordingEntity?) -> Unit,
    onPlayRecording: (RecordingEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val totalRecordingsCount = recordings.size
    val totalSeconds = recordings.sumOf { it.durationMs / 1000 }
    val totalMinutes = totalSeconds / 60
    val totalHours = totalMinutes / 60
    val remainingMins = totalMinutes % 60

    // Practice streaks
    val uniqueDates = remember(recordings, practiceLogs) {
        val recDates = recordings.map {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(it.recordedAt))
        }
        val logDates = practiceLogs.map { it.dateStr }
        (recDates + logDates).distinct().sortedDescending()
    }

    val streakCount = remember(uniqueDates) {
        uniqueDates.size.coerceAtLeast(1)
    }

    // Kalam distribution
    val kalam1Count = recordings.count { it.speedKalam == 1 }
    val kalam2Count = recordings.count { it.speedKalam == 2 }
    val kalam3Count = recordings.count { it.speedKalam == 3 }
    val kalam4Count = recordings.count { it.speedKalam == 4 }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Daily Sadhana & Progress Review",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Track your daily practice time, kalam speeds, and listen to voice progress",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Metrics Banner: Streak, Total Practice, Recordings
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Streak Card
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "$streakCount Days",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Practice Streak",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }

                // Total Time Card
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (totalHours > 0) "${totalHours}h ${remainingMins}m" else "${totalMinutes}m",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Total Riyaz",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }

                // Total Recordings Card
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "$totalRecordingsCount",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Recordings",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Kalam Speed Mastery Section
        item {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Kalam (Speed) Mastery Progress",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    // 1st Kalam
                    val maxTarget = (totalRecordingsCount.coerceAtLeast(10)).toFloat()
                    KalamProgressRow(
                        title = "1st Speed (Prathama Kalam)",
                        count = kalam1Count,
                        progress = (kalam1Count / maxTarget).coerceIn(0f, 1f),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // 2nd Kalam
                    KalamProgressRow(
                        title = "2nd Speed (Dvitiya Kalam)",
                        count = kalam2Count,
                        progress = (kalam2Count / maxTarget).coerceIn(0f, 1f),
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // 3rd Kalam
                    KalamProgressRow(
                        title = "3rd Speed (Tritiya Kalam)",
                        count = kalam3Count,
                        progress = (kalam3Count / maxTarget).coerceIn(0f, 1f),
                        color = MaterialTheme.colorScheme.tertiary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // 4th Kalam
                    KalamProgressRow(
                        title = "4th Speed (Chaturtha Kalam)",
                        count = kalam4Count,
                        progress = (kalam4Count / maxTarget).coerceIn(0f, 1f),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        // "Listen & Compare Progress" Feature
        item {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("compare_progress_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CompareArrows,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Compare Voice Progress (Side-by-Side)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Compare your Day 1 recording vs Recent recording to hear vocal evolution",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Recording A Picker & Play
                    CompareSlot(
                        label = "Recording A (e.g. Earlier Session)",
                        selectedRecording = compareRecordingA,
                        allRecordings = recordings,
                        isPlaying = activePlayingRecordingId == compareRecordingA?.id && isPlayingAudio,
                        onSelect = { onSetCompareA(it) },
                        onPlay = { compareRecordingA?.let { onPlayRecording(it) } }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Recording B Picker & Play
                    CompareSlot(
                        label = "Recording B (e.g. Latest Session)",
                        selectedRecording = compareRecordingB,
                        allRecordings = recordings,
                        isPlaying = activePlayingRecordingId == compareRecordingB?.id && isPlayingAudio,
                        onSelect = { onSetCompareB(it) },
                        onPlay = { compareRecordingB?.let { onPlayRecording(it) } }
                    )
                }
            }
        }

        // Daily Practice Activity List
        item {
            Text(
                text = "Recent Practice Logs:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        if (recordings.isEmpty()) {
            item {
                Text(
                    text = "No practice logs yet. Start recording your daily Sarali and Jantai varisais!",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            items(recordings.take(8)) { rec ->
                val dateFmt = remember(rec.recordedAt) {
                    SimpleDateFormat("EEEE, MMM dd", Locale.getDefault()).format(Date(rec.recordedAt))
                }
                val isPlayingThis = activePlayingRecordingId == rec.id && isPlayingAudio

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FilledIconButton(
                            onClick = { onPlayRecording(rec) },
                            modifier = Modifier.size(38.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlayingThis) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = rec.title,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "$dateFmt • Kalam ${rec.speedKalam} • ${rec.pitchKattai}",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun KalamProgressRow(
    title: String,
    count: Int,
    progress: Float,
    color: androidx.compose.ui.graphics.Color
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
            Text(text = "$count recordings", style = MaterialTheme.typography.bodySmall, color = color)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = color,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CompareSlot(
    label: String,
    selectedRecording: RecordingEntity?,
    allRecordings: List<RecordingEntity>,
    isPlaying: Boolean,
    onSelect: (RecordingEntity) -> Unit,
    onPlay: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedRecording?.title ?: "Select a recording to compare",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        singleLine = true
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        allRecordings.forEach { rec ->
                            val dateFmt = SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date(rec.recordedAt))
                            DropdownMenuItem(
                                text = { Text("${rec.title} ($dateFmt - Kalam ${rec.speedKalam})") },
                                onClick = {
                                    onSelect(rec)
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                if (selectedRecording != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    FilledIconButton(
                        onClick = onPlay,
                        modifier = Modifier.size(46.dp)
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play"
                        )
                    }
                }
            }
        }
    }
}
