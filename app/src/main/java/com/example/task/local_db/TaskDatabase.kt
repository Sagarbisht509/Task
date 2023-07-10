package com.example.task.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task.models.Task

@Database(
    entities = [Task::class],
    version = 1
)
/*@TypeConverters(
    Convertors::class
)*/
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    // Creating singleton object of database
    companion object {

        @Volatile // if any changes are done INSTANCE variable it will reflected to all threads where it is used
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            if (INSTANCE == null) {
                synchronized(this) { // multiple threads can start creating object synchronize will handle this case by allowing one thread to create object at a time
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "taskDB"
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }
}