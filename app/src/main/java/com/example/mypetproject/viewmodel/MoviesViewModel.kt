package com.example.mypetproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.data.Movies.Movies
import com.example.mypetproject.data.Movies.Result
import com.example.mypetproject.data.MoviesVideos.MoviesVideos
import com.example.mypetproject.data.MoviesVideos.ResultX
import com.example.mypetproject.data.actors.Cast
import com.example.mypetproject.data.actors.MoviesActors
import com.example.mypetproject.data.review.ResultReview
import com.example.mypetproject.data.review.review
import com.example.mypetproject.model.repository.MoviesDBRepository
import com.example.mypetproject.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Result?>>()
    val movies: LiveData<List<Result?>> = _movies

    private val _movieDetails = MutableLiveData<MoviesDetails>()
    val movieDetails: LiveData<MoviesDetails> = _movieDetails

    private val _movieDetailsActors = MutableLiveData<List<Cast>>()
    val movieDetailsActors: LiveData<List<Cast>> = _movieDetailsActors

    private val _movieReview = MutableLiveData<List<ResultReview>>()
    val movieReview: LiveData<List<ResultReview>> = _movieReview

    private val _movieVideoYoutubeID = MutableLiveData<List<ResultX>>()
    val movieVideoYoutubeID: LiveData<List<ResultX>> = _movieVideoYoutubeID

    val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "On response Success Movies : ${call.toString()}")
                _movies.postValue(response?.body()?.results)
            }


            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("testLogs", "onFailure Movies : ${t.message}")

            }
        })
    }

    fun getMovieDetails(id: Int) {
        val response = mMoviesRepository.getMoviesDetails(id)
        response.enqueue(object : Callback<MoviesDetails> {
            override fun onResponse(call: Call<MoviesDetails>, response: Response<MoviesDetails>) {

                Log.d("testLogs", "Onresponse Success getMovieDetails${call.toString()}")
                _movieDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<MoviesDetails>, t: Throwable) {
                Log.d("testLogs", "onFailure getMovieDetails : ${t.message}")
            }
        })
    }

    fun getMovieActors(id: Int) {
        val response = mMoviesRepository.getActors(id)
        response.enqueue(object : Callback<MoviesActors> {
            override fun onResponse(call: Call<MoviesActors>, response: Response<MoviesActors>) {

                Log.d("testLogs", "Onresponse Success getActors${call.toString()}")
                _movieDetailsActors.postValue(response?.body()?.cast)
            }

            override fun onFailure(call: Call<MoviesActors>, t: Throwable) {
                Log.d("testLogs", "onFailure getActors : ${t.message}")
            }
        })
    }

    fun getReview(id: Int) {
        val response = mMoviesRepository.getReview(id)
        response.enqueue(object : Callback<review> {
            override fun onResponse(call: Call<review>, response: Response<review>) {

                Log.d("testLogs", "Onresponse Success getReview${call.toString()}")
                _movieReview.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<review>, t: Throwable) {
                Log.d("testLogs", "onFailure getActors : ${t.message}")
            }
        })
    }

    fun getMoviesVideos(id: Int) {
        val response = mMoviesRepository.getMoviesVideos(id)
        response.enqueue(object : Callback<MoviesVideos> {
            override fun onResponse(call: Call<MoviesVideos>, response: Response<MoviesVideos>) {

                Log.d("testLogs", "Onresponse Success getVideo : ${call.toString()}")
                _movieVideoYoutubeID.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<MoviesVideos>, t: Throwable) {
                Log.d("testLogs", "onFailure getVideo : ${t.message}")
            }
        })
    }


}

