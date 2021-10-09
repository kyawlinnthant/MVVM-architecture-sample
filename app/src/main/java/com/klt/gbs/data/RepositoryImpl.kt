package com.klt.gbs.data

import com.klt.gbs.BuildConfig.API_KEY
import com.klt.gbs.data.local.DbDataSource
import com.klt.gbs.data.remote.ApiDataSource
import com.klt.gbs.di.IoDispatcher
import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: ApiDataSource,
    private val db: DbDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {

    override suspend fun requestMovies(type: String, page: Int) = withContext(ioDispatcher) {
        safeApiCall {
            api.fetchMoviesByType(type, API_KEY, page)
        }
    }

    override suspend fun requestMovieDetail(id: Int, lang: String) = withContext(ioDispatcher) {
        safeApiCall {
            api.fetchMovie(id, API_KEY, lang)
        }
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return withContext(ioDispatcher) {
            db.getPopularMovies()
        }
    }

    override suspend fun addPopularMovies(list: List<Movie>) {

        withContext(ioDispatcher) {
            db.savePopularMovies(list)
        }
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        return withContext(ioDispatcher) {
            db.getUpcomingMovies()
        }
    }

    override suspend fun addUpcomingMovies(list: List<Movie>) {
        withContext(ioDispatcher) {
            db.saveUpcomingMovies(list)
        }
    }


}