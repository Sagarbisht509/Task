package com.example.task.ui.addTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task.repository.TaskRepository

class AddTaskViewModelProviderFactory(
    private val taskRepository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTaskViewModel(taskRepository) as T
    }
}