package com.fury.todoapp.core.repository

import androidx.lifecycle.LiveData
import com.fury.todoapp.db.TaskDao
import com.fury.todoapp.model.TaskModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepositoryImpl(private val taskDao : TaskDao) : TaskRepository {
    override suspend fun insertTask(task: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.insertTask(task)
        }
    }

    override suspend fun updateTask(task: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.updateTask(task)
        }
    }

    override suspend fun deleteTask(task: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.deleteTask(task)
        }
    }

    override suspend fun deleteAllTask() {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.deleteAllTasks()
        }
    }

    override fun getAllTask(): LiveData<List<TaskModel>> {
        return taskDao.getAllTasks()
    }
}