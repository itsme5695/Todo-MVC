package com.example.todoappmvc.room.database

import com.example.todoappmvc.room.entity.Category
import com.example.todoappmvc.room.entity.TaskData
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {
    fun addCategory(category: Category)

    fun deleteCategory(category: Category)

    fun updateCategory(category: Category)

    suspend fun getCategoryList(): List<Category>

    fun addTask(taskData: TaskData)

    fun deleteTask(taskData: TaskData)

    fun updateTask(taskData: TaskData)

    suspend fun getAllTaskList(): List<TaskData>

    fun getTaskByCategoryId(): List<TaskData>

    fun gettask(id: Int): Flow<List<TaskData>>

    fun getbb(id: Int): List<TaskData>
}