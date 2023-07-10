package com.example.task.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task.repository.Repo
import com.example.task.repository.TaskRepository

class HomeViewModelProviderFactory(
    private val repo: Repo,
    private val taskRepository: TaskRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repo, taskRepository) as T
    }
}