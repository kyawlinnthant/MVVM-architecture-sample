package com.klt.gbs.data

import com.klt.gbs.BuildConfig
import com.klt.gbs.data.local.DbHelper
import com.klt.gbs.data.remote.ApiHelper
import com.klt.gbs.di.DefaultDispatcher
import com.klt.gbs.di.IoDispatcher
import com.klt.gbs.di.MainDispatcher
import com.klt.gbs.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dbHelper: DbHelper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    private val LKEY = BuildConfig.API_KEY//****Bad**** data layer access the Android Resources

    suspend fun requestMovies(type: String, page: Int) = withContext(defaultDispatcher) {
        apiHelper.getMovieListByTypes(LKEY, type, page)
    }


    suspend fun requestMovieDetail(id: Double, lang: String) =
        apiHelper.getMovieDetail(LKEY, id, lang)

    suspend fun getMovies() = dbHelper.getMovies()
    suspend fun addMovies(list: List<Movie>) = dbHelper.saveMovies(list)


}