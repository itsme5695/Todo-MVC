package com.example.todoappmvc.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoappmvc.controller.Controller
import com.example.todoappmvc.room.database.DatabaseHelper

class ViewmodelFactory(private var dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Controller::class.java)) {
            return Controller(dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}