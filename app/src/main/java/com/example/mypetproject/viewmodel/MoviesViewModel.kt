package com.example.mypetproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypetproject.data.Movies
import com.example.mypetproject.data.MoviesDetails
import com.example.mypetproject.data.MoviesVideos
import com.example.mypetproject.data.Result
import com.example.mypetproject.model.repository.MoviesDBRepository
import com.example.mypetproject.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {

    private val _movies = MutableLiveData<List<Result?>>()
    val movies: LiveData<List<Result?>> = _movies

    private val _movieDetails = MutableLiveData<MoviesDetails>()
    val movieDetails: LiveData<MoviesDetails> = _movieDetails

    private val _movieVideoYoutubeID = MutableLiveData<MoviesVideos>()
    val movieVideoYoutubeID = _movieVideoYoutubeID

     val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "On response Success ${call.toString()}")
                _movies.postValue(response?.body()?.results)
            }


            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("testLogs", "onFailure : ${t.message}")

            }
        })
    }

    fun getMovieDetails(id: Int) {
        val response = mMoviesRepository.getMoviesDetails(id)
        response.enqueue(object : Callback<MoviesDetails> {
            override fun onResponse(call: Call<MoviesDetails>, response: Response<MoviesDetails>) {

                Log.d("testLogs", "Onresponse Success ${call.toString()}")
                _movieDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<MoviesDetails>, t: Throwable) {
                Log.d("testLogs", "onFailure : ${t.message}")
            }
        })
    }

    fun getMoviesVideos(id: Int) {
        val response = mMoviesRepository.getMoviesVideos(id)
        response.enqueue(object : Callback<MoviesVideos> {
            override fun onResponse(call: Call<MoviesVideos>, response: Response<MoviesVideos>) {

                Log.d("testLogs", "Onresponse Success ${call.toString()}")
                _movieVideoYoutubeID.postValue(response.body())
            }
            override fun onFailure(call: Call<MoviesVideos>, t: Throwable) {
                Log.d("testLogs", "onFailure : ${t.message}")
            }
        })
    }


}

