package com.example.task.local_db

import androidx.room.TypeConverter
import java.util.Date

class Convertors {

    @TypeConverter
    fun convertDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertLongToDate(long: Long): Date {
        return Date(long)
    }

}