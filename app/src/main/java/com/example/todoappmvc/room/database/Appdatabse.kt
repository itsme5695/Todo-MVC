package com.example.todoappmvc.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoappmvc.room.dao.TaskDao
import com.example.todoappmvc.room.entity.Category
import com.example.todoappmvc.room.entity.TaskData

@Database(entities = [TaskData::class, Category::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}