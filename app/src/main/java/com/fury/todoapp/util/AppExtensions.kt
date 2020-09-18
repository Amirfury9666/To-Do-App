package com.fury.todoapp.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.toast(message : String?){
    Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show()
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}