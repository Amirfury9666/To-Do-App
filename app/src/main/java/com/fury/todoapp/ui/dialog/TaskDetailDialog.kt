package com.fury.todoapp.ui.dialog

import android.content.Context
import android.content.Intent
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.fury.todoapp.R
import com.fury.todoapp.databinding.DialogTaskDetailBinding
import com.fury.todoapp.model.TaskModel
import com.fury.todoapp.ui.AddToDoActivity
import com.fury.todoapp.util.Constant

class TaskDetailDialog(context: Context,val task  : TaskModel) : AppCompatDialog(context){

    private lateinit var binding : DialogTaskDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_task_detail,null,false)
        setContentView(binding.root)
        val window = window
        window?.let {
            val drawable = InsetDrawable(ContextCompat.getDrawable(context,android.R.color.transparent),0)
            it.setBackgroundDrawable(drawable)
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
            it.setWindowAnimations(R.style.WindowAnimation)
        }

        binding.title.text = task.title
        binding.decription.text = task.description

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.editButton.setOnClickListener {
            val intent = Intent(context,AddToDoActivity::class.java)
            intent.putExtra(Constant.IS_FOR_EDIT,true)
            intent.putExtra(Constant.TASK_TITLE,task.title)
            intent.putExtra(Constant.TASK_DESCRIPTION,task.description)
            context.startActivity(intent)
            dismiss()
        }

    }
}