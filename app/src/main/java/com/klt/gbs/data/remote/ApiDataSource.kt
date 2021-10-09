package com.klt.gbs.data.remote

import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ResponseMovieList
import retrofit2.Response

interface ApiDataSource {

    suspend fun fetchMoviesByType(
        type: String,
        key: String,
        page: Int
    ): Response<ResponseMovieList>

    suspend fun fetchMovie(id: Int, key: String, lang: String): Response<Movie>
}