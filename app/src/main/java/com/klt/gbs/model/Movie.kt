package com.klt.gbs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


/*{
    "adult": false,
    "backdrop_path": "/t9nyF3r0WAlJ7Kr6xcRYI4jr9jm.jpg",
    "genre_ids": [878,28],
    "id": 580489,
    "original_language": "en",
    "original_title": "Venom: Let There Be Carnage",
    "overview": "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
    "popularity": 4238.873,
    "poster_path": "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
    "release_date": "2021-09-30",
    "title": "Venom: Let There Be Carnage",
    "video": false,
    "vote_average": 7.5,
    "vote_count": 164
}*/

@Parcelize
@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    val movieId: Int,
    @Json(name = "original_title")
    val title: String,
    @Json(name = "backdrop_path")
    val path: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "vote_average")
    val voteAverage: Double
) : Parcelable
