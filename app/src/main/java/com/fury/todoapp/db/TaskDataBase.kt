package com.fury.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fury.todoapp.model.TaskModel

@Database(entities = [TaskModel::class],version = 2,exportSchema = false)
abstract class TaskDataBase : RoomDatabase(){
    abstract fun taskDao() : TaskDao

    companion object{
        var instance : TaskDataBase? = null
        var LOCK = Any()


        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: buildDataBase(context)?.also {
                instance = it
            }
        }
        private fun buildDataBase(context: Context) : TaskDataBase {
            return Room.databaseBuilder(context.applicationContext,TaskDataBase::class.java,"ToDoApp.db").fallbackToDestructiveMigration().build()
        }
    }
}