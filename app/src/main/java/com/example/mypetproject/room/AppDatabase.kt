package com.example.mypetproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mypetproject.data.Details.MoviesDetails

@Database(entities = [MoviesDetails::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}