package com.klt.gbs.data.remote

import com.klt.gbs.model.Movie
import com.klt.gbs.util.Resource
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getMovieListByTypes(
        key: String,
        type: String,
        page: Int
    ): Resource<List<Movie>> = apiService.getMovieByTypes(key, type, page)

    override suspend fun getMovieDetail(key: String, id: Double, lang: String): Resource<Movie> =
        apiService.getMovieDetail(key, id, lang)

}