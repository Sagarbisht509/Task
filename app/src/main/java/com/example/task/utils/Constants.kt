package com.example.task.utils

import com.example.task.R
import com.example.task.models.Task
import com.example.task.models.TaskModel

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

    fun getDummyDate(): List<TaskModel> {
        return listOf(
            TaskModel("Project Meeting", "", "9 am", "11 am", "", "Meeting", R.drawable.ic_description),
            TaskModel("Client Call", "", "12 am", "1 pm", "", "Call", R.drawable.ic_call),
            TaskModel("Team Meeting", "", "2 pm", "3 pm", "", "Meeting", R.drawable.ic_meeting),
            TaskModel("Project Meeting", "", "6 pm", "6:30 pm", "", "Meeting", R.drawable.ic_description),
            TaskModel("Project Review", "", "10 pm", "11 pm", "", "Meeting", R.drawable.ic_meeting),
        )
    }

}