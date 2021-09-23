package com.klt.gbs.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "website")
    val website: String = "",
    @Json(name = "phone")
    val phone: String = ""
) : Parcelable