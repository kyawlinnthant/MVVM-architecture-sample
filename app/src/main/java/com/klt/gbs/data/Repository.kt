package com.klt.gbs.data

import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ApiResponse
import com.klt.gbs.model.response.ResponseMovieList
import com.klt.gbs.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun requestMovies(type: String, page: Int): Flow<Resource<ApiResponse<ResponseMovieList>>>
    suspend fun requestMovieDetail(id: Double, lang: String): Flow<Resource<ApiResponse<Movie>>>
    suspend fun getMovies(): Flow<Resource<List<Movie>>>
    suspend fun addMovies(list: List<Movie>) : Flow<Resource<Unit>>
}