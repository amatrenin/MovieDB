package com.example.mypetproject.view.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mypetproject.model.apis.ApiInterface
import com.example.mypetproject.Constants.API_KEY
import com.example.mypetproject.data.Details.MoviesDetails

const val NETWORK_PAGE_SIZE = 25

class MoviesPagingSource(private val apiService: ApiInterface) :
    PagingSource<Int, MoviesDetails>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDetails> {

        return try {
            val position = params.key ?: 1
            val response = apiService.getMovies(API_KEY, language = "en-US", position)
            LoadResult.Page(
                data = response.body()?.results ?: emptyList(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MoviesDetails>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
