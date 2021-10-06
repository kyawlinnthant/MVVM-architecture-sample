package com.klt.gbs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int,
    val id: Int,
    val original_title: String,
    val backdrop_path: String,
    val overview: String,
    val release_date: String,
    val vote_average: Double
) : Parcelable