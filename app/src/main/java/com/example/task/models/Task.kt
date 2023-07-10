package com.example.task.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val date: String,
    val category: String
)
