package com.klt.gbs.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.klt.gbs.di.IoDispatcher
import com.klt.gbs.util.Resource
import retrofit2.Response
import javax.inject.Inject

abstract class NetworkBondResource<ResultType, RequestType> @Inject constructor(
    @IoDispatcher private val defaultDispatcher: IoDispatcher,
) {

    //todo : make Coroutines correct
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (shouldFetch(it)) fetchFromNetwork(dbSource)
            else
                result.addSource(dbSource) {
                    setValue(Resource.success(it))
                }
        }

    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { setValue(Resource.loading(it)) }
        result.addSource(apiResponse) {
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            if (it.isSuccessful) {
                //todo make in defaultDispatcher
                saveCallResult(processResponse(it)!!)
                //todo in main thread
                result.addSource(loadFromDb()) { v -> setValue(Resource.success(v)) }

                if (it.body() == null) {
                    //do in main
                    result.addSource(loadFromDb()) { v -> setValue(Resource.success(v)) }

                } else {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        setValue(Resource.error("Error"))
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    //do in main thread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    //do in default thread
    protected open fun processResponse(response: Response<RequestType>) = response.body()

    //do in default thread
    protected abstract fun saveCallResult(item: RequestType)

    //do in main thread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    //do in main thread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    //do in main thread
    protected abstract fun createCall(): LiveData<Response<RequestType>>

}
