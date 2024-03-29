package com.example.mypetproject.model.apis

import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.data.Movies.Movies
import com.example.mypetproject.data.MoviesVideos.MoviesVideos
import com.example.mypetproject.data.actors.MoviesActors
import com.example.mypetproject.data.actors.details.ActorsDetails
import com.example.mypetproject.data.review.review
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET("movie/popular")
   suspend fun getMovies(
        @Query("api_key") sort: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<Movies>

    @GET("movie/{movie_id}")
    fun getMoviesDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Call<MoviesDetails>

    @GET("movie/{movie_id}/credits")
    fun getActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Call<MoviesActors>

    @GET("person/{person_id}")
    fun getActorsDetails(
        @Path("person_id") person_Id: Int,
        @Query("api_key") apiKey: String,
    ): Call<ActorsDetails>

    @GET("movie/{movie_id}/reviews")
    fun getReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Call<review>

    @GET("movie/{movie_id}/videos")
    fun getVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Call<MoviesVideos>


    companion object {

        var BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}