package com.klt.gbs.data.remote

import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ResponseMovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{type}/")
    suspend fun getMovieByTypes(
        @Path("type") movieType: String,
        @Query("api_key") key: String,
        @Query("page") pageNumber: Int
    ): Response<ResponseMovieList>


    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): Response<Movie>
}