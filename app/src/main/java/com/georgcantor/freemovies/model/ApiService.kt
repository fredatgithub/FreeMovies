package com.georgcantor.freemovies.model

import com.georgcantor.freemovies.model.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getVideos(
        @Url url: String,
        @Query("playlistId") playlistId: String
    ): Response<VideoResponse>
}