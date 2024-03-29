package com.example.mypetproject.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.view.adapters.CustomAdapterActors
import com.example.mypetproject.view.adapters.CustomAdapterReview
import com.example.mypetproject.view.adapters.TrailerAdapter
import com.example.mypetproject.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MoviesDetailsActivity() : AppCompatActivity(), CustomAdapterActors.ItemClickListenerActors,
    CustomAdapterReview.ItemClickListenerReview {

    private val mViewModel: MoviesViewModel by viewModels()

    private lateinit var mMoviesActorsRecycler: RecyclerView
    private lateinit var mMoviesActorsAdapter: CustomAdapterActors
    private lateinit var mReviewRecycler: RecyclerView
    private lateinit var mReviewAdapter: CustomAdapterReview
    private lateinit var mVideosRecycler: RecyclerView
    private lateinit var mTrailerAdapter: TrailerAdapter
    private lateinit var mTitle: TextView
    private lateinit var mReliaseDate: TextView
    private lateinit var mScore: TextView
    private lateinit var mOverview: TextView
    private lateinit var mBanner: ImageView
    private var mIsFavorite = false
    private lateinit var mFavoriteClick: ImageView
    private lateinit var mProgressDialog: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
        val id = intent.getIntExtra("id", 0)
        initViews()
        initObservers()
        initObserversActors()
        initObserversReview()
        initObserversVideos()
        observeLoadingAndErrors()
        mViewModel.getMovieDetails(id)
        mViewModel.getMoviesVideos(id)
        mViewModel.getMovieActors(id)
        mViewModel.getReview(id)
    }

    private fun observeLoadingAndErrors() {
        mViewModel.loadingDetails.observe(this) { isLoading ->
            mProgressDialog.isVisible = isLoading
            mViewModel.errorMessage.observe(this) { errorMessage ->
                errorMessage?.let {
                    Toast.makeText(this, "Error progressBar", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initObservers() {
        mViewModel.apply {
            movieDetails.observe(this@MoviesDetailsActivity) {
                setMovieInformation(it, context = baseContext)
            }
        }
    }

    private fun initObserversActors() {
        mViewModel.apply {
            movieDetailsActors.observe(this@MoviesDetailsActivity) {
                mMoviesActorsAdapter = CustomAdapterActors(it, this@MoviesDetailsActivity)
                mMoviesActorsRecycler.adapter = mMoviesActorsAdapter
            }
        }
    }

    private fun initObserversReview() {
        mViewModel.apply {
            movieReview.observe(this@MoviesDetailsActivity) {
                mReviewAdapter = CustomAdapterReview(it, this@MoviesDetailsActivity)
                mReviewRecycler.adapter = mReviewAdapter
            }
        }
    }

    private fun initObserversVideos() {
        mViewModel.apply {
            movieVideoYoutubeID.observe(this@MoviesDetailsActivity) {
                mTrailerAdapter = TrailerAdapter(it, this@MoviesDetailsActivity)
                mVideosRecycler.adapter = mTrailerAdapter
            }
        }
    }

    private fun setMovieInformation(movieDetails: MoviesDetails?, context: Context) {
        val valueBoolean = updateFavoriteButtonImage(movieDetails, context)
        mTitle.text = movieDetails?.title
        mReliaseDate.text = movieDetails?.release_date.toString()
        mScore.text = movieDetails?.vote_average.toString()
        mOverview.text = movieDetails?.overview
        Picasso.get()
            .load(getString(R.string.https_image_tmdb) + movieDetails?.backdrop_path)
            .into(mBanner)
        if (movieDetails != null) {
            updateFavoriteStatus(
                movieDetails = movieDetails,
                valueBoolean = valueBoolean,
                context = context
            )
        }
    }

    private fun updateFavoriteStatus(
        valueBoolean: Boolean,
        movieDetails: MoviesDetails,
        context: Context
    ) {
        mFavoriteClick.setOnClickListener {
            mIsFavorite = if (mIsFavorite == valueBoolean) {
                mFavoriteClick.setImageResource(R.drawable.ic_baseline_favorite_24)
                SaveShared.setFavorite(context, movieDetails.id.toString(), true)
                mViewModel.insertItem(moviesData = movieDetails)
                true
            } else {
                mFavoriteClick.setImageResource(R.drawable.ic_baseline_favorite_cansel)
                SaveShared.setFavorite(context, movieDetails.id.toString(), false)
                mViewModel.deleteItem(moviesData = movieDetails)
                false
            }
        }
    }

    private fun updateFavoriteButtonImage(movieDetails: MoviesDetails?, context: Context): Boolean {
        val valueBoolean =
            SaveShared.getFavorite(context, movieDetails?.id.toString())
        if (mIsFavorite != valueBoolean) {
            mFavoriteClick.setImageResource(R.drawable.ic_baseline_favorite_24)

        } else {
            mFavoriteClick.setImageResource(R.drawable.ic_baseline_favorite_cansel)
        }
        return valueBoolean
    }


    private fun initViews() {
        mFavoriteClick = findViewById(R.id.favoriteBtnAdd)
        mTitle = findViewById(R.id.movies_details_title)
        mReliaseDate = findViewById(R.id.movies_details_date)
        mScore = findViewById(R.id.movies_details_score)
        mOverview = findViewById(R.id.movies_details_body_overview)
        mBanner = findViewById(R.id.movies_details_image_banner)
        mProgressDialog = findViewById<ProgressBar>(R.id.progressBarDetails) as ProgressBar
        mMoviesActorsRecycler = findViewById(R.id.rcViewActors)
        mMoviesActorsRecycler.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
        mReviewRecycler = findViewById(R.id.rcReview)
        mReviewRecycler.layoutManager = LinearLayoutManager(this)

        mVideosRecycler = findViewById(R.id.rcVideosRecycler)
        mVideosRecycler.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    override fun onItemClickActors(position: Int) {
        val intent = Intent(this, MoviesActivityActors::class.java)
        intent.putExtra("id", position)
        startActivity(intent)
    }

    override fun onItemClickReview(position: Int) {
        val intent = Intent(this, MoviesActivityActors::class.java)
        intent.putExtra("id", position)
        startActivity(intent)
    }
}

@Suppress("DEPRECATION")
class SaveShared {
    companion object {
        fun setFavorite(context: Context?, key: String, value: Boolean) {
            val setFavoriteShared = PreferenceManager.getDefaultSharedPreferences(context)
            setFavoriteShared.edit().putBoolean(key, value).apply()
        }

        fun getFavorite(context: Context, key: String): Boolean {
            val getFavoriteShared = PreferenceManager.getDefaultSharedPreferences(context)
            return getFavoriteShared.getBoolean(key, false)
        }
    }
}
