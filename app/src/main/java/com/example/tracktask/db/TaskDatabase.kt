package com.example.tracktask.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    companion object{
        @Volatile
        private var INSTANCE: TaskDatabase?=null
        fun getDatabase(context: Context,scope: CoroutineScope): TaskDatabase {
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext, TaskDatabase::class.java,"TASK_DATABASE"
                ).allowMainThreadQueries().build()
                INSTANCE =instance
                instance
            }
        }
    }
}