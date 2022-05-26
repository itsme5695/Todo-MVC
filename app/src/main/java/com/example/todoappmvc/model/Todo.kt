package com.example.todoappmvc.model

import java.util.*

data class Todo(
    var name:String,
    var category:String,
    var date:Date,
    var time:String
)