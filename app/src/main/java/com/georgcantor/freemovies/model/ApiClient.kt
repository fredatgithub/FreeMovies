package com.georgcantor.freemovies.model

import android.content.Context
import com.georgcantor.freemovies.BuildConfig.DEBUG
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ApiClient {

    fun create(context: Context): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (DEBUG) Level.BODY else Level.NONE

        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(OfflineResponseCacheInterceptor(context))
            .addInterceptor(loggingInterceptor)
            .cache(Cache(File(context.cacheDir, "ResponsesCache"), (10 * 1024 * 1024).toLong()))
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("222")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}