package com.fury.todoapp.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.fury.todoapp.R
import com.fury.todoapp.core.BaseActivity
import com.fury.todoapp.core.viewModelFactory.ViewModelFactory
import com.fury.todoapp.databinding.ActivityAddToDoBinding
import com.fury.todoapp.model.TaskModel
import com.fury.todoapp.util.Constant
import com.fury.todoapp.util.toast
import com.fury.todoapp.viewModel.TaskViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddToDoActivity : BaseActivity<ActivityAddToDoBinding>(),KodeinAware {
    override val kodein: Kodein by kodein()

    private var isForEdit = false
    private val _viewModelFactory : ViewModelFactory by instance()
    private lateinit var taskViewModel : TaskViewModel
    override val layoutRes: Int
        get() = R.layout.activity_add_to_do

    override fun getToolbar(binding: ActivityAddToDoBinding): Toolbar? {return null}

    override fun onActivityReady(instanceState: Bundle?, binding: ActivityAddToDoBinding) {
        taskViewModel = ViewModelProviders.of(this,_viewModelFactory)[TaskViewModel::class.java]


        intent.hasExtra(Constant.IS_FOR_EDIT)?.let {
            if (it){
                val title = intent?.getStringExtra(Constant.TASK_TITLE)
                val desc = intent?.getStringExtra(Constant.TASK_DESCRIPTION)
                binding.taskTitle.setText(title)
                binding.taskDescription.setText(desc)
                isForEdit = true
            }
        }


        binding.backButton.setOnClickListener {
            finish()
        }

        binding.saveTaskButton.setOnClickListener {
            val title = binding.taskTitle.text.toString()
            val desc = binding.taskDescription.text.toString()

            if (title.isNullOrEmpty()){
                toast("Please Enter A Task Title")
                return@setOnClickListener
            }
            if (desc.isNullOrEmpty()){
                toast("Please Add Task Description")
                return@setOnClickListener
            }
            val task = TaskModel(title,desc)

            if (!isForEdit){
                taskViewModel.insertTask(task)
            }else{
                taskViewModel.updateTask(task)
            }
            finish()
        }
    }

}