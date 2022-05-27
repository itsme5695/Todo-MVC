package com.example.todoappmvc.model.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "task_table")
data class MyToDo(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    var name: String,
    var category: String,
    var date: String,
    var time: String

) : Serializable