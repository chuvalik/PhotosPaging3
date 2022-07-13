package com.example.picturesapp.feature_search_photo.presentation.photos_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.picturesapp.feature_search_photo.domain.model.ResultDomain
import com.example.picturesapp.feature_search_photo.domain.repository.PhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

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