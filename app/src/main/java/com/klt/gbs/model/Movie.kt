package com.klt.gbs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Movie(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val movieId: Int,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("vote_average")
    val vote_average: Double
) : Parcelable
