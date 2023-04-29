package com.example.mypetproject.room

import androidx.lifecycle.LiveData
import com.example.mypetproject.data.Details.MoviesDetails
import javax.inject.Inject

/**
 * Repository that handles logic with room data base
 */
class RoomRepositoryImpl @Inject constructor(private val toDoDao: ToDoDao) : RoomRepository {

    override fun getAllItem(): LiveData<List<MoviesDetails>> {
        return toDoDao.getAllItems()
    }

    override fun insertItem(moviesData: MoviesDetails) {
        toDoDao.insertItem(moviesData)
    }

    override fun deleteItem(moviesData: MoviesDetails) {
        toDoDao.deleteItem(moviesData)
    }

    companion object {
        const val DATABASE_NAME = "database-name"
    }
}