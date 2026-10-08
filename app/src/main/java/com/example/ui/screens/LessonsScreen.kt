package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity
import com.example.data.model.RecordingEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonsScreen(
    categories: List<CategoryEntity>,
    lessons: List<LessonEntity>,
    recordings: List<RecordingEntity>,
    selectedCategoryId: String?,
    activePlayingRecordingId: Long? = null,
    isPlayingAudio: Boolean = false,
    onPlayRecording: (RecordingEntity) -> Unit = {},
    onSelectCategory: (String?) -> Unit,
    onStartRecordingForLesson: (LessonEntity) -> Unit,
    onWatchYouTubeForLesson: (LessonEntity) -> Unit,
    onAddNewLesson: () -> Unit,
    onEditCategory: (CategoryEntity) -> Unit,
    onDeleteCategory: (String) -> Unit,
    onEditLesson: (LessonEntity) -> Unit,
    onDeleteLesson: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var expandedLessonId by remember { mutableStateOf<String?>(null) }
    var lessonToDelete by remember { mutableStateOf<LessonEntity?>(null) }
    var categoryToDelete by remember { mutableStateOf<CategoryEntity?>(null) }

    val filteredLessons = remember(lessons, selectedCategoryId, searchQuery) {
        lessons.filter { lesson ->
            val matchesCategory = selectedCategoryId == null || lesson.categoryId == selectedCategoryId
            val matchesQuery = searchQuery.isBlank() ||
                lesson.title.contains(searchQuery, ignoreCase = true) ||
                lesson.raga.contains(searchQuery, ignoreCase = true) ||
                lesson.tala.contains(searchQuery, ignoreCase = true) ||
                lesson.swaras.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search lessons, ragas, swaras...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_lessons_input"),
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Category Tabs Row (Sarali, Jantai, Dattu, Mel Sthayi, Keel Sthayi, Alankarams, etc.)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedCategoryId == null,
                        onClick = { onSelectCategory(null) },
                        label = { Text("All Lessons (${lessons.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.testTag("chip_category_all")
                    )
                }

                items(categories) { cat ->
                    val isSelected = cat.id == selectedCategoryId
                    val lessonCount = lessons.count { it.categoryId == cat.id }
                    FilterChip(
                        selected = isSelected,
                        onClick = { onSelectCategory(cat.id) },
                        label = { Text("${cat.name} ($lessonCount)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.testTag("chip_category_${cat.id}")
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Current Category Banner if a category is selected (with Edit/Delete actions)
            selectedCategoryId?.let { catId ->
                val currentCat = categories.find { it.id == catId }
                if (currentCat != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .testTag("current_category_banner"),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f)),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = currentCat.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                                Text(
                                    text = currentCat.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                                )
                            }

                            // Edit Category Button
                            IconButton(
                                onClick = { onEditCategory(currentCat) },
                                modifier = Modifier.testTag("btn_edit_category_${currentCat.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Category",
                                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }

                            // Delete Category Button
                            IconButton(
                                onClick = { categoryToDelete = currentCat },
                                modifier = Modifier.testTag("btn_delete_category_${currentCat.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Category",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }

            // Lessons List
            if (filteredLessons.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.MusicNote,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                            modifier = Modifier.size(54.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "No lessons found in this category",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Tap + to add a custom lesson or exercise",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filteredLessons) { lesson ->
                        val isExpanded = expandedLessonId == lesson.id
                        val lessonRecs = recordings.filter { it.lessonId == lesson.id }
                        val hasRecordings = lessonRecs.isNotEmpty()

                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("lesson_card_${lesson.id}"),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = if (hasRecordings) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Circular Icon Indicator: Shows Headphones & count if audio is recorded!
                                    if (hasRecordings) {
                                        BadgedBox(
                                            badge = {
                                                Badge(
                                                    containerColor = MaterialTheme.colorScheme.secondary,
                                                    contentColor = MaterialTheme.colorScheme.onSecondary
                                                ) {
                                                    Text("${lessonRecs.size}")
                                                }
                                            },
                                            modifier = Modifier.clickable {
                                                expandedLessonId = if (isExpanded) null else lesson.id
                                            }
                                        ) {
                                            Surface(
                                                modifier = Modifier.size(40.dp),
                                                shape = CircleShape,
                                                color = MaterialTheme.colorScheme.secondaryContainer
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Headphones,
                                                    contentDescription = "Recorded Audio Available",
                                                    tint = MaterialTheme.colorScheme.secondary,
                                                    modifier = Modifier
                                                        .padding(9.dp)
                                                        .size(22.dp)
                                                )
                                            }
                                        }
                                    } else {
                                        Surface(
                                            modifier = Modifier
                                                .size(38.dp)
                                                .clickable {
                                                    expandedLessonId = if (isExpanded) null else lesson.id
                                                },
                                            shape = CircleShape,
                                            color = MaterialTheme.colorScheme.primaryContainer
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.MusicNote,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier
                                                    .padding(8.dp)
                                                    .size(20.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable {
                                                expandedLessonId = if (isExpanded) null else lesson.id
                                            }
                                    ) {
                                        Text(
                                            text = lesson.title,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "${lesson.raga} • ${lesson.tala}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )

                                        // Visible "Audio Available" Icon & Tag in Header
                                        if (hasRecordings) {
                                            Surface(
                                                shape = RoundedCornerShape(8.dp),
                                                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.85f),
                                                modifier = Modifier.padding(top = 4.dp)
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Headphones,
                                                        contentDescription = "Audio Available",
                                                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                                        modifier = Modifier.size(13.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Text(
                                                        text = "Audio Available (${lessonRecs.size})",
                                                        style = MaterialTheme.typography.labelSmall,
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    // Quick Edit Lesson Button
                                    IconButton(
                                        onClick = { onEditLesson(lesson) },
                                        modifier = Modifier.testTag("btn_edit_lesson_${lesson.id}")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Edit Lesson",
                                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                                        )
                                    }

                                    // Quick Delete Lesson Button
                                    IconButton(
                                        onClick = { lessonToDelete = lesson },
                                        modifier = Modifier.testTag("btn_delete_lesson_${lesson.id}")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete Lesson",
                                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
                                        )
                                    }

                                    // Expand toggle
                                    IconButton(
                                        onClick = {
                                            expandedLessonId = if (isExpanded) null else lesson.id
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                            contentDescription = if (isExpanded) "Collapse" else "Expand"
                                        )
                                    }
                                }

                                // Expanded Content: RECORDED AUDIOS (Tapping plays immediately!) + Swaras + Actions
                                AnimatedVisibility(visible = isExpanded) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 10.dp)
                                    ) {
                                        // 1. RECORDED AUDIO AVAILABILITY SECTION (Multiple Takes: Swaram, Akaram, Ukaram)
                                        if (hasRecordings) {
                                            Card(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(bottom = 10.dp)
                                                    .testTag("recorded_audio_section_${lesson.id}"),
                                                colors = CardDefaults.cardColors(
                                                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f)
                                                ),
                                                shape = RoundedCornerShape(14.dp)
                                            ) {
                                                Column(modifier = Modifier.padding(14.dp)) {
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Icon(
                                                            imageVector = Icons.Default.Headphones,
                                                            contentDescription = null,
                                                            tint = MaterialTheme.colorScheme.secondary,
                                                            modifier = Modifier.size(22.dp)
                                                        )
                                                        Spacer(modifier = Modifier.width(8.dp))
                                                        Column(modifier = Modifier.weight(1f)) {
                                                            Text(
                                                                text = "Recorded Takes (${lessonRecs.size}) - Tap to Play:",
                                                                style = MaterialTheme.typography.titleSmall,
                                                                fontWeight = FontWeight.Bold,
                                                                color = MaterialTheme.colorScheme.onSecondaryContainer
                                                            )
                                                            Text(
                                                                text = "Swaram, Akaram, Ukaram & multi-speed recordings",
                                                                style = MaterialTheme.typography.bodySmall,
                                                                fontSize = 11.sp,
                                                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                                                            )
                                                        }
                                                    }

                                                    Spacer(modifier = Modifier.height(10.dp))

                                                    lessonRecs.forEach { rec ->
                                                        val isThisPlaying = activePlayingRecordingId == rec.id && isPlayingAudio
                                                        val durSec = rec.durationMs / 1000
                                                        val durFormatted = String.format(Locale.getDefault(), "%02d:%02d", durSec / 60, durSec % 60)
                                                        val dateFormatted = remember(rec.recordedAt) {
                                                            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(rec.recordedAt))
                                                        }

                                                        Surface(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(vertical = 4.dp)
                                                                .clickable { onPlayRecording(rec) }
                                                                .testTag("recorded_audio_item_${rec.id}"),
                                                            shape = RoundedCornerShape(12.dp),
                                                            color = if (isThisPlaying) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                                                            tonalElevation = if (isThisPlaying) 6.dp else 2.dp,
                                                            shadowElevation = 2.dp
                                                        ) {
                                                            Row(
                                                                modifier = Modifier
                                                                    .fillMaxWidth()
                                                                    .padding(10.dp),
                                                                verticalAlignment = Alignment.CenterVertically
                                                            ) {
                                                                // Big Play/Pause Button (52dp with 32dp icon!)
                                                                FilledIconButton(
                                                                    onClick = { onPlayRecording(rec) },
                                                                    modifier = Modifier
                                                                        .size(52.dp)
                                                                        .testTag("btn_play_lesson_rec_${rec.id}"),
                                                                    colors = IconButtonDefaults.filledIconButtonColors(
                                                                        containerColor = if (isThisPlaying) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                                                        contentColor = if (isThisPlaying) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
                                                                    )
                                                                ) {
                                                                    Icon(
                                                                        imageVector = if (isThisPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                                                        contentDescription = if (isThisPlaying) "Pause" else "Play",
                                                                        modifier = Modifier.size(32.dp)
                                                                    )
                                                                }

                                                                Spacer(modifier = Modifier.width(12.dp))

                                                                Column(modifier = Modifier.weight(1f)) {
                                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                                        Text(
                                                                            text = rec.title,
                                                                            style = MaterialTheme.typography.bodyLarge,
                                                                            fontWeight = FontWeight.Bold,
                                                                            maxLines = 1,
                                                                            overflow = TextOverflow.Ellipsis
                                                                        )
                                                                        if (isThisPlaying) {
                                                                            Spacer(modifier = Modifier.width(6.dp))
                                                                            Text(
                                                                                text = "Playing...",
                                                                                style = MaterialTheme.typography.labelSmall,
                                                                                color = MaterialTheme.colorScheme.primary,
                                                                                fontWeight = FontWeight.Bold
                                                                            )
                                                                        }
                                                                    }

                                                                    Spacer(modifier = Modifier.height(3.dp))

                                                                    Row(
                                                                        verticalAlignment = Alignment.CenterVertically,
                                                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                                                    ) {
                                                                        Surface(
                                                                            shape = RoundedCornerShape(4.dp),
                                                                            color = MaterialTheme.colorScheme.secondaryContainer
                                                                        ) {
                                                                            Text(
                                                                                text = rec.practiceType.ifBlank { "Swaram" },
                                                                                style = MaterialTheme.typography.labelSmall,
                                                                                fontWeight = FontWeight.Bold,
                                                                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                                            )
                                                                        }
                                                                        Text(
                                                                            text = "${rec.pitchKattai} • $durFormatted • $dateFormatted",
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
                                        }

                                        // Instruction Card: Sing in all 4 speeds
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 10.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                                            ),
                                            shape = RoundedCornerShape(12.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(12.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Speed,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.primary,
                                                    modifier = Modifier.size(22.dp)
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Column {
                                                    Text(
                                                        text = "Instruction: Sing in all 4 Speeds (Kalams)",
                                                        style = MaterialTheme.typography.labelLarge,
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                                    )
                                                    Text(
                                                        text = "1st Speed (1 note/beat) • 2nd Speed (2 notes/beat) • 3rd Speed (4 notes/beat) • 4th Speed (8 notes/beat). Sing continuously through all speeds in your take.",
                                                        style = MaterialTheme.typography.bodySmall,
                                                        fontSize = 11.sp,
                                                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                                                    )
                                                }
                                            }
                                        }

                                        // 2. AROHANA & AVAROHANA
                                        if (lesson.arohana.isNotBlank()) {
                                            Row {
                                                Text(
                                                    text = "Arohanam: ",
                                                    fontWeight = FontWeight.Bold,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                                Text(
                                                    text = lesson.arohana,
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                            Row {
                                                Text(
                                                    text = "Avarohanam: ",
                                                    fontWeight = FontWeight.Bold,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                                Text(
                                                    text = lesson.avarohana,
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }

                                        // 3. SWARAS NOTATION BOX
                                        if (lesson.swaras.isNotBlank()) {
                                            Surface(
                                                modifier = Modifier.fillMaxWidth(),
                                                shape = RoundedCornerShape(10.dp),
                                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                            ) {
                                                Column(modifier = Modifier.padding(10.dp)) {
                                                    Text(
                                                        text = "Swara Notation:",
                                                        style = MaterialTheme.typography.labelMedium,
                                                        color = MaterialTheme.colorScheme.secondary,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Spacer(modifier = Modifier.height(4.dp))
                                                    Text(
                                                        text = lesson.swaras,
                                                        style = MaterialTheme.typography.bodySmall,
                                                        fontFamily = FontFamily.Monospace,
                                                        lineHeight = 18.sp,
                                                        fontWeight = FontWeight.Medium
                                                    )
                                                }
                                            }
                                        }

                                        // 4. SAHITYA
                                        if (lesson.sahitya.isNotBlank()) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Sahitya (Lyrics):",
                                                style = MaterialTheme.typography.labelMedium,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.secondary
                                            )
                                            Text(
                                                text = lesson.sahitya,
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }

                                        if (lesson.meaning.isNotBlank()) {
                                            Spacer(modifier = Modifier.height(6.dp))
                                            Text(
                                                text = lesson.meaning,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(12.dp))

                                        // 5. ACTION BUTTONS: Record, Guru Audio, Video, Edit
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Button(
                                                onClick = { onStartRecordingForLesson(lesson) },
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .testTag("btn_record_lesson_${lesson.id}"),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Mic,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text("Record")
                                            }

                                            val refAudio = com.example.data.local.PreloadedCurriculum.getReferenceAudioUrl(lesson.id)
                                            if (refAudio != null) {
                                                OutlinedButton(
                                                    onClick = {
                                                        val guruRecording = RecordingEntity(
                                                            id = -((lesson.orderIndex.toLong().takeIf { it > 0 } ?: 1L) + 1000L),
                                                            title = "${lesson.title} (Guru Audio)",
                                                            filePath = refAudio,
                                                            fileName = "${lesson.id}_guru_audio.mp3",
                                                            categoryId = lesson.categoryId,
                                                            lessonId = lesson.id,
                                                            durationMs = 60000L,
                                                            speedKalam = 1,
                                                            practiceType = "Guru Audio",
                                                            tala = lesson.tala,
                                                            pitchKattai = "1 Kattai (C)",
                                                            notes = "Reference audio from Haribol Carnatic Academy (carnatic.askharekrishna.com)",
                                                            source = "TEACHER_RECORDING"
                                                        )
                                                        onPlayRecording(guruRecording)
                                                    },
                                                    modifier = Modifier.testTag("btn_guru_audio_${lesson.id}")
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Headphones,
                                                        contentDescription = null,
                                                        modifier = Modifier.size(18.dp),
                                                        tint = MaterialTheme.colorScheme.primary
                                                    )
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Text("Guru Audio")
                                                }
                                            }

                                            if (lesson.youtubeUrl != null) {
                                                OutlinedButton(
                                                    onClick = { onWatchYouTubeForLesson(lesson) },
                                                    modifier = Modifier.testTag("btn_youtube_lesson_${lesson.id}")
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.OndemandVideo,
                                                        contentDescription = null,
                                                        modifier = Modifier.size(18.dp),
                                                        tint = MaterialTheme.colorScheme.secondary
                                                    )
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Text("Video")
                                                }
                                            }

                                            OutlinedButton(
                                                onClick = { onEditLesson(lesson) }
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Edit,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text("Edit")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // FAB to add custom lesson or category
        FloatingActionButton(
            onClick = onAddNewLesson,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 80.dp)
                .testTag("fab_add_lesson"),
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add custom lesson or category")
        }
    }

    // Delete Lesson Confirmation
    lessonToDelete?.let { lesson ->
        AlertDialog(
            onDismissRequest = { lessonToDelete = null },
            icon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            },
            title = { Text("Delete Lesson?") },
            text = { Text("Are you sure you want to delete \"${lesson.title}\"?") },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteLesson(lesson.id)
                        lessonToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { lessonToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Delete Category Confirmation
    categoryToDelete?.let { cat ->
        AlertDialog(
            onDismissRequest = { categoryToDelete = null },
            icon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            },
            title = { Text("Delete Category?") },
            text = {
                Text(
                    "Are you sure you want to delete category \"${cat.name}\"?\n\nAll lessons in this category will be removed. Any recordings in this category will be safely preserved in Sarali Varisais."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteCategory(cat.id)
                        categoryToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete Category")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { categoryToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}
