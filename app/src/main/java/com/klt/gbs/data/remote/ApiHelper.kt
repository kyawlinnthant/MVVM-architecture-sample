package com.klt.gbs.data.remote

import com.klt.gbs.model.Movie
import com.klt.gbs.util.Resource
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getMovieListByTypes(key: String, type: String, page: Int): Resource<List<Movie>>
    suspend fun getMovieDetail(key: String, id: Double, lang: String): Resource<Movie>
}