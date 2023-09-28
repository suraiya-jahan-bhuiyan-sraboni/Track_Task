package com.example.tracktask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tracktask.db.Task
import com.example.tracktask.repo.TaskRepo
import kotlinx.coroutines.launch

class TaskViewModel(private val repo: TaskRepo):ViewModel() {
    val allTasks:LiveData<List<Task>> = repo.allTasks.asLiveData()
    fun deleteAllTasks()=viewModelScope.launch {
            repo.deleteAllTasks()
        }

    fun insert(task: Task)=viewModelScope.launch {
        repo.insert(task)
    }
    fun update(task: Task)=viewModelScope.launch {
        repo.update(task)
    }
    fun delete(task: Task)=viewModelScope.launch {
        repo.delete(task)
    }
}