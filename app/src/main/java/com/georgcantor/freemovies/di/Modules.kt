package com.georgcantor.freemovies.di

import com.georgcantor.freemovies.model.ApiClient
import org.koin.dsl.module

val apiModule = module {
    single { ApiClient.create(get()) }
}

val repositoryModule = module {
//    single { Repository(get()) }
}

val viewModelModule = module(override = true) {
//    viewModel {
//        MainViewModel(get(), get())
//    }
}