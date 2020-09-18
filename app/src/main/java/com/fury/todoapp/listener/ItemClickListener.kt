package com.fury.todoapp.listener

interface ItemClickListener <T> {
    fun onItemClick(item : T, position : Int)
}