package com.example.todoappmvc.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class MyToDoDb {
    @PrimaryKey(autoGenerate = true)
    var name: String? = null
    var category: String? = null
    var date: Date? = null
    var time: String? = null

    constructor(name: String?, category: String?, date: Date?, time: String?) {
        this.name = name
        this.category = category
        this.date = date
        this.time = time
    }
}