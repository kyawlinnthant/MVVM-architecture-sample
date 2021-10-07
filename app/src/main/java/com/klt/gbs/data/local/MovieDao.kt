package com.klt.gbs.data.local

import androidx.room.*
import com.klt.gbs.app.AppConstant
import com.klt.gbs.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("SELECT * FROM ${AppConstant.DB_NAME}")
    suspend fun retrieveMovies(): List<Movie>

    @Query("DELETE FROM ${AppConstant.DB_NAME}")
    suspend fun deleteMovies()
}