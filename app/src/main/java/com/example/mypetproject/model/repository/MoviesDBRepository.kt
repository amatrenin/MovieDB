package com.example.mypetproject.model.repository

import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.data.Movies.Movies
import com.example.mypetproject.data.MoviesVideos.MoviesVideos
import com.example.mypetproject.data.actors.MoviesActors
import com.example.mypetproject.data.review.review
import retrofit2.Call

/**
 * Repository provaides information taken from MovieDB API
 */
interface MoviesDBRepository {

    /**
     * Returns list of popular [Movies]
     */
    fun getMovies() : Call<Movies>

    /**
     * Returns information for a single movie by returning [MoviesDetails]
     * @param id - indification number of the needed movie
     */
    fun getMoviesDetails(id : Int) : Call<MoviesDetails>

    fun getActors(id : Int) : Call<MoviesActors>

    fun getReview(id : Int) : Call<review>

    fun getMoviesVideos(id: Int) : Call<MoviesVideos>
}