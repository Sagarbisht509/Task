package com.example.task.models

data class TaskModel(
    val title: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val date: String,
    val category: String,
    val image: Int
)
