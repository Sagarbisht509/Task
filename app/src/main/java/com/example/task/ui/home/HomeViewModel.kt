package com.example.task.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.models.Task
import com.example.task.models.TaskModel
import com.example.task.repository.Repo
import com.example.task.repository.TaskRepository
import com.example.task.utils.Constants
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel(
    private val repo: Repo,
    private val taskRepository: TaskRepository
) : ViewModel() {

    val localDate: MutableLiveData<LocalDate> = MutableLiveData()

    val list: MutableLiveData<ArrayList<Pair<String, String>>> = MutableLiveData()

    val taskList: MutableLiveData<List<TaskModel>> = MutableLiveData()

    init {
        localDate.value = LocalDate.now()
        getWeekData()
        getTaskList()
    }

    private fun getTaskList() {
       val response = Constants.getDummyDate()
        // val response = taskRepository.getTask(date: String)
        taskList.value = response
    }

    private fun getWeekData() = viewModelScope.launch {
        list.postValue(repo.getDateOfThisWeek(localDate.value!!))
    }

    fun updateDate(selectedDate: LocalDate) {
        localDate.value = selectedDate
        getWeekData()
        getTaskList()
    }

}