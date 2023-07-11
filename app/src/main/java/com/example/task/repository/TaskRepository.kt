package com.example.task.repository

import androidx.lifecycle.LiveData
import com.example.task.local_db.TaskDatabase
import com.example.task.models.Task

class TaskRepository(
    private val db: TaskDatabase
) {

     suspend fun getTask(date: String) =
        db.taskDao().getTask(date)

     suspend fun upsertTask(task: Task) =
         db.taskDao().upsertTask(task)

     suspend fun deleteTask(task: Task) =
         db.taskDao().deleteTask(task)
}