package com.klt.gbs.data.local

import com.klt.gbs.model.Movie
import kotlinx.coroutines.flow.Flow

interface DbDataSource {
    suspend fun saveMovies(movies: List<Movie>)
    suspend fun getMovies(): Flow<List<Movie>>
    suspend fun deleteMovies()
}