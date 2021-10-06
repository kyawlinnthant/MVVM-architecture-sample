package com.klt.gbs.data.remote

import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ApiResponse
import com.klt.gbs.model.response.ResponseMovieList

interface ApiDataSource {

    suspend fun getMovieListByTypes(
        type: String,
        key: String,
        page: Int
    ): ApiResponse<ResponseMovieList>

    suspend fun getMovieDetail(id: Double, key: String, lang: String): ApiResponse<Movie>
}