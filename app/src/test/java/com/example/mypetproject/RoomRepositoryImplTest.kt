package com.example.mypetproject

import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.room.RoomRepositoryImpl
import com.example.mypetproject.room.ToDoDao
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

class RoomRepositoryImplTest {

    private lateinit var subject: RoomRepositoryImpl
    private val todoDaoMock: ToDoDao = mock()

    private val mockItemOne: MoviesDetails = MoviesDetails(
        backdrop_path = "backdrop_pathTest",
    id = 0,
    overview = "overviewTest",
    poster_path = "",
    release_date = "poster_pathTest",
    vote_average = 0.0,
    title = "titleTest",
    video = true)

    private val mockItemTwo: MoviesDetails = MoviesDetails(
        backdrop_path = "backdrop_pathTest",
        id = 1,
        overview = "overviewTest",
        poster_path = "",
        release_date = "poster_pathTest",
        vote_average = 1.0,
        title = "titleTest",
        video = true)

    private val mockList: List<MoviesDetails> = listOf(
        mockItemOne,
        mockItemTwo
    )

    @Before
    fun setup(){
        subject = RoomRepositoryImpl(todoDaoMock)
    }

    @Test
    fun getAllItem_success(){
        `when`(todoDaoMock.getAllItems()).thenReturn(mockList)
        subject.getAllItem()
        val result = subject.getAllItem()
        assertEquals(2, result.size)
    }

    @Test
    fun insertItem_success(){
        subject.insertItem(mockItemOne)
        verify(todoDaoMock).insertItem(mockItemOne)
    }

    @Test
    fun deleteItem_success(){
        subject.deleteItem(mockItemOne)
        verify(todoDaoMock).deleteItem(mockItemOne)
    }
}