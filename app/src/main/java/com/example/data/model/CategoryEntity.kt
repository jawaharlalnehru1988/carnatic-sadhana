package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val iconKey: String = "music",
    val orderIndex: Int = 0,
    val isCustom: Boolean = false
)
