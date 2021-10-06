package com.klt.gbs.data.remote

import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ApiResponse
import com.klt.gbs.model.response.ResponseMovieList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(private val apiService: ApiService) : ApiDataSource {

    override suspend fun getMovieListByTypes(
        type: String,
        key: String,
        page: Int
    ): ApiResponse<ResponseMovieList> = apiService.getMovieByTypes(type, key, page)

    override suspend fun getMovieDetail(
        id: Double,
        key: String,
        lang: String
    ): ApiResponse<Movie> =
        apiService.getMovieDetail(id, key, lang)

}