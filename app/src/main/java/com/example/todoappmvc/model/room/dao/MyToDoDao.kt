package com.example.todoappmvc.model.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoappmvc.model.Todo
import com.example.todoappmvc.model.room.entity.MyToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface MyToDoDao {
    @Insert
    fun addTask(myToDoDb: MyToDo)

    @Update
    fun editTask(myToDoDb: MyToDo)

    @Query("select * from task_table where category=:category")
    fun getTaskByCategory(category:String): Flow<List<Todo>>

    @Query("Delete from task_table where id=:id")
    fun deleteTask(id:Int)
}