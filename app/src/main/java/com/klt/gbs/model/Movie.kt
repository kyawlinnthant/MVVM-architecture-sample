package com.klt.gbs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
/*

"adult"
"backdrop_path"
"genre_ids"
"id"
"original_language"
"original_title"
"overview"
"popularity"
"poster_path"
"release_date"
"title"
"video"
"vote_average"
"vote_count"

*/



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

    @Json(name = "adult") val isAdult : Boolean,
    @Json(name = "backdrop_path") val path: String,
    @Json(name = "genre_ids") val genreId : List<Int>,
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id") val movieId: Int,
    @Json(name = "original_language") val originalLanguage : String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity : Double,
    @Json(name = "poster_path") val posterPath : String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "title")val title : String,
    @Json(name = "video")val isVideo :Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount : Int
) : Parcelable
