package com.example.mypetproject.data.Movies

import com.example.mypetproject.data.Details.MoviesDetails

data class Movies(
    val page: Int,
    val results: List<MoviesDetails>,
    val total_pages: Int,
    val total_results: Int
)