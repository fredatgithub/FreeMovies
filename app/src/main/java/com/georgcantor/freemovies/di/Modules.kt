package com.georgcantor.freemovies.di

import com.georgcantor.freemovies.model.local.FavDatabase
import com.georgcantor.freemovies.model.remote.ApiClient
import com.georgcantor.freemovies.repository.Repository
import com.georgcantor.freemovies.ui.fragment.favorites.FavoritesViewModel
import com.georgcantor.freemovies.ui.fragment.list.ListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { ApiClient.create(get()) }
}

val dbModule = module {
    single { FavDatabase.buildDefault(androidApplication()).dao() }
}

val repositoryModule = module {
    single { Repository(get(), get()) }
}

val viewModelModule = module(override = true) {
    viewModel {
        ListViewModel(get())
    }
    viewModel {
        FavoritesViewModel(get())
    }
}