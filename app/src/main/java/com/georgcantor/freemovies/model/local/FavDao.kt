package com.georgcantor.freemovies.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAllVideos(): List<FavVideo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favVideo: FavVideo)

    @Query("DELETE FROM favorites WHERE videoId = :videoId")
    suspend fun deleteById(videoId: String?)
}