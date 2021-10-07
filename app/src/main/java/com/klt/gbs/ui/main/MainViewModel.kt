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
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private var _movieList = MutableLiveData<Resource<List<Movie>>>()
    val movieList: LiveData<Resource<List<Movie>>> get() = _movieList

    fun getList(type: String) {
        viewModelScope.launch {
            val dbList = repo.getMovies()
            val networkResponse = repo.requestMovies(type, 1)
            if (networkHelper.isNetworkConnected()) {
                if (dbList.isNotEmpty()) repo.deleteMovies()
                networkResponse.collect {
                    if (it.status == Resource.Status.SUCCESS) repo.addMovies(it.data?.list!!)
                }
                _movieList.value = Resource.success(repo.getMovies())
            } else _movieList.value = Resource.error("NO INTERNET CONNECTION")
        }

    }
}