package com.klt.gbs.ui.popular

import androidx.lifecycle.*
import com.klt.gbs.data.Repository
import com.klt.gbs.model.Movie
import com.klt.gbs.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    val movies = MediatorLiveData<Resource<List<Movie>>>()

    private var _initialListFromDb: LiveData<Resource<List<Movie>>>
    private val _listFromDb = MutableLiveData<List<Movie>>()
    private var _updatedMovies = MutableLiveData<Resource<List<Movie>>>()

    init {
        _initialListFromDb = Transformations.switchMap(_listFromDb) {
            getDbList()
        }
        movies.addSource(_updatedMovies) {
            movies.value = it
        }
        movies.addSource(_initialListFromDb) {
            movies.value = it
        }
    }

    fun getList(type: String) {
        viewModelScope.launch {
            _updatedMovies.value = Resource.loading(null)
            val response = repo.requestMovies(type, 1)
            response.data?.let {
                repo.addPopularMovies(it.list)
                _updatedMovies.value = Resource.success(it.list)
            }
        }
    }

    private fun getDbList(): LiveData<Resource<List<Movie>>> {
        val liveData = MutableLiveData<Resource<List<Movie>>>()
        viewModelScope.launch {
            val movies = repo.getPopularMovies()
            liveData.value = Resource.success(movies)
        }
        return liveData
    }
}