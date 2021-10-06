package com.klt.gbs.data

import com.klt.gbs.BuildConfig.API_KEY
import com.klt.gbs.data.local.DbDataSource
import com.klt.gbs.data.remote.ApiDataSource
import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ResponseMovieList
import com.klt.gbs.model.response.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: ApiDataSource,
    private val db: DbDataSource,
) : Repository {

    override suspend fun requestMovies(type: String, page: Int) =
        object : NetworkBoundResource<List<Movie>, ResponseMovieList>() {
            override suspend fun loadFromDb(): List<Movie> {
                return db.getMovies()
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true
            }

            override suspend fun makeApiCall(): ResponseMovieList? {
                return safeApiCall { api.getMovieListByTypes(type, API_KEY, page) }.data
            }

            override suspend fun saveCallResult(data: ResponseMovieList) {
                db.saveMovies(data.list)
            }


        }.asLiveData()
}