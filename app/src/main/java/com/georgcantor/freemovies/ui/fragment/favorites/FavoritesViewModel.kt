package com.georgcantor.freemovies.ui.fragment.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgcantor.freemovies.model.local.FavVideo
import com.georgcantor.freemovies.repository.Repository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: Repository) : ViewModel() {

    val favorites = MutableLiveData<List<FavVideo>>()

    fun getFavorites() {
        viewModelScope.launch {
            favorites.postValue(repository.getFavorites().await())
        }
    }
}