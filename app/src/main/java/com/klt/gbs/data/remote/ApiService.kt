package com.klt.gbs.data.remote

import com.klt.gbs.model.Movie
import com.klt.gbs.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{type}")
    suspend fun getMovieByTypes(
        @Query("api_key") key : String,
        @Path("type") movieType: String,
        @Query("page") pageNumber: Int
    ): Resource<List<Movie>>


    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Query("api_key") key : String,
        @Path("id") movieId: Double,
        @Query("language") language: String
    ): Resource<Movie>
}