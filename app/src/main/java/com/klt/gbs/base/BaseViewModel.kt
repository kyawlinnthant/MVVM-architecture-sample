package com.klt.gbs.base

import androidx.lifecycle.ViewModel
import com.klt.gbs.data.Repository
import com.klt.gbs.util.NetworkHelper
import javax.inject.Inject

open class BaseViewModel @Inject constructor(
    private val repository: Repository,
    private val networkHelper: NetworkHelper
) : ViewModel()