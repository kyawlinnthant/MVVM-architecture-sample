package com.klt.gbs.data.local

import androidx.lifecycle.LiveData
import com.klt.gbs.model.Movie
import javax.inject.Inject

open class DbDataSourceImpl @Inject constructor(private val db: MovieDatabase) : DbDataSource {
    override suspend fun savePopularMovies(movies: List<Movie>) {
        db.getPopular().insertMovies(movies)
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return db.getPopular().retrieveMovies()
    }

    override suspend fun saveUpcomingMovies(movies: List<Movie>) {
        db.getUpcoming().insertMovies(movies)
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        return db.getUpcoming().retrieveMovies()
    }


}