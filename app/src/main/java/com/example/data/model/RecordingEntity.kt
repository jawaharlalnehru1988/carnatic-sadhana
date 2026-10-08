package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recordings")
data class RecordingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val categoryId: String,
    val lessonId: String? = null,
    val filePath: String,
    val fileName: String,
    val durationMs: Long = 0L,
    val recordedAt: Long = System.currentTimeMillis(),
    val speedKalam: Int = 1, // 1st speed, 2nd speed, 3rd speed, 4th speed
    val practiceType: String = "Swaram", // Swaram, Akaram (Aa), Ukaram (Uu), Ikaram (Ii), Sahitya (Lyrics), All 4 Speeds Drill, Teacher Class
    val pitchKattai: String = "1 Kattai (C)",
    val tala: String = "Adi Tala",
    val notes: String = "",
    val source: String = "STUDENT_MIC", // STUDENT_MIC, TEACHER_RECORDING, IMPORTED_AUDIO, CONVERTED_MP3
    val rating: Int = 0,
    val isFavorite: Boolean = false
)
