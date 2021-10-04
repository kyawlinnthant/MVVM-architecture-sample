package com.klt.gbs.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klt.gbs.data.AppRepository
import com.klt.gbs.model.Movie
import com.klt.gbs.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    var movieList : MutableLiveData<List<Movie>> = MutableLiveData()
    fun getListByType(type: String){
        viewModelScope.launch {
            repository.requestMovies(type,25).collect { movieList.value = it.data }
        }
    }
}