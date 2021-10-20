package com.klt.gbs.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.klt.gbs.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/*
 "page": 1,
 "results": [ {.....},...],
 "total_pages": 500,
 "total_results": 10000
 */

@Parcelize
data class ResponseMovieList(
    @Json(name = "results")
    val list: List<Movie>,
    @Json(name = "total_result")
    val totalResults: Double
) : Parcelable
