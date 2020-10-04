package com.georgcantor.freemovies.ui.fragment.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgcantor.freemovies.model.local.FavVideo
import com.georgcantor.freemovies.repository.Repository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: Repository) : ViewModel() {

    val isFavorite = MutableLiveData<Boolean>()
    val favorites = MutableLiveData<List<FavVideo>>()

    fun getFavorites() {
        viewModelScope.launch {
            favorites.postValue(repository.getFavoritesAsync().await())
        }
    }

    fun checkIsFavorite(video: FavVideo) {
        viewModelScope.launch {
            isFavorite.postValue(repository.getFavoritesAsync().await().any { it.videoId == video.videoId })
        }
    }

    fun addToFavorites(video: FavVideo) {
        viewModelScope.launch {
            repository.insertAsync(video).await()
            isFavorite.postValue(true)
            getFavorites()
        }
    }

    fun removeFromFavorites(id: String?) {
        viewModelScope.launch {
            repository.deleteByIdAsync(id).await()
            isFavorite.postValue(false)
            getFavorites()
        }
    }
}