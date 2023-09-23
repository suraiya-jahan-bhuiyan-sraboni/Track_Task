package com.example.tracktask.repo

import androidx.annotation.WorkerThread
import com.example.tracktask.db.Task
import com.example.tracktask.db.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepo(private val taskDao: TaskDao) {
    val allTasks:Flow<List<Task>> = taskDao.getAllTask()
    @WorkerThread
    suspend fun insert(task: Task){
        taskDao.insert(task)
    }
    @WorkerThread
    suspend fun update(task: Task){
        taskDao.update(task)
    }
    @WorkerThread
    suspend fun delete(task: Task){
        taskDao.delete(task)
    }
}