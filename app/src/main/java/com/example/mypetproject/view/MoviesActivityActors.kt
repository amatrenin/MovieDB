package com.example.mypetproject.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.data.actors.details.ActorsDetails
import com.example.mypetproject.view.adapters.CustomAdapterActors
import com.example.mypetproject.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MoviesActivityActors : AppCompatActivity(), CustomAdapterActors.ItemClickListenerActors {

    private val mViewModel: MoviesViewModel by viewModels()

    private lateinit var mTitleActors: TextView
    private lateinit var mBiography: TextView
    private lateinit var mBannerActors: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_view_design_actors_activity)
        val id = intent.getIntExtra("id", 0)
        initObservers()
        initViews()
        mViewModel.getMovieActorsDetails(id)
    }

    private fun initObservers() {
        mViewModel.apply {
            movieDetailsActorsActivity.observe(this@MoviesActivityActors) {
                setMovieInformationActors(it)
            }
        }
    }


    private fun setMovieInformationActors(movieDetails: ActorsDetails?) {
        mTitleActors.text = movieDetails?.name
        mBiography.text = movieDetails?.biography


        Picasso.get()
            .load(getString(R.string.https_image_tmdb) + movieDetails?.profile_path)
            .into(mBannerActors)
    }

    private fun initViews() {
        mTitleActors = findViewById(R.id.actors_details_title)
        mBannerActors = findViewById(R.id.actors_details_image_banner)
        mBiography = findViewById(R.id.actors_details_dody_biography)
    }

    override fun onItemClickActors(position: Int) {
    }
}