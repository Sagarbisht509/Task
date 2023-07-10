package com.example.task.repository

import com.example.task.local_db.TaskDatabase
import com.example.task.models.Task

class TaskRepository(
    private val db: TaskDatabase
) {

     fun getTask(date: String) =
        db.taskDao().getTask(date)

     suspend fun insertTask(task: Task) =
         db.taskDao().insertTask(task)

     suspend fun deleteTask(task: Task) =
         db.taskDao().deleteTask(task)
}