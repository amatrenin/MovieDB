package com.example.mypetproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.data.MoviesVideos.MoviesVideos
import com.example.mypetproject.data.MoviesVideos.ResultX
import com.example.mypetproject.data.actors.Cast
import com.example.mypetproject.data.actors.MoviesActors
import com.example.mypetproject.data.actors.details.ActorsDetails
import com.example.mypetproject.data.review.ResultReview
import com.example.mypetproject.data.review.review
import com.example.mypetproject.model.repository.MoviesDBRepository
import com.example.mypetproject.room.RoomRepository
import com.example.mypetproject.view.adapters.CustomAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class MoviesViewModel @Inject constructor(
    private val mMoviesDBRepository: MoviesDBRepository,
    private val mRoomRepository: RoomRepository
) :
    ViewModel() {

    private val _movies = MutableLiveData<PagingData<MoviesDetails>>()
    val movies: MutableLiveData<PagingData<MoviesDetails>> = _movies

    private val _movieDetails = MutableLiveData<MoviesDetails>()
    val movieDetails: LiveData<MoviesDetails> = _movieDetails

    private val _movieDetailsActors = MutableLiveData<List<Cast>>()
    val movieDetailsActors: LiveData<List<Cast>> = _movieDetailsActors

    private val _movieDetailsActorsActivity = MutableLiveData<ActorsDetails>()
    val movieDetailsActorsActivity: LiveData<ActorsDetails> = _movieDetailsActorsActivity

    private val _movieReview = MutableLiveData<List<ResultReview>>()
    val movieReview: LiveData<List<ResultReview>> = _movieReview

    private val _movieVideoYoutubeID = MutableLiveData<List<ResultX>>()
    val movieVideoYoutubeID: LiveData<List<ResultX>> = _movieVideoYoutubeID

    private val favoriteItemList: MutableLiveData<List<MoviesDetails>> = MutableLiveData()
    val favoriteItemResult: MutableLiveData<List<MoviesDetails>> = favoriteItemList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _loadingDetails = MutableLiveData<Boolean>()
    val loadingDetails: LiveData<Boolean> get() = _loadingDetails

    /**
     * Provides all data from room
     */
    fun getAllItem() {
        val result = mRoomRepository.getAllItem()
        favoriteItemList.postValue(result.asReversed())
    }

    /**
     * Insert Item in room data base
     * @param item - provides item that need to be insert in room data base
     */
    fun insertItem(moviesData: MoviesDetails) {
        favoriteItemList.value.let {
            favoriteItemList.postValue(it?.plus(moviesData))
            mRoomRepository.insertItem(moviesData)
        }
    }

    /**
     * Delete existing item from room database
     * @param item - provides item that need to be deleted from room data base
     */
    fun deleteItem(moviesData: MoviesDetails) {
        favoriteItemList.value.let {
            favoriteItemList.postValue(it?.minus(moviesData))
            mRoomRepository.deleteItem(moviesData)
        }
    }

    fun initLoadState(adapter: CustomAdapter) {
        adapter.addLoadStateListener { loadState ->
            // update loading state
            _loading.value = loadState.refresh is LoadState.Loading ||
                    loadState.append is LoadState.Loading
            // update error message
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                _errorMessage.value = it.error.toString()
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = mMoviesDBRepository.getMovies().cachedIn(viewModelScope)
            response.observeForever { response ->
                _movies.postValue(response)
            }
        }
    }

    fun getMovieDetails(id: Int) {
        _loadingDetails.value = true
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = mMoviesDBRepository.getMoviesDetails(id)
                response.enqueue(object : Callback<MoviesDetails> {
                    override fun onResponse(
                        call: Call<MoviesDetails>,
                        response: Response<MoviesDetails>,
                    ) {
                        _movieDetails.postValue(response.body())
                    }
                    override fun onFailure(call: Call<MoviesDetails>, t: Throwable) {
                    }
                })
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _loadingDetails.value = false
            }
        }
    }

    fun getMovieActors(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mMoviesDBRepository.getActors(id)
            response.enqueue(object : Callback<MoviesActors> {
                override fun onResponse(
                    call: Call<MoviesActors>,
                    response: Response<MoviesActors>,
                ) {
                    _movieDetailsActors.postValue(response.body()?.cast)
                }
                override fun onFailure(call: Call<MoviesActors>, t: Throwable) {
                }
            })
        }
    }

    fun getMovieActorsDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mMoviesDBRepository.getActorsDetails(id)
            response.enqueue(object : Callback<ActorsDetails> {
                override fun onResponse(
                    call: Call<ActorsDetails>,
                    response: Response<ActorsDetails>,
                ) {
                    _movieDetailsActorsActivity.postValue(response.body())
                }
                override fun onFailure(call: Call<ActorsDetails>, t: Throwable) {
                }
            })
        }
    }

    fun getReview(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mMoviesDBRepository.getReview(id)
            response.enqueue(object : Callback<review> {
                override fun onResponse(call: Call<review>, response: Response<review>) {
                    _movieReview.postValue(response.body()?.results)
                }
                override fun onFailure(call: Call<review>, t: Throwable) {
                }
            })
        }
    }

    fun getMoviesVideos(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mMoviesDBRepository.getMoviesVideos(id)
            response.enqueue(object : Callback<MoviesVideos> {
                override fun onResponse(
                    call: Call<MoviesVideos>,
                    response: Response<MoviesVideos>,
                ) {
                    _movieVideoYoutubeID.postValue(response.body()?.results)
                }
                override fun onFailure(call: Call<MoviesVideos>, t: Throwable) {
                }
            })
        }
    }
}

