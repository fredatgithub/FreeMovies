package com.georgcantor.freemovies.ui.fragment.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgcantor.freemovies.model.local.FavVideo
import com.georgcantor.freemovies.model.remote.response.Item
import com.georgcantor.freemovies.repository.Repository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    fun addToFavorites(item: Item) {
        viewModelScope.launch {
            repository.insert(
                FavVideo(
                    item.snippet?.title,
                    item.snippet?.description,
                    item.snippet?.resourceId?.videoId,
                    item.snippet?.thumbnails?.standard?.url
                )
            ).await()
        }
    }
}