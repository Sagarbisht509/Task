package com.example.task.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.models.Task
import com.example.task.repository.Repo
import com.example.task.repository.TaskRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel(
    private val repo: Repo,
    private val taskRepository: TaskRepository
) : ViewModel() {

    val selectedDate = MutableLiveData(LocalDate.now())
    val weekList: MutableLiveData<ArrayList<LocalDate>> = MutableLiveData()

    private var _taskByDate: MutableLiveData<List<Task>> = MutableLiveData()
    val taskByDate: LiveData<List<Task>> get() = _taskByDate

    init {
        selectedDate.postValue(LocalDate.now())
        getData()
        getWeekData()
        getTaskList()
    }

    private fun getData() = viewModelScope.launch {
        val data = viewModelScope.async {
            taskRepository.getTask(selectedDate.value.toString())
        }

        _taskByDate.postValue(data.await())
    }

    fun getTaskList() = taskByDate


    private fun getWeekData() = viewModelScope.launch {
        weekList.postValue(repo.getDateOfThisWeek(selectedDate.value!!))
    }

    fun updateDate(date: LocalDate) {
        selectedDate.value = date
        getWeekData()
        getData()
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task)
    }

    fun upsertTask(task: Task) = viewModelScope.launch {
        taskRepository.upsertTask(task)
    }

}