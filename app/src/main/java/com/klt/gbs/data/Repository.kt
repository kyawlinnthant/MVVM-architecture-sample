package com.klt.gbs.data

import androidx.lifecycle.LiveData
import com.klt.gbs.model.Movie
import com.klt.gbs.util.Resource

interface Repository {
    suspend fun requestMovies(type: String, page: Int): LiveData<Resource<List<Movie>>>
}