package com.klt.gbs.data.remote

import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ResponseMovieList
import com.klt.gbs.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{type}/")
    suspend fun getMovieByTypes(
        @Path("type") movieType: String,
        @Query("api_key") key : String,
        @Query("page") pageNumber: Int
    ): Resource<ResponseMovieList>


    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Double,
        @Query("api_key") key : String,
        @Query("language") language: String
    ): Resource<Movie>
}