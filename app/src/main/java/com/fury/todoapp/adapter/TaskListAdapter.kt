package com.fury.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fury.todoapp.databinding.ItemTaskBinding
import com.fury.todoapp.listener.ItemClickListener
import com.fury.todoapp.model.TaskModel

class TaskListAdapter(private val listener : ItemClickListener<TaskModel>?) : ListAdapter<TaskModel, TaskListAdapter.TaskHolder>(TaskDiffUtil()) {

    private class TaskDiffUtil : DiffUtil.ItemCallback<TaskModel>() {
        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem.title == newItem.title && oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem.equals(newItem)
        }
    }

    fun getTask(position: Int) : TaskModel{
        return getItem(position)
    }

    inner class TaskHolder(private val binding : ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : TaskModel){
            binding.model = item
            binding.executePendingBindings()
            itemView.setOnClickListener {
                listener?.onItemClick(item,adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int)  = holder.bind(getItem(position))
}