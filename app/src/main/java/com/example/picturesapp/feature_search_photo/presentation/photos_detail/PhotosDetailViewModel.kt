package com.example.picturesapp.feature_search_photo.presentation.photos_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.picturesapp.feature_search_photo.domain.model.ResultDomain

class PhotosDetailViewModel(
    state: SavedStateHandle
) : ViewModel() {

    val photo = state.getLiveData<ResultDomain>("photo")
}