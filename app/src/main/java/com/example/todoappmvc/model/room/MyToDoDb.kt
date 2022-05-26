package com.example.todoappmvc.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "task_table")
data class MyToDoDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    var name: String,
    var category: String,
    var date: Date,
    var time: String

) : Serializable