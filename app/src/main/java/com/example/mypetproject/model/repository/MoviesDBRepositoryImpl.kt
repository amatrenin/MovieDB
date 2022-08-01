package com.example.mypetproject.model.repository

import com.example.mypetproject.ApiInterface
import com.example.mypetproject.Constants
import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.data.Movies.Movies
import com.example.mypetproject.data.MoviesVideos.MoviesVideos
import com.example.mypetproject.data.actors.MoviesActors
import com.example.mypetproject.data.actors.details.ActorsDetails
import com.example.mypetproject.data.review.review
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {
    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(Constants.API_KEY, "eu-US", 1)
    }

    override fun getMoviesDetails(id: Int): Call<MoviesDetails> {
        return apiInterface.getMoviesDetails(id, Constants.API_KEY)
    }

    override fun getActors(id: Int): Call<MoviesActors> {
        return apiInterface.getActors(id, Constants.API_KEY)
    }

    override fun getActorsDetails(id: Int): Call<ActorsDetails> {
        return apiInterface.getActorsDetails(id, Constants.API_KEY)
    }

    override fun getReview(id: Int): Call<review> {
        return apiInterface.getReviews(id, Constants.API_KEY)
    }

    override fun getMoviesVideos(id: Int): Call<MoviesVideos> {
        return apiInterface.getVideos(id, Constants.API_KEY)
    }
}