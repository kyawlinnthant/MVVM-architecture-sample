package com.klt.gbs.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klt.gbs.data.Repository
import com.klt.gbs.model.Movie
import com.klt.gbs.util.NetworkHelper
import com.klt.gbs.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repo: Repository, private val networkHelper: NetworkHelper
) : ViewModel() {

    private var _movieData = MutableLiveData<Resource<Movie>>()
    val movieData: LiveData<Resource<Movie>> get() = _movieData

    fun fetchData(id: Int) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected())
            _movieData.value = repo.requestMovieDetail(id, "en")
            else _movieData.value = Resource.error( "NO NETWORK CONNECTION ")
        }
    }

}