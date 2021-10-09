package com.klt.gbs.ui.upcoming

import androidx.lifecycle.*
import com.klt.gbs.data.Repository
import com.klt.gbs.model.Movie
import com.klt.gbs.util.NetworkHelper
import com.klt.gbs.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val repo: Repository, private val networkHelper: NetworkHelper
) : ViewModel() {

    val movies = MediatorLiveData<Resource<List<Movie>>>()

    private var _initialListFromDb: LiveData<Resource<List<Movie>>>
    private var _updatedMovies = MutableLiveData<Resource<List<Movie>>>()

    init {
        _initialListFromDb = getDbList()

        movies.addSource(_updatedMovies) {
            movies.value = it
        }
        movies.addSource(_initialListFromDb) {
            movies.value = it
        }
    }

    fun getList(type: String) {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                _updatedMovies.value = Resource.loading(null)
                val response = repo.requestMovies(type, 1)
                response.data?.let {
                    repo.addUpcomingMovies(it.list)
                    _updatedMovies.value = Resource.success(it.list)
                }
            }
        }

    }

    private fun getDbList(): LiveData<Resource<List<Movie>>> {
        val liveData = MutableLiveData<Resource<List<Movie>>>()
        viewModelScope.launch {
            val movies = repo.getUpcomingMovies()
            liveData.value = Resource.success(movies)
        }
        return liveData
    }
}