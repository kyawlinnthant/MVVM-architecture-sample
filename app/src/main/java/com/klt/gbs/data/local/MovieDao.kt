package com.klt.gbs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.klt.gbs.app.AppConstant
import com.klt.gbs.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("SELECT * FROM ${AppConstant.DB_NAME}")
    suspend fun retrieveMovie(): Movie

    @Query("SELECT * FROM ${AppConstant.DB_NAME}")
    suspend fun retrieveMovies(): List<Movie>
}