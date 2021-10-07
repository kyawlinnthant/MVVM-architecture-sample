package com.klt.gbs.data

import com.klt.gbs.BuildConfig.API_KEY
import com.klt.gbs.data.local.DbDataSource
import com.klt.gbs.data.remote.ApiDataSource
import com.klt.gbs.di.IoDispatcher
import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: ApiDataSource,
    private val db: DbDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {

    override suspend fun requestMovies(type: String, page: Int) = flow {

        emit(
            safeApiCall { api.getMovieListByTypes(type, API_KEY, page) }
        )
    }.flowOn(ioDispatcher)

    override suspend fun requestMovieDetail(id: Int, lang: String) = flow {

        emit(
            safeApiCall { api.getMovieDetail(id, API_KEY, lang) }
        )
    }.flowOn(ioDispatcher)

    override suspend fun getMovies(): List<Movie> {
        var movies: List<Movie>
        withContext(ioDispatcher) {
            movies = db.getMovies()
        }
        Timber.tag("getMovies in Repo : ").d(movies.toString())
        return movies

    }

    override suspend fun addMovies(list: List<Movie>) = withContext(ioDispatcher) {
        db.saveMovies(list)
    }

    override suspend fun deleteMovies() = withContext(ioDispatcher) {
        db.deleteMovies()
    }

}