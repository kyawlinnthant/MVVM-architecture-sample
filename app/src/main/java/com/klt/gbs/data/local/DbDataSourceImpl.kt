package com.klt.gbs.data.local

import com.klt.gbs.model.Movie
import javax.inject.Inject

open class DbDataSourceImpl @Inject constructor(private val db: MovieDatabase) : DbDataSource {

    override suspend fun saveMovie(movie: Movie) = db.getMovieDao().saveMovie(movie)

    override suspend fun saveMovies(movies: List<Movie>) = db.getMovieDao().saveMovies(movies)

    override suspend fun getMovie(): Movie = db.getMovieDao().retrieveMovie()

    override suspend fun getMovies(): List<Movie> = db.getMovieDao().retrieveMovies()

}