package com.klt.gbs.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klt.gbs.data.Repository
import com.klt.gbs.model.Movie
import com.klt.gbs.model.response.ResponseMovieList
import com.klt.gbs.util.NetworkHelper
import com.klt.gbs.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _movieList = MutableLiveData<Resource<List<Movie>>>()
    val movieList: LiveData<Resource<List<Movie>>> get() = _movieList

    fun getListByType(type: String) {

        viewModelScope.launch {

            val res = repo.requestMovies(type,1)
            Timber.tag("VM : ").e("${res.value?.status}")
            repo.requestMovies(type,1).apply {
                if (networkHelper.isNetworkConnected()) _movieList.value = this.value
                else _movieList.value = Resource.error("NO INTERNET CONNECTION")
            }

           /* repo.requestMovies(type, 1).collect {
                if (networkHelper.isNetworkConnected()) _movieList.value = it
                else _movieList.value = Resource.error("NO INTERNET CONNECTION")
            }*/
        }
    }
}