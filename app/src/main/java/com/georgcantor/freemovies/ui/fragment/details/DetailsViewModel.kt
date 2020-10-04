package com.georgcantor.freemovies.ui.fragment.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgcantor.freemovies.model.local.FavVideo
import com.georgcantor.freemovies.repository.Repository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    val isFavorite = MutableLiveData<Boolean>()

    fun checkIsFavorite(video: FavVideo) {
        viewModelScope.launch {
            isFavorite.postValue(repository.getFavoritesAsync().await().any { it.videoId == video.videoId })
        }
    }

    fun addToFavorites(video: FavVideo) {
        viewModelScope.launch {
            repository.insertAsync(video).await()
            isFavorite.postValue(true)
        }
    }

    fun removeFromFavorites(id: String?) {
        viewModelScope.launch {
            repository.deleteByIdAsync(id).await()
            isFavorite.postValue(false)
        }
    }
}