package com.georgcantor.freemovies.repository

import com.georgcantor.freemovies.BuildConfig.YOUTUBE_URL
import com.georgcantor.freemovies.model.local.FavDao
import com.georgcantor.freemovies.model.local.FavVideo
import com.georgcantor.freemovies.model.remote.ApiService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class Repository(
    private val apiService: ApiService,
    private val dao: FavDao
) {

    suspend fun getVideos(id: String) = apiService.getVideos(YOUTUBE_URL, id)

    suspend fun getFavorites(): Deferred<List<FavVideo>> = coroutineScope {
        async { return@async dao.getAllVideos() }
    }

    suspend fun insert(favVideo: FavVideo) = coroutineScope {
        async { dao.insert(favVideo) }
    }

    suspend fun deleteById(videoId: String?) = coroutineScope {
        async { dao.deleteById(videoId) }
    }
}