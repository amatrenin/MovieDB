package com.example.mypetproject.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mypetproject.data.Details.MoviesDetails

@Dao
interface ToDoDao {
    @Query("SELECT * FROM movie_table")
    fun getAllItems(): List<MoviesDetails>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(moviesData: MoviesDetails)

    @Delete
    fun deleteItem(moviesData: MoviesDetails)
}