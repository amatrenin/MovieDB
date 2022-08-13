package com.example.mypetproject.model.repository

import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.data.Movies.Movies
import com.example.mypetproject.data.MoviesVideos.MoviesVideos
import com.example.mypetproject.data.actors.MoviesActors
import com.example.mypetproject.data.actors.details.ActorsDetails
import com.example.mypetproject.data.review.review
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call

/**
 * Repository provaides information taken from MovieDB API
 */
@ExperimentalCoroutinesApi
interface MoviesDBRepository {

    /**
     * Returns list of popular [Movies]
     */
    suspend fun getMovies(): Call<Movies>

    /**
     * Returns information for a single movie by returning [MoviesDetails]
     * @param id - indification number of the needed movie
     */
    suspend fun getMoviesDetails(id: Int): Call<MoviesDetails>

    suspend fun getActors(id: Int): Call<MoviesActors>

    suspend fun getActorsDetails(id: Int): Call<ActorsDetails>

    suspend fun getReview(id: Int): Call<review>

    suspend fun getMoviesVideos(id: Int): Call<MoviesVideos>
}