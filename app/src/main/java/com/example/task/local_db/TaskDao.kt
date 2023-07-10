package com.example.task.local_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.task.models.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task WHERE date=:date")
    fun getTask(date: String) : LiveData<List<Task>>
}