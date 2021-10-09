package com.klt.gbs.data.local

import com.klt.gbs.model.Movie

interface DbDataSource {
    suspend fun savePopularMovies(movies: List<Movie>)
    suspend fun getPopularMovies(): List<Movie>
    suspend fun saveUpcomingMovies(movies: List<Movie>)
    suspend fun getUpcomingMovies(): List<Movie>
}