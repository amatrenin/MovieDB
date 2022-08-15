package com.example.mypetproject.model.repository

import com.example.mypetproject.ApiInterface
import com.example.mypetproject.Constants
import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.data.Movies.Movies
import com.example.mypetproject.data.MoviesVideos.MoviesVideos
import com.example.mypetproject.data.actors.MoviesActors
import com.example.mypetproject.data.actors.details.ActorsDetails
import com.example.mypetproject.data.review.review
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call

@ExperimentalCoroutinesApi
class MoviesDBRepositoryImpl(private val apiInterface: ApiInterface) : MoviesDBRepository {

    override suspend fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(Constants.API_KEY, "eu-US", 1)
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