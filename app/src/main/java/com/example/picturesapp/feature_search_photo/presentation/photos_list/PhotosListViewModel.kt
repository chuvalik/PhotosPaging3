package com.example.picturesapp.feature_search_photo.presentation.photos_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.LoadState
import com.example.picturesapp.feature_search_photo.domain.repository.PhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class PhotosListViewModel(
    private val repository: PhotoRepository
): ViewModel() {

    private val currentQuery = MutableStateFlow(BASE_QUERY)

    fun onSearch(query: String) {
        currentQuery.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val photos = currentQuery.flatMapLatest { query ->
        repository.searchPhotos(query)
    }.asLiveData()

    companion object {
        const val BASE_QUERY = "cats"
    }
}