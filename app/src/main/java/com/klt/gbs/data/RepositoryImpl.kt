package com.klt.gbs.data

import com.klt.gbs.BuildConfig.API_KEY
import com.klt.gbs.data.local.DbDataSource
import com.klt.gbs.data.remote.ApiDataSource
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: ApiDataSource,
    private val db: DbDataSource,
) : Repository {

    override suspend fun requestMovies(type: String, page: Int) =
        networkBoundResource(
            query = {
                db.getMovies()
            },
            fetch = {
                delay(2000)//wait 2s to load
                api.getMovieListByTypes(type, API_KEY, page)
            },
            saveFetchResult = { items ->
                db.deleteMovies()
                db.saveMovies(items.body()?.list!!)
            }
        )

}