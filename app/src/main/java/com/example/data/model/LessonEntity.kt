package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey
    val id: String,
    val categoryId: String,
    val title: String,
    val raga: String = "Mayamalavagowla",
    val tala: String = "Adi Tala",
    val arohana: String = "S R1 G3 M1 P D1 N3 Ṡ",
    val avarohana: String = "Ṡ N3 D1 P M1 G3 R1 S",
    val swaras: String = "",
    val sahitya: String = "",
    val meaning: String = "",
    val youtubeUrl: String? = null,
    val orderIndex: Int = 0,
    val isCustom: Boolean = false
)
