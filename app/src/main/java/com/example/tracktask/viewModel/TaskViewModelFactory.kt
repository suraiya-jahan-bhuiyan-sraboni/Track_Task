package com.example.tracktask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tracktask.repo.TaskRepo

class TaskViewModelFactory(private val repo: TaskRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknow ViewModel Class")
    }
}

