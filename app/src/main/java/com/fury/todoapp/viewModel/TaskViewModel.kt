package com.fury.todoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fury.todoapp.core.repository.TaskRepository
import com.fury.todoapp.model.TaskModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    var taskTitle = ""
    var taskDescription = ""

    fun insertTask(task: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertTask(task)
        }
    }

    fun updateTask(task : TaskModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteTask(task)
        }
    }

    fun deleteAllTasks() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAllTask()
        }
    }

    fun getAllTasks(): LiveData<List<TaskModel>> {
        return repository.getAllTask()
    }

}