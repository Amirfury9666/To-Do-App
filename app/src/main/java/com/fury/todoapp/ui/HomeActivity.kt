package com.fury.todoapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.fury.todoapp.R
import com.fury.todoapp.adapter.TaskListAdapter
import com.fury.todoapp.core.BaseActivity
import com.fury.todoapp.core.viewModelFactory.ViewModelFactory
import com.fury.todoapp.databinding.ActivityHomeBinding
import com.fury.todoapp.listener.ItemClickListener
import com.fury.todoapp.model.TaskModel
import com.fury.todoapp.ui.dialog.TaskDetailDialog
import com.fury.todoapp.util.hide
import com.fury.todoapp.util.show
import com.fury.todoapp.util.toast
import com.fury.todoapp.viewModel.TaskViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : BaseActivity<ActivityHomeBinding>(), KodeinAware,
    ItemClickListener<TaskModel> {

    override val kodein: Kodein by kodein()

    private val adapter = TaskListAdapter(this)
    private val _viewModelFactory: ViewModelFactory by instance()
    private lateinit var taskViewModel: TaskViewModel
    override val layoutRes: Int
        get() = R.layout.activity_home

    override fun getToolbar(binding: ActivityHomeBinding): Toolbar? {
        return null
    }

    override fun onActivityReady(instanceState: Bundle?, binding: ActivityHomeBinding) {
        taskViewModel = ViewModelProviders.of(this, _viewModelFactory)[TaskViewModel::class.java]
        binding.taskRecycler.adapter = adapter

        binding.addTaskButton.setOnClickListener {
            startActivity(Intent(this, AddToDoActivity::class.java))
        }

        taskViewModel.getAllTasks().observe(this, Observer {
            if (it.isNullOrEmpty()){
                binding.noTaskText.show()
            }else{
                binding.noTaskText.hide()
                adapter.submitList(it)
            }
        })


        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                taskViewModel.deleteTask(adapter.getTask(viewHolder.adapterPosition))
                toast("Task Deleted")
            }

        }).attachToRecyclerView(binding.taskRecycler)
    }


    override fun onItemClick(item: TaskModel, position: Int) {
        TaskDetailDialog(this, item).show()
    }
}