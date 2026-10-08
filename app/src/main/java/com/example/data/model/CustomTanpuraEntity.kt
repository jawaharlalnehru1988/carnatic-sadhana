package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "custom_tanpuras")
data class CustomTanpuraEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val pitchKattai: String = "1.5 Kattai (C#)",
    val filePath: String,
    val fileName: String,
    val durationMs: Long = 0L,
    val addedAt: Long = System.currentTimeMillis()
)
