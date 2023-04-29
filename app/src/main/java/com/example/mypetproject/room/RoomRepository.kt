package com.example.mypetproject.room

import androidx.lifecycle.LiveData
import com.example.mypetproject.data.Details.MoviesDetails

interface RoomRepository {
    /**
     * Get todo item from room data base
     */
    fun getAllItem(): LiveData<List<MoviesDetails>>

    /**
     * add todo item from room data base
     */
    fun insertItem(moviesData: MoviesDetails)

    /**
     * delete todo item from room data base
     */
    fun deleteItem(moviesData: MoviesDetails)
}