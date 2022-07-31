package com.example.mypetproject.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.view.adapters.CustomAdapterActors
import com.example.mypetproject.view.adapters.CustomAdapterReview
import com.example.mypetproject.view.adapters.TrailerAdapter
import com.example.mypetproject.viewmodel.MoviesViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.squareup.picasso.Picasso


class MoviesDetailsActivity : AppCompatActivity(), CustomAdapterActors.ItemClickListenerActors,
    CustomAdapterReview.ItemClickListenerReview {

    private val mViewModel: MoviesViewModel = MoviesViewModel()


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
    private lateinit var mYouTubePlayer: YouTubePlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

        val id = intent.getIntExtra("id", 0)
        initViews()
        initObservers()
        initObserversActors()
        initObserversReview()
        initObserversVideos()
        mViewModel.getMovieDetails(id)
        mViewModel.getMoviesVideos(id)
        mViewModel.getMovieActors(id)
        mViewModel.getReview(id)
    }

    private fun initObservers() {
        mViewModel.apply {
            movieDetails.observe(this@MoviesDetailsActivity) {
                setMovieInformation(it)
            }
        }
    }

    private fun initObserversActors() {
        mViewModel.apply {
            movieDetailsActors.observe(this@MoviesDetailsActivity) {
                mMoviesActorsAdapter = CustomAdapterActors(it, this@MoviesDetailsActivity)
                mMoviesActorsRecycler.adapter = mMoviesActorsAdapter

//                it.cast.forEach { cast ->
//                    (cast)
//                }
                Log.d("testLogs", "observe ${it}")
            }
        }
    }

    private fun initObserversReview() {
        mViewModel.apply {
            movieReview.observe(this@MoviesDetailsActivity) {
                mReviewAdapter = CustomAdapterReview(it, this@MoviesDetailsActivity)
                mReviewRecycler.adapter = mReviewAdapter

//                it.cast.forEach { cast ->
//                    (cast)
//                }
                Log.d("testLogs", "observe ${it}")
            }
        }
    }

    private fun initObserversVideos() {
        mViewModel.apply {
            movieVideoYoutubeID.observe(this@MoviesDetailsActivity) {
                mTrailerAdapter = TrailerAdapter(it, this@MoviesDetailsActivity)
                mVideosRecycler.adapter = mTrailerAdapter


                Log.d("testLogs", "videos observe ${it}")
            }
        }
    }

    private fun setMovieInformation(movieDetails: MoviesDetails?) {
        mTitle.text = movieDetails?.title
        mReliaseDate.text = movieDetails?.release_date.toString()
        mScore.text = movieDetails?.vote_average.toString()
        mOverview.text = movieDetails?.overview


        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500" + movieDetails?.backdrop_path)
            .into(mBanner)
    }


    private fun initViews() {
        mTitle = findViewById(R.id.movies_details_title)
        mReliaseDate = findViewById(R.id.movies_details_date)
        mScore = findViewById(R.id.movies_details_score)
        mOverview = findViewById(R.id.movies_details_body_overview)
        mBanner = findViewById(R.id.movies_details_image_banner)

        mMoviesActorsRecycler = findViewById(R.id.rcViewActors)
        mMoviesActorsRecycler.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
//
        mReviewRecycler = findViewById(R.id.rcReview)
        mReviewRecycler.layoutManager = LinearLayoutManager(this)

        mVideosRecycler = findViewById(R.id.rcVideosRecycler)
        mVideosRecycler.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onItemClickActors(id: Int) {
        val intent = Intent(this, MoviesActivityActors::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onItemClickReview(id: Int) {
        val intent = Intent(this, MoviesActivityActors::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
