package com.example.mypetproject.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.view.adapters.CustomAdapterActors
import com.example.mypetproject.view.adapters.CustomAdapterActorsActivity
import com.example.mypetproject.viewmodel.MoviesViewModel

class MoviesActivityActors : AppCompatActivity(), CustomAdapterActors.ItemClickListenerActors {

    private val mViewModel: MoviesViewModel = MoviesViewModel()

    private lateinit var mMoviesActorsRecycler: RecyclerView
    private lateinit var mMoviesActorsAdapter: CustomAdapterActorsActivity

    private lateinit var mTitleActors: TextView
    private lateinit var mBiography: TextView
    private lateinit var mBannerActors: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_actors)
        val id = intent.getIntExtra("id", 0)
        initObservers()
        initViews()
        mViewModel.getMovieActors(id)
        Log.d("testLogs", "get actorsACtivity $id")
    }

    private fun initObservers() {
        mViewModel.apply {
            movieDetailsActors.observe(this@MoviesActivityActors) {
                mMoviesActorsAdapter = CustomAdapterActorsActivity(it, this@MoviesActivityActors)
                mMoviesActorsRecycler.adapter = mMoviesActorsAdapter



//                it.forEach { Cast ->
//                    setMovieInformationActors(Cast)
//                    Log.d("testLogs", "test getActorsActivity $Cast")
//                }

                }
            }
        }



//    private fun setMovieInformationActors(movieDetails: Cast?) {
//        mTitleActors.text = movieDetails?.name
//        mBiography.text = movieDetails?.character
//
//
//        Picasso.get()
//            .load("https://image.tmdb.org/t/p/w500" + movieDetails?.profile_path)
//            .into(mBannerActors)
//    }

    private fun initViews() {
        mMoviesActorsRecycler = findViewById(R.id.rcActorsActivity)
        mMoviesActorsRecycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClickActors(position: Int) {
        TODO("Not yet implemented")
    }
}