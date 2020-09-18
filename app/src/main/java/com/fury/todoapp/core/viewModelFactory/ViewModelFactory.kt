package com.fury.todoapp.core.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fury.todoapp.core.repository.TaskRepository
import com.fury.todoapp.viewModel.TaskViewModel

class ViewModelFactory(private val repository: TaskRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(TaskViewModel::class.java) -> TaskViewModel(repository)
                else -> error("Invalid ViewModel")
            }
    } as T
}