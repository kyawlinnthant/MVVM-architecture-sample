package com.klt.gbs.data

import com.klt.gbs.BuildConfig.API_KEY
import com.klt.gbs.data.local.DbHelper
import com.klt.gbs.data.remote.ApiHelper
import com.klt.gbs.di.DefaultDispatcher
import com.klt.gbs.di.IoDispatcher
import com.klt.gbs.di.MainDispatcher
import com.klt.gbs.model.Movie
import com.klt.gbs.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dbHelper: DbHelper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) {

    suspend fun requestMovies(type: String, page: Int) = flow {
        val response = apiHelper.getMovieListByTypes(API_KEY, type, page)
        try {
            emit(Resource.loading(response.data))
            emit(Resource.success(response.data))
        } catch (e: Exception) {
            emit(Resource.error(e.localizedMessage ?: "error", response.data))
        }
    }.flowOn(defaultDispatcher)

    suspend fun requestMovieDetail(id: Double, lang: String) = flow {
        val response = apiHelper.getMovieDetail(API_KEY, id, lang)
        try {
            emit(Resource.loading(response.data))
            emit(Resource.success(response.data))
        } catch (e: Exception) {
            emit(Resource.error(e.localizedMessage ?: "error", response.data))
        }
    }.flowOn(defaultDispatcher)

    suspend fun getMovies() = flow {
        val query = dbHelper.getMovies()
        try {
            emit(query)
        } catch (e: Exception) {
            emit(e.localizedMessage)
        }
    }.flowOn(ioDispatcher)

    suspend fun addMovies(list: List<Movie>) = flow {
        val query = dbHelper.saveMovies(list)
        try {
            emit(query)
        } catch (e: Exception) {
            emit(e.localizedMessage)
        }
    }.flowOn(ioDispatcher)


}