package com.example.mypetproject.data.review

data class review(
    val id: Int,
    val page: Int,
    val results: List<ResultReview>,
    val total_pages: Int,
    val total_results: Int
)