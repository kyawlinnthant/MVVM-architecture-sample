package com.klt.gbs.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klt.gbs.data.Repository
import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ResponseMovieList
import com.klt.gbs.util.NetworkHelper
import com.klt.gbs.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _movieListFlow = Channel<Resource<ResponseMovieList>>(Channel.BUFFERED)
    val movieListFlow = _movieListFlow.receiveAsFlow()

    fun getListByType(type: String) {
        //todo ALL RESOURCE STATE AND NETWORK STATE ARE NOT IMPLEMENTED WELL
        //todo response data is always null this may be because of FLOW

        viewModelScope.launch {

            val ee = repo.requestMovies(type, 1)
            Timber.e(ee.toString())

            repo.requestMovies(type, 1).catch { e ->
                _movieListFlow.send(Resource.error(e.localizedMessage ?: "error"))
            }.collect {
                Timber.e(it.toString())
                if (networkHelper.isNetworkConnected()) {
                    _movieListFlow.send(it)
                    Timber.e(it.data?.list?.size.toString())
                }
                else _movieListFlow.send(Resource.error("NO INTERNET CONNECTION"))
            }

        }
    }
}