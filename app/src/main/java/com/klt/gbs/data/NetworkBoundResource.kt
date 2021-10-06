package com.klt.gbs.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.klt.gbs.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result = MutableLiveData<Resource<ResultType>>()
    private val job = Job()

    init {
        CoroutineScope(Dispatchers.IO + job).launch {
            result.postValue(Resource.loading(null))
            val dbSource = loadFromDb()
            if (shouldFetch(dbSource)) fetchFromNetwork(dbSource)
            else result.postValue(Resource.success(dbSource))
        }
    }

    private suspend fun fetchFromNetwork(dbSource: ResultType?) {

        //emit local data only if not empty
        dbSource?.let {
            result.postValue(Resource.success(it))
        }

        try {
            val apiResponse = makeApiCall()

            apiResponse?.let {
                //get from network(not empty response) and save it to database
                saveCallResult(it)
                result.postValue(Resource.success(loadFromDb()))
            } ?: run {
                //if empty response and empty database
                if (dbSource == null)
                    result.postValue(null)
            }

        } catch (e: Exception) {
            //fail state emit
            result.postValue(Resource.error(e.localizedMessage ?: "error in network bound"))
        }
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    protected abstract suspend fun loadFromDb(): ResultType?

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun makeApiCall(): RequestType?

    protected abstract suspend fun saveCallResult(data: RequestType)

}