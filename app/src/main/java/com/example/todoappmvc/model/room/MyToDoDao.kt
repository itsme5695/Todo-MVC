package com.example.todoappmvc.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyToDoDao {
    @Insert
    fun addTask(myToDoDb: MyToDoDb)

    @Update
    fun editTask(myToDoDb: MyToDoDb)

    /*@Query("select * from mytododb")
    fun get*/
}