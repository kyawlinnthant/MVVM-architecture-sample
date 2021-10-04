package com.klt.gbs.model.response

import android.os.Parcelable
import com.klt.gbs.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val list: List<Movie>
) : Parcelable