package com.example.todoappmvc.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyToDoDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myToDoDao(): MyToDoDao

    companion object {
        private var instance: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "AppDataBase"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}