package com.fury.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskModel(val title: String, val description: String){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}