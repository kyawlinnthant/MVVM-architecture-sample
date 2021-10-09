package com.klt.gbs.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ResponseMovieList
import com.klt.gbs.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    //from api
    suspend fun requestMovies(type: String, page: Int): Resource<ResponseMovieList>
    suspend fun requestMovieDetail(id: Int, lang: String): Resource<Movie>

    //from db
    suspend fun getMovies(): List<Movie>?
    suspend fun addMovies(list: List<Movie>)
}