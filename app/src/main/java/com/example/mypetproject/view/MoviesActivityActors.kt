package com.example.mypetproject.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.viewmodel.MoviesViewModel

class MoviesActivityActors : AppCompatActivity() {

    private val mViewModel: MoviesViewModel = MoviesViewModel()

    private lateinit var mMoviesActorsRecycler: RecyclerView
 //   private lateinit var mMoviesActorsAdapter: CustomAdapterActors

    private lateinit var mTitleActors: TextView
    private lateinit var mBiography: TextView
    private lateinit var mBannerActors: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_actors)
        val id = intent.getIntExtra("id", 0)
//        initObservers()
//        initViews()
    }

//    private fun initObservers() {
//        mViewModel.apply {
//            movieDetailsActors2.observe(this@MoviesActivityActors) {
//                mMoviesActorsAdapter = CustomAdapterActors(it, this@MoviesActivityActors)
//                mMoviesActorsRecycler.adapter = mMoviesActorsAdapter
//                }
//            }
//        }
//    }
//
//    private fun setMovieInformation(movieDetails: Cast?) {
//        mTitleActors.text = movieDetails?.name
//        mBiography.text = movieDetails?.character
//
//
//        Picasso.get()
//            .load("https://image.tmdb.org/t/p/w500" + movieDetails?.profile_path)
//            .into(mBannerActors)
//    }
//
//    private fun initViews() {
//        mTitleActors = findViewById(R.id.actors_details_title)
//        mBiography = findViewById(R.id.actors_details_dody_biography)
//        mBannerActors = findViewById(R.id.actors_details_image_banner)
//

    }
