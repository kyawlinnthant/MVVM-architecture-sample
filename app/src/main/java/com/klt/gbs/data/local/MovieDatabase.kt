package com.klt.gbs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.klt.gbs.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}