package com.klt.gbs.data.remote

import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(private val apiService: ApiService) : ApiDataSource {

    override suspend fun fetchMoviesByType(
        type: String,
        key: String,
        page: Int
    ) = apiService.getMovieByTypes(type, key, page)

    override suspend fun fetchMovie(
        id: Int,
        key: String,
        lang: String
    ) = apiService.getMovieDetail(id, key, lang)

}