package com.example.todoappmvc.model.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.todoappmvc.model.room.entity.Category

@Dao
interface CategoryDao {
    @Insert
    fun add(category: Category)


    @Update
    fun editDao(category: Category)

    @Delete
    fun deleteDao(category: Category)

}