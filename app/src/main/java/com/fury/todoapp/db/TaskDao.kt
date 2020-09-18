package com.fury.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fury.todoapp.model.TaskModel

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: TaskModel)

    @Update
    fun updateTask(task : TaskModel)

    @Delete
    fun deleteTask(task: TaskModel)

    @Query("DELETE FROM task_table")
    fun deleteAllTasks()

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<TaskModel>>
}