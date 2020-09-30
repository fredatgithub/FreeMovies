package com.georgcantor.freemovies.repository

import com.georgcantor.freemovies.BuildConfig.YOUTUBE_URL
import com.georgcantor.freemovies.model.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getVideos() = apiService.getVideos(
        YOUTUBE_URL,
        "UUPOhQupz3MwGSIBG0OqVnAg"
    )
}