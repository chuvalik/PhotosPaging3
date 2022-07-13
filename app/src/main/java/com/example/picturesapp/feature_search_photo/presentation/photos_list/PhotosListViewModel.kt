package com.example.picturesapp.feature_search_photo.presentation.photos_list

import androidx.lifecycle.*
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.example.picturesapp.feature_search_photo.domain.repository.PhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class PhotosListViewModel(
    private val repository: PhotoRepository,
    state: SavedStateHandle
): ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, BASE_QUERY)

    fun onSearch(query: String) {
        currentQuery.value = query
    }

    val photos = currentQuery.switchMap { query ->
        repository.searchPhotos(query)
    }.cachedIn(viewModelScope)

    companion object {
        private const val BASE_QUERY = "cats"
        private const val CURRENT_QUERY ="current_query"
    }
}