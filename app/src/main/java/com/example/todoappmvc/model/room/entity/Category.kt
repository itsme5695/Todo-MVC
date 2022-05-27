package com.example.todoappmvc.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "category_name") var categoryName: String,
    @ColumnInfo(name = "category_color") var categoryColor: String,
    )