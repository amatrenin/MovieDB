package com.example.mypetproject.room

import com.example.mypetproject.data.Details.MoviesDetails
import javax.inject.Inject

/**
 * Repository that handles logic with room data base
 */
class RoomRepositoryImpl @Inject constructor(private val mTodoDao: ToDoDao) : RoomRepository {

    override fun getAllItem(): List<MoviesDetails> {
        return mTodoDao.getAllItems()
    }

    override fun insertItem(moviesData: MoviesDetails) {
        mTodoDao.insertItem(moviesData)
    }

    override fun deleteItem(moviesData: MoviesDetails) {
        mTodoDao.deleteItem(moviesData)
    }

    companion object {
        const val DATABASE_NAME = "database-name"
    }
}