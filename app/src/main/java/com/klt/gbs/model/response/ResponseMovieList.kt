package com.klt.gbs.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.klt.gbs.model.Movie
import kotlinx.parcelize.Parcelize

/*
    "page": 1,
    "results": [
    {
        "adult": false,
        "backdrop_path": "/t9nyF3r0WAlJ7Kr6xcRYI4jr9jm.jpg",
        "genre_ids": [
                        878,
                        28
                      ],
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
    },...
            ],
    "total_pages": 500,
    "total_results": 10000
*/

@Parcelize
data class ResponseMovieList(
    @SerializedName("results") val list: List<Movie>
) : Parcelable