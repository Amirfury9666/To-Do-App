package com.fury.todoapp.core

import android.app.Application
import com.fury.todoapp.core.repository.TaskRepository
import com.fury.todoapp.core.repository.TaskRepositoryImpl
import com.fury.todoapp.core.viewModelFactory.ViewModelFactory
import com.fury.todoapp.db.TaskDao
import com.fury.todoapp.db.TaskDataBase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ToDoApp : Application(),KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ToDoApp))
        bind() from singleton { TaskDataBase(instance()) }
        bind<TaskDao>() with singleton { TaskDataBase(instance()).taskDao() }
        bind<TaskRepository>() with singleton { TaskRepositoryImpl(instance()) }
        bind() from provider { ViewModelFactory(instance()) }
    }

}