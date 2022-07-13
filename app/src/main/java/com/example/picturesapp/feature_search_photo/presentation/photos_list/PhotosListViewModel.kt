package com.example.picturesapp.feature_search_photo.presentation.photos_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.picturesapp.feature_search_photo.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class PhotosListViewModel(
    private val repository: PhotoRepository
): ViewModel() {

    private val currentQuery = MutableStateFlow(BASE_QUERY)

    val photos = currentQuery.flatMapLatest { query ->
        repository.searchPhotos(query)
    }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun onSearch(query: String) {
        currentQuery.value = query
    }

    companion object {
        const val BASE_QUERY = "cats"
    }
}