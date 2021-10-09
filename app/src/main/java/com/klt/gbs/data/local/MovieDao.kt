package com.klt.gbs.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.klt.gbs.app.AppConstant
import com.klt.gbs.model.Movie

@Dao
interface PopularMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM ${AppConstant.DB_NAME}")
    suspend fun retrieveMovies(): List<Movie>

}

@Dao
interface UpcomingMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM ${AppConstant.DB_NAME}")
    suspend fun retrieveMovies(): List<Movie>

}