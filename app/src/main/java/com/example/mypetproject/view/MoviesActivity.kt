package com.example.mypetproject.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.view.adapters.CustomAdapter
import com.example.mypetproject.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MoviesActivity : AppCompatActivity(), CustomAdapter.ItemClickListener {

    private val mViewModel: MoviesViewModel by viewModels()
    private lateinit var mMoviesRecycler: RecyclerView
    private val mMoviesAdapter = CustomAdapter()
    private lateinit var mProgressDialog: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        initViews()
        initObservers()
        initAdapterClickListener()
        observeLoadingAndErrors()
        mViewModel.getMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorites -> {
                val intent = Intent(this, MoviesFavorite::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initObservers() {
        mViewModel.apply {
            movies.observe(this@MoviesActivity) {
                it?.let {
                    mMoviesAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    private fun initAdapterClickListener() {
        mMoviesAdapter.setOnMovieClickListener(
            object : CustomAdapter.ItemClickListener {
                override fun onItemClick(id: Int) {
                    val intent = Intent(baseContext, MoviesDetailsActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                }
            })
    }

    private fun observeLoadingAndErrors() {
        mViewModel.initLoadState(mMoviesAdapter)
        mViewModel.loading.observe(this) { isLoading ->
            mProgressDialog.isVisible = isLoading
        }

        mViewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, "Error progressBar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViews() {
        mMoviesRecycler = findViewById(R.id.recyclerview)
        mProgressDialog = findViewById(R.id.progressDialog)
        mMoviesRecycler.layoutManager = GridLayoutManager(this, 3)
        mMoviesRecycler.adapter = mMoviesAdapter
        val toolbar1 = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar1).apply {
            title = getString(R.string.toolbar_menu)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(baseContext, MoviesDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
