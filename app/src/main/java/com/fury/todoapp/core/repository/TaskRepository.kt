package com.fury.todoapp.core.repository

import androidx.lifecycle.LiveData
import com.fury.todoapp.model.TaskModel

interface TaskRepository {

    suspend fun insertTask(task  : TaskModel)

    suspend fun updateTask(task: TaskModel)

    suspend fun deleteTask(task: TaskModel)

    suspend fun deleteAllTask()

    fun getAllTask() : LiveData<List<TaskModel>>
}