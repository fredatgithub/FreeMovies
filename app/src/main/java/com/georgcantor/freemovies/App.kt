package com.georgcantor.freemovies

import android.app.Application
import com.georgcantor.freemovies.di.apiModule
import com.georgcantor.freemovies.di.dbModule
import com.georgcantor.freemovies.di.repositoryModule
import com.georgcantor.freemovies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(apiModule, dbModule, repositoryModule, viewModelModule))
        }
    }
}