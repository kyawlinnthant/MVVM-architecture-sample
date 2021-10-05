package com.klt.gbs.data.remote

import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ResponseMovieList
import com.klt.gbs.util.Resource

interface ApiDataSource {
    suspend fun getMovieListByTypes(type: String, key: String, page: Int): Resource<ResponseMovieList>
    suspend fun getMovieDetail(id: Double, key: String, lang: String): Resource<Movie>
}