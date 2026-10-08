package com.example.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.CategoryEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLessonDialog(
    categories: List<CategoryEntity>,
    initialCategoryId: String?,
    onDismiss: () -> Unit,
    onAddLesson: (
        categoryId: String,
        title: String,
        raga: String,
        tala: String,
        swaras: String,
        sahitya: String,
        youtubeUrl: String?
    ) -> Unit,
    onAddCategory: (name: String, description: String) -> Unit
) {
    var isCreatingCategory by remember { mutableStateOf(false) }

    // For Lesson
    var selectedCategoryId by remember {
        mutableStateOf(initialCategoryId ?: categories.firstOrNull()?.id ?: "sarali")
    }
    var title by remember { mutableStateOf("") }
    var raga by remember { mutableStateOf("Mayamalavagowla") }
    var tala by remember { mutableStateOf("Adi Tala") }
    var swaras by remember { mutableStateOf("") }
    var sahitya by remember { mutableStateOf("") }
    var youtubeUrl by remember { mutableStateOf("") }

    // For Category
    var newCategoryName by remember { mutableStateOf("") }
    var newCategoryDesc by remember { mutableStateOf("") }

    var categoryDropdownExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        title = {
            Text(
                text = if (isCreatingCategory) "Add New Category" else "Add New Carnatic Lesson",
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
                // Toggle mode
                Row(modifier = Modifier.fillMaxWidth()) {
                    FilterChip(
                        selected = !isCreatingCategory,
                        onClick = { isCreatingCategory = false },
                        label = { Text("New Lesson") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    FilterChip(
                        selected = isCreatingCategory,
                        onClick = { isCreatingCategory = true },
                        label = { Text("New Category") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (isCreatingCategory) {
                    OutlinedTextField(
                        value = newCategoryName,
                        onValueChange = { newCategoryName = it },
                        label = { Text("Category Name") },
                        placeholder = { Text("e.g. Swarajathis, Varnams, Daily Warmup") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newCategoryDesc,
                        onValueChange = { newCategoryDesc = it },
                        label = { Text("Description") },
                        placeholder = { Text("e.g. Intermediate rhythmic and vocal agility studies") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2
                    )
                } else {
                    // Category selector
                    ExposedDropdownMenuBox(
                        expanded = categoryDropdownExpanded,
                        onExpandedChange = { categoryDropdownExpanded = !categoryDropdownExpanded }
                    ) {
                        OutlinedTextField(
                            value = categories.find { it.id == selectedCategoryId }?.name ?: "Category",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Category") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryDropdownExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = categoryDropdownExpanded,
                            onDismissRequest = { categoryDropdownExpanded = false }
                        ) {
                            categories.forEach { cat ->
                                DropdownMenuItem(
                                    text = { Text(cat.name) },
                                    onClick = {
                                        selectedCategoryId = cat.id
                                        categoryDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Lesson Title") },
                        placeholder = { Text("e.g. Sarali Varisai 15, Bilahari Gitam") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = raga,
                            onValueChange = { raga = it },
                            label = { Text("Ragam") },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = tala,
                            onValueChange = { tala = it },
                            label = { Text("Talam") },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = swaras,
                        onValueChange = { swaras = it },
                        label = { Text("Swara Notation") },
                        placeholder = { Text("S R G M | P D N Ṡ ||") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 4
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = sahitya,
                        onValueChange = { sahitya = it },
                        label = { Text("Sahitya / Lyrics (Optional)") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = youtubeUrl,
                        onValueChange = { youtubeUrl = it },
                        label = { Text("YouTube URL / ID (Optional)") },
                        placeholder = { Text("https://www.youtube.com/watch?v=...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (isCreatingCategory) {
                        if (newCategoryName.isNotBlank()) {
                            onAddCategory(newCategoryName, newCategoryDesc)
                            onDismiss()
                        }
                    } else {
                        if (title.isNotBlank()) {
                            onAddLesson(
                                selectedCategoryId,
                                title,
                                raga,
                                tala,
                                swaras,
                                sahitya,
                                youtubeUrl.ifBlank { null }
                            )
                            onDismiss()
                        }
                    }
                },
                enabled = if (isCreatingCategory) newCategoryName.isNotBlank() else title.isNotBlank(),
                modifier = Modifier.testTag("btn_confirm_add_lesson")
            ) {
                Text(if (isCreatingCategory) "Add Category" else "Add Lesson")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
