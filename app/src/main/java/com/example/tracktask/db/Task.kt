package com.example.tracktask.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task_table")
data class Task(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Task_id")
    val id: Int =0,
    @ColumnInfo(name = "Task_desc")
    val task: String
)
