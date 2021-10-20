package com.klt.gbs.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.klt.gbs.model.Movie
import kotlinx.parcelize.Parcelize

/*
 "page": 1,
 "results": [ {.....},...],
 "total_pages": 500,
 "total_results": 10000
 */

@Parcelize
data class ResponseMovieList(
    @SerializedName("results") val list: List<Movie>,
    val total_results: Double,
    val page : Int
) : Parcelable
