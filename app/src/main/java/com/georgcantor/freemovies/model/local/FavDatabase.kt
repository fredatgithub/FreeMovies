package com.georgcantor.freemovies.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavVideo::class], version = 1)
abstract class FavDatabase : RoomDatabase() {

    companion object {
        private const val dbName = "fav_db"

        fun buildDefault(context: Context) = Room.databaseBuilder(context, FavDatabase::class.java, dbName)
                .build()
    }

    abstract fun dao(): FavDao
}