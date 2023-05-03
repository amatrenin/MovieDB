package com.example.mypetproject

import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.model.repository.MoviesDBRepository
import com.example.mypetproject.room.RoomRepository
import com.example.mypetproject.viewmodel.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {

    private lateinit var subject : MoviesViewModel
    private val moviesDBRepositoryMock: MoviesDBRepository = mock()
    private val roomRepositoryMock: RoomRepository = mock()

    private val mockItemOne: MoviesDetails = MoviesDetails(
        backdrop_path = "backdrop_pathTestOne",
        id = 0,
        overview = "overviewTestOne",
        poster_path = "",
        release_date = "poster_pathTestOne",
        vote_average = 0.0,
        title = "titleTestOne",
        video = true)
    private val mockItemTwo: MoviesDetails = MoviesDetails(
        backdrop_path = "backdrop_pathTestOne",
        id = 0,
        overview = "overviewTestOne",
        poster_path = "",
        release_date = "poster_pathTestOne",
        vote_average = 0.0,
        title = "titleTestOne",
        video = true)

    private val mockList: List<MoviesDetails> = listOf(
        mockItemOne,
        mockItemTwo
    )

    @Before
    fun setup(){
        subject = MoviesViewModel(moviesDBRepositoryMock, roomRepositoryMock)
    }

    @Test
    fun getAllItem_success(){
        `when`(roomRepositoryMock.getAllItem()).thenReturn(mockList)
        subject.getAllItem()
        val expectedResult = subject.movies.value
        val firstItem = subject.movies.value
    }

    @Test
    fun insertItem_success(){
        `when`(roomRepositoryMock.getAllItem()).thenReturn(mockList)
        subject.insertItem(mockItemOne)

    }
}