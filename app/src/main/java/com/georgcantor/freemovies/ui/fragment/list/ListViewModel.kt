package com.georgcantor.freemovies.ui.fragment.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgcantor.freemovies.model.remote.response.Item
import com.georgcantor.freemovies.repository.Repository
import kotlinx.coroutines.launch

class ListViewModel(private val repository: Repository) : ViewModel() {

    val videos = MutableLiveData<List<Item>>()
    val isProgressVisible = MutableLiveData<Boolean>()

    fun getVideos(id: String) {
        isProgressVisible.value = true
        viewModelScope.launch {
            val response = repository.getVideos(id)
            if (response.isSuccessful) videos.postValue(response.body()?.items?.reversed())
            isProgressVisible.postValue(false)
        }
    }
}