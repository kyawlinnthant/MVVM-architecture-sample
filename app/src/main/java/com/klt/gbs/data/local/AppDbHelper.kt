package com.klt.gbs.data.local

import com.klt.gbs.model.Movie
import javax.inject.Inject

open class AppDbHelper @Inject constructor(private val db: MovieDatabase) : DbHelper {
    override suspend fun saveMovies(movies: List<Movie>) = db.getMovieDao().saveMovies(movies)
    override suspend fun getMovies() = db.getMovieDao().retrieveMovies()
}