package com.example.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioConvertImportDialog(
    categories: List<CategoryEntity>,
    lessons: List<LessonEntity>,
    initialCategoryId: String? = null,
    onDismiss: () -> Unit,
    onConfirmImport: (
        uri: Uri,
        title: String,
        categoryId: String,
        lessonId: String?,
        isTeacherRecording: Boolean,
        speedKalam: Int,
        pitchKattai: String,
        tala: String,
        notes: String
    ) -> Unit
) {
    var selectedUri by remember { mutableStateOf<Uri?>(null) }
    var title by remember { mutableStateOf("") }
    var selectedCategoryId by remember {
        mutableStateOf(initialCategoryId ?: categories.find { it.id == "teacher" }?.id ?: categories.firstOrNull()?.id ?: "teacher")
    }
    var selectedLessonId by remember { mutableStateOf<String?>(null) }
    var isTeacherRecording by remember { mutableStateOf(selectedCategoryId == "teacher") }
    var practiceType by remember { mutableStateOf("Swaram") }
    var speedKalam by remember { mutableStateOf(1) }
    var pitchKattai by remember { mutableStateOf("1 Kattai (C)") }
    var notes by remember { mutableStateOf("") }

    val filePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedUri = uri
            if (title.isBlank()) {
                val lastSegment = uri.lastPathSegment ?: "Imported Lesson"
                title = lastSegment.substringAfterLast('/').substringBeforeLast('.')
            }
        }
    }

    var categoryExpanded by remember { mutableStateOf(false) }
    var lessonExpanded by remember { mutableStateOf(false) }
    var pitchExpanded by remember { mutableStateOf(false) }

    val categoryLessons = remember(selectedCategoryId, lessons) {
        lessons.filter { it.categoryId == selectedCategoryId }
    }

    val pitchOptions = listOf(
        "1 Kattai (C)", "1.5 Kattai (C#)", "2 Kattai (D)", "2.5 Kattai (D#)",
        "3 Kattai (E)", "4 Kattai (F)", "4.5 Kattai (F#)", "5 Kattai (G)",
        "5.5 Kattai (G#)", "6 Kattai (A)", "6.5 Kattai (A#)", "7 Kattai (B)"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Default.AudioFile,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        title = {
            Text(
                text = "Import & Convert Audio",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Upload audio from native sound recorder, WhatsApp, or downloaded teacher recordings and standardize to MP3/M4A format.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))

                // File Selector Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { filePicker.launch("audio/*") }
                        .testTag("btn_select_audio_file"),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedUri != null) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.FileOpen,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (selectedUri != null) "File Selected" else "Tap to Choose Audio File",
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = selectedUri?.lastPathSegment ?: "Supports MP3, WAV, M4A, 3GP, AAC, OGG",
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Title Input
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Recording Title") },
                    placeholder = { Text("e.g. Guru's class - Sarali Varisai 4") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_import_title"),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Category Dropdown
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded }
                ) {
                    OutlinedTextField(
                        value = categories.find { it.id == selectedCategoryId }?.name ?: "Select Category",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Assign Category") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat.name) },
                                onClick = {
                                    selectedCategoryId = cat.id
                                    selectedLessonId = null
                                    if (cat.id == "teacher") isTeacherRecording = true
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }

                if (categoryLessons.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    ExposedDropdownMenuBox(
                        expanded = lessonExpanded,
                        onExpandedChange = { lessonExpanded = !lessonExpanded }
                    ) {
                        OutlinedTextField(
                            value = categoryLessons.find { it.id == selectedLessonId }?.title ?: "None (General Category Audio)",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Attach to Lesson (Optional)") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = lessonExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = lessonExpanded,
                            onDismissRequest = { lessonExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("None (General Category Audio)") },
                                onClick = {
                                    selectedLessonId = null
                                    lessonExpanded = false
                                }
                            )
                            categoryLessons.forEach { les ->
                                DropdownMenuItem(
                                    text = { Text(les.title) },
                                    onClick = {
                                        selectedLessonId = les.id
                                        lessonExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Pitch Kattai Dropdown
                ExposedDropdownMenuBox(
                    expanded = pitchExpanded,
                    onExpandedChange = { pitchExpanded = !pitchExpanded }
                ) {
                    OutlinedTextField(
                        value = pitchKattai,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Pitch / Shruti Kattai") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = pitchExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = pitchExpanded,
                        onDismissRequest = { pitchExpanded = false }
                    ) {
                        pitchOptions.forEach { p ->
                            DropdownMenuItem(
                                text = { Text(p) },
                                onClick = {
                                    pitchKattai = p
                                    pitchExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Practice Type Selection (Swaram, Akaram, Ukaram, All 4 Speeds)
                Text(
                    text = "Practice Style / Mode:",
                    style = MaterialTheme.typography.labelMedium
                )
                androidx.compose.foundation.lazy.LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val modes = listOf(
                        "Swaram" to "Swaram",
                        "Akaram (Aa)" to "Akaram",
                        "Ukaram (Uu)" to "Ukaram",
                        "Sahitya (Lyrics)" to "Sahitya",
                        "All 4 Speeds Drill" to "All 4 Speeds"
                    )
                    items(modes.size) { idx ->
                        val (k, label) = modes[idx]
                        FilterChip(
                            selected = practiceType == k,
                            onClick = { practiceType = k },
                            label = { Text(label) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Teacher recording toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Mark as Teacher's / Guru's Recording",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = isTeacherRecording,
                        onCheckedChange = { isTeacherRecording = it }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes / Teacher Instructions") },
                    placeholder = { Text("e.g. Focus on gamaka oscillations in Gandharam") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    selectedUri?.let { uri ->
                        onConfirmImport(
                            uri,
                            title.ifBlank { "Imported Audio" },
                            selectedCategoryId,
                            selectedLessonId,
                            isTeacherRecording,
                            speedKalam,
                            pitchKattai,
                            "Adi Tala",
                            notes
                        )
                    }
                },
                enabled = selectedUri != null,
                modifier = Modifier.testTag("btn_confirm_import_audio")
            ) {
                Text("Standardize & Save")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
