package com.example.mypetproject.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mypetproject.R
import com.example.mypetproject.data.Cast
import com.example.mypetproject.data.MoviesDetails
import com.example.mypetproject.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso


class MoviesDetailsActivity : AppCompatActivity() {

    private val mViewModel: MoviesViewModel = MoviesViewModel()

    private lateinit var mTitle: TextView
    private lateinit var mTitleActors: TextView
    private lateinit var mReliaseDate: TextView
    private lateinit var mScore: TextView
    private lateinit var mOverview: TextView
    private lateinit var mBanner: ImageView
    private lateinit var mBannerActors: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

        val id = intent.getIntExtra("id", 0)
        initViews()
        initObservers()
        initObserversActors()
        mViewModel.getMovieDetails(id)
        mViewModel.getMoviesVideos(id)
        mViewModel.getMovieActors(id)

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
                it.cast.forEach { cast ->
                    setMovieInformationActors(cast)
                }
                Log.d("testLogs", "observe ${it}")
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

    private fun setMovieInformationActors(movieDetailsActors: Cast?) {
        mTitleActors.text = movieDetailsActors?.name

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500" + movieDetailsActors?.profile_path)
            .into(mBannerActors)
    }

    private fun initViews() {
        mTitle = findViewById(R.id.movies_details_title)
        mTitleActors = findViewById(R.id.movies_details_actor_name)
        mReliaseDate = findViewById(R.id.movies_details_date)
        mScore = findViewById(R.id.movies_details_score)
        mOverview = findViewById(R.id.movies_details_body_overview)
        mBanner = findViewById(R.id.movies_details_image_banner)
        mBannerActors = findViewById(R.id.movies_details_actors_banner)

    }
}
