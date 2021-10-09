package com.klt.gbs.data.local

import com.klt.gbs.model.Movie

interface DbDataSource {
    suspend fun saveMovies(movies: List<Movie>)
    suspend fun getMovies(): List<Movie>
}