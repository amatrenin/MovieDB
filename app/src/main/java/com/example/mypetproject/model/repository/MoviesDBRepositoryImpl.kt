package com.example.mypetproject.model.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.mypetproject.model.apis.ApiInterface
import com.example.mypetproject.Constants
import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.data.MoviesVideos.MoviesVideos
import com.example.mypetproject.data.actors.MoviesActors
import com.example.mypetproject.data.actors.details.ActorsDetails
import com.example.mypetproject.data.review.review
import com.example.mypetproject.view.paging.MoviesPagingSource
import com.example.mypetproject.view.paging.NETWORK_PAGE_SIZE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call

@ExperimentalCoroutinesApi
class MoviesDBRepositoryImpl(private val apiInterface: ApiInterface) : MoviesDBRepository {

    override suspend fun getMovies(): LiveData<PagingData<MoviesDetails>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviesPagingSource(apiInterface)
            }, initialKey = 1
        ).liveData
    }

    override suspend fun getMoviesDetails(id: Int): Call<MoviesDetails> {
        return apiInterface.getMoviesDetails(id, Constants.API_KEY)
    }

    override suspend fun getActors(id: Int): Call<MoviesActors> {
        return apiInterface.getActors(id, Constants.API_KEY)
    }

    override suspend fun getActorsDetails(id: Int): Call<ActorsDetails> {
        return apiInterface.getActorsDetails(id, Constants.API_KEY)
    }

    override suspend fun getReview(id: Int): Call<review> {
        return apiInterface.getReviews(id, Constants.API_KEY)
    }

    override suspend fun getMoviesVideos(id: Int): Call<MoviesVideos> {
        return apiInterface.getVideos(id, Constants.API_KEY)
    }
}