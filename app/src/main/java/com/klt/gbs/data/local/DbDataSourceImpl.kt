package com.klt.gbs.data.local

import androidx.lifecycle.LiveData
import com.klt.gbs.model.Movie
import javax.inject.Inject

open class DbDataSourceImpl @Inject constructor(private val db: MovieDatabase) : DbDataSource {
    override suspend fun saveMovies(movies: List<Movie>) {
        db.getDao().insertMovies(movies)
    }

    override suspend fun getMovies(): List<Movie> {
        return db.getDao().retrieveMovies()
    }

}