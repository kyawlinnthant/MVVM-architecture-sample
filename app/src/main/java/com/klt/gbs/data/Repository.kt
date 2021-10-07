package com.klt.gbs.data

import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ResponseMovieList
import com.klt.gbs.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun requestMovies(type: String, page: Int): Flow<Resource<List<Movie>>>
}