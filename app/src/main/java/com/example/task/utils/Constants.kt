package com.example.task.utils

const val TABLE_NAME = "task"
const val DATABASE_NAME = "taskDB"

object Constants {
    fun getCategoryList(): List<String> {
        return listOf(
            "Marketing",
            "Meeting",
            "Dev",
            "Mobile",
            "UI Design",
            "HTML",
            "Graphic Design",
            "Android App",
            "IOS App",
            "Megento",
            "SEO"
        )
    }
}