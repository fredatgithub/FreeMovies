package com.georgcantor.freemovies.repository

import com.georgcantor.freemovies.BuildConfig.YOUTUBE_URL
import com.georgcantor.freemovies.model.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getVideos(id: String) = apiService.getVideos(YOUTUBE_URL, id)
}