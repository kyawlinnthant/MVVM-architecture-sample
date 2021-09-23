package com.klt.gbs.data.local

import androidx.lifecycle.LiveData
import com.klt.gbs.model.Movie

interface DbHelper {

    suspend fun saveMovies(movies: List<Movie>)
    suspend fun getMovies() : LiveData<List<Movie>>
}