package com.example.task.ui.addTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.models.Task
import com.example.task.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> = _date

    private val _startTime = MutableStateFlow("")
    val startTime: StateFlow<String> = _startTime

    private val _endTime = MutableStateFlow("")
    val endTime: StateFlow<String> = _endTime

    private val _category = MutableStateFlow("")
    val category: StateFlow<String> = _category

    fun onTitleTextChanged(title: String) {
        _title.value = title
    }

    fun onDescriptionTextChanged(description: String) {
        _description.value = description
    }

    fun onDateChanged(date: String) {
        _date.value = date
    }

    fun onStartTimeChanged(time: String) {
        _startTime.value = time
    }

    fun onEndTimeChanged(time: String) {
        _endTime.value = time
    }

    fun addNewTask(task: Task) = viewModelScope.launch {
        taskRepository.upsertTask(task)
    }

    fun validateUserInput(): Boolean {
        return (_title.value.isNotEmpty() && _description.value.isNotEmpty() && _date.value.isNotEmpty() && _startTime.value.isNotEmpty() && _endTime.value.isNotEmpty() && _category.value.isNotEmpty())
    }

    fun onCategoryChange(category: String) {
        _category.value = category
    }
}