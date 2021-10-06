package com.klt.gbs.data

import com.klt.gbs.BuildConfig.API_KEY
import com.klt.gbs.data.local.DbDataSource
import com.klt.gbs.data.remote.ApiDataSource
import com.klt.gbs.di.IoDispatcher
import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.safeApiCall
import com.klt.gbs.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: ApiDataSource,
    private val db: DbDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {

    //todo : need to consider return type and add NetworkBoundResource
    override suspend fun requestMovies(type: String, page: Int) = flow {

        emit(
            safeApiCall { api.getMovieListByTypes(type, API_KEY, page) }
        )
    }.flowOn(ioDispatcher)

    override suspend fun requestMovieDetail(id: Double, lang: String) = flow {

        /* return object : NetworkBondResource<Movie,Movie>(ioDispatcher){
             override fun saveCallResult(item: Movie) {
                 db.saveMovies(item)
             }

             override fun shouldFetch(data: Movie?): Boolean {
                 return data == null
             }

             override fun loadFromDb(): LiveData<Movie> {
                 return db.getMovies()
             }

             override fun createCall(): LiveData<Resource<Movie>> {
                 return db.getMovies()
             }
         }.asLiveData()*/

        emit(
            safeApiCall { api.getMovieDetail(id, API_KEY, lang) }
        )
    }.flowOn(ioDispatcher)

    override suspend fun getMovies() = flow {

        val query = db.getMovies()
        try {
            emit(Resource.loading(query))
            emit(Resource.success(query))
        } catch (e: Exception) {
            emit(Resource.error(e.localizedMessage ?: "db List Retrieve error", query))
        }
    }.flowOn(ioDispatcher)

    override suspend fun getMovie() = flow {
        val query = db.getMovie()
        try {
            emit(Resource.loading(query))
            emit(Resource.success(query))
        } catch (e: Exception) {
            emit(Resource.error(e.localizedMessage ?: "db Object Retrieve error", query))
        }

    }.flowOn(ioDispatcher)

    override suspend fun addMovie(movie: Movie) = flow {
        val query = db.saveMovie(movie)
        try {
            emit(Resource.loading(query))
            emit(Resource.success(query))
        } catch (e: Exception) {
            emit(
                Resource.error(e.localizedMessage ?: "db Object insert error", query)
            )
        }
    }.flowOn(ioDispatcher)

    override suspend fun addMovies(list: List<Movie>) = flow {
        val query = db.saveMovies(list)
        try {
            emit(Resource.loading(query))
            emit(Resource.success(query))
        } catch (e: Exception) {
            emit(Resource.error(e.localizedMessage ?: "db List insert error", query))
        }
    }.flowOn(ioDispatcher)

}