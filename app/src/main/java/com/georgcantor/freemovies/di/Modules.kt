package com.georgcantor.freemovies.di

import com.georgcantor.freemovies.model.ApiClient
import com.georgcantor.freemovies.repository.Repository
import com.georgcantor.freemovies.ui.fragment.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { ApiClient.create(get()) }
}

val repositoryModule = module {
    single { Repository(get()) }
}

val viewModelModule = module(override = true) {
    viewModel {
        ListViewModel(get())
    }
}