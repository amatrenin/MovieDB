package com.example.mypetproject.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.viewmodel.MoviesViewModel
import com.example.mypetproject.view.adapters.CustomAdapter
import com.example.mypetproject.view.adapters.CustomAdapterFavorites
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@OptIn(ExperimentalCoroutinesApi::class)
class MoviesFavorite() : AppCompatActivity(), CustomAdapter.ItemClickListener {

    private val mViewModel: MoviesViewModel by viewModels()
    private lateinit var mMoviesRecycler: RecyclerView
    private val mAdapterFavorite by lazy { CustomAdapterFavorites() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_movies_screen)
        initViews()
        initObservers()
        initAdapterClickListener()
        mViewModel.getAllItem()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        mViewModel.apply {
            favoriteItemResult.observe(this@MoviesFavorite, Observer { list ->
                if (list != null) {
                    mAdapterFavorite.mList = list
                    mAdapterFavorite.notifyDataSetChanged()
                }
            })
            mViewModel.getAllItem()
        }
    }

    private fun initViews() {
        mMoviesRecycler = findViewById<RecyclerView>(R.id.rcView_favorites)
        mMoviesRecycler.layoutManager = GridLayoutManager(this, 3)
        mMoviesRecycler.adapter = mAdapterFavorite
    }

    private fun initAdapterClickListener() {
        mAdapterFavorite.setOnMovieClickListener(
            object : CustomAdapterFavorites.onClickItemFav {
                override fun onClikedItemFavorite(id: Int) {
                    val intent = Intent(baseContext, MoviesDetailsActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                }
            })
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(this, MoviesDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
