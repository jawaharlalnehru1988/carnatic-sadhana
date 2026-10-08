package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "practice_logs")
data class PracticeLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dateStr: String, // e.g. "2026-10-03"
    val durationSeconds: Int,
    val lessonTitle: String,
    val categoryName: String,
    val speedKalam: Int = 1,
    val timestamp: Long = System.currentTimeMillis()
)
