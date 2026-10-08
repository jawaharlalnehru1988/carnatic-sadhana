package com.example.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Transform
import androidx.compose.material.icons.filled.UploadFile
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.audio.AudioConverterUtil
import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity

data class CuratedTutorial(
    val title: String,
    val categoryName: String,
    val videoId: String,
    val description: String
)

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YouTubeReferenceScreen(
    categories: List<CategoryEntity>,
    lessons: List<LessonEntity>,
    onAttachVideoToLesson: (lessonId: String, youtubeUrl: String) -> Unit,
    onOpenAudioConverter: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var inputUrl by remember { mutableStateOf("") }
    var currentVideoId by remember { mutableStateOf("kYJv8ZqjS_k") } // Default Sarali Varisai guide
    var currentVideoTitle by remember { mutableStateOf("Carnatic Sarali Varisai - 3 Speeds Tutorial") }

    var selectedLessonIdToAttach by remember { mutableStateOf<String?>(null) }
    var showAttachDialog by remember { mutableStateOf(false) }

    val curatedList = remember {
        listOf(
            CuratedTutorial(
                title = "Sarali Varisai 1 to 14 in 3 Kalams",
                categoryName = "Sarali Varisais",
                videoId = "kYJv8ZqjS_k",
                description = "Mayamalavagowla 1st, 2nd, and 3rd speed continuous laya demonstration."
            ),
            CuratedTutorial(
                title = "Jantai Varisai & Sphuritam Technique",
                categoryName = "Jantai Varisais",
                videoId = "8V-d1m2N3x4",
                description = "Mastering forceful twin swara pulse from the naval without throat strain."
            ),
            CuratedTutorial(
                title = "Sapta Tala Alankarams in 3 Kalams",
                categoryName = "Alankarams",
                videoId = "q6g4tQz8m7E",
                description = "Chatusra Dhruva, Rupaka, and Triputa Tala rhythm structures."
            ),
            CuratedTutorial(
                title = "Sri Gananatha (Malahari Gitam)",
                categoryName = "Gitams",
                videoId = "VzE3O35jJb4",
                description = "Purandara Dasa's Pillari Gitam with Swara and Sahitya alignment."
            ),
            CuratedTutorial(
                title = "Varaveena Mridupani (Mohanam Gitam)",
                categoryName = "Gitams",
                videoId = "VaraveenaMohanam",
                description = "Obeisance to Goddess Saraswati in Audava raga Mohanam."
            ),
            CuratedTutorial(
                title = "Ninnukori Varnam (Mohanam)",
                categoryName = "Varnams",
                videoId = "NinnukoriMohanam",
                description = "Mukthayi swaras and charanam sahitya with proper gamakas."
            )
        )
    }

    val htmlData = remember(currentVideoId) {
        """
        <!DOCTYPE html>
        <html>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
            <style>
                body { margin: 0; padding: 0; background-color: #000; }
                .video-container { position: relative; width: 100vw; height: 100vh; }
                iframe { position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none; }
            </style>
        </head>
        <body>
            <div class="video-container">
                <iframe
                    src="https://www.youtube.com/embed/$currentVideoId?playsinline=1&rel=0&modestbranding=1"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen>
                </iframe>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))

            // URL input & Embedder header
            Text(
                text = "YouTube Reference & MP3 Companion",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Embed lesson demonstrations or extract audio tracks for your sadhana",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Input Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputUrl,
                    onValueChange = { inputUrl = it },
                    placeholder = { Text("Paste YouTube URL or Video ID...") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.OndemandVideo, contentDescription = null)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("input_youtube_url"),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        val parsed = AudioConverterUtil.extractYouTubeVideoId(inputUrl)
                        if (parsed != null) {
                            currentVideoId = parsed
                            currentVideoTitle = "Custom Reference ($parsed)"
                        }
                    },
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.testTag("btn_load_youtube_video")
                ) {
                    Text("Load")
                }
            }
        }

        // Active Embedded Video Player Box
        item {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("embedded_youtube_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = currentVideoTitle,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                            Text(
                                text = "ID: $currentVideoId",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=$currentVideoId")
                                )
                                context.startActivity(intent)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.OpenInNew,
                                contentDescription = "Open in YouTube"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // WebView YouTube Embed
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        AndroidView(
                            factory = { ctx ->
                                WebView(ctx).apply {
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    webChromeClient = WebChromeClient()
                                    webViewClient = WebViewClient()
                                    settings.apply {
                                        javaScriptEnabled = true
                                        domStorageEnabled = true
                                        mediaPlaybackRequiresUserGesture = false
                                        loadWithOverviewMode = true
                                        useWideViewPort = true
                                    }
                                    loadDataWithBaseURL(
                                        "https://www.youtube.com",
                                        htmlData,
                                        "text/html",
                                        "UTF-8",
                                        null
                                    )
                                }
                            },
                            update = { webView ->
                                webView.loadDataWithBaseURL(
                                    "https://www.youtube.com",
                                    htmlData,
                                    "text/html",
                                    "UTF-8",
                                    null
                                )
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Attach to Lesson Button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        OutlinedButton(
                            onClick = { showAttachDialog = true },
                            modifier = Modifier.testTag("btn_attach_video_to_lesson")
                        ) {
                            Icon(
                                imageVector = Icons.Default.BookmarkAdd,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Attach to a Lesson")
                        }
                    }
                }
            }
        }

        // MP3 Audio Conversion Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Transform,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Audio & Video to MP3 Converter",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Recorded video or audio files from your teacher's class or downloaded lessons can be extracted directly into high quality MP3/M4A format inside this app.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.85f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = onOpenAudioConverter,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier.testTag("btn_launch_audio_converter")
                    ) {
                        Icon(imageVector = Icons.Default.UploadFile, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Import & Convert Audio File")
                    }
                }
            }
        }

        // Curated Tutorials Header
        item {
            Text(
                text = "Curated Carnatic Masterclasses:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        items(curatedList) { tutorial ->
            val isCurrent = currentVideoId == tutorial.videoId
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        currentVideoId = tutorial.videoId
                        currentVideoTitle = tutorial.title
                    },
                colors = CardDefaults.cardColors(
                    containerColor = if (isCurrent) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .padding(10.dp)
                                .size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = tutorial.title,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = tutorial.categoryName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = tutorial.description,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 2
                        )
                    }
                }
            }
        }
    }

    // Attach to Lesson Dialog
    if (showAttachDialog) {
        var attachCategoryExpanded by remember { mutableStateOf(false) }
        var attachCategoryId by remember { mutableStateOf(categories.firstOrNull()?.id ?: "sarali") }
        val categoryLessons = remember(attachCategoryId, lessons) {
            lessons.filter { it.categoryId == attachCategoryId }
        }

        AlertDialog(
            onDismissRequest = { showAttachDialog = false },
            title = { Text("Bookmark Reference to Lesson", fontWeight = FontWeight.Bold) },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Choose a lesson to link this YouTube video ($currentVideoId):",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ExposedDropdownMenuBox(
                        expanded = attachCategoryExpanded,
                        onExpandedChange = { attachCategoryExpanded = !attachCategoryExpanded }
                    ) {
                        OutlinedTextField(
                            value = categories.find { it.id == attachCategoryId }?.name ?: "Category",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Category") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = attachCategoryExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = attachCategoryExpanded,
                            onDismissRequest = { attachCategoryExpanded = false }
                        ) {
                            categories.forEach { cat ->
                                DropdownMenuItem(
                                    text = { Text(cat.name) },
                                    onClick = {
                                        attachCategoryId = cat.id
                                        selectedLessonIdToAttach = null
                                        attachCategoryExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(modifier = Modifier.height(160.dp)) {
                        items(categoryLessons) { les ->
                            val isSel = selectedLessonIdToAttach == les.id
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                                    .clickable { selectedLessonIdToAttach = les.id },
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSel) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                            ) {
                                Row(
                                    modifier = Modifier.padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = les.title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (isSel) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        selectedLessonIdToAttach?.let { lesId ->
                            onAttachVideoToLesson(lesId, "https://www.youtube.com/watch?v=$currentVideoId")
                        }
                        showAttachDialog = false
                    },
                    enabled = selectedLessonIdToAttach != null
                ) {
                    Text("Bookmark")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showAttachDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
