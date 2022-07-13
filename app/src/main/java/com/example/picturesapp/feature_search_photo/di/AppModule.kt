package com.example.picturesapp.feature_search_photo.di

import androidx.lifecycle.SavedStateHandle
import com.example.picturesapp.feature_search_photo.presentation.photos_detail.PhotosDetailViewModel
import com.example.picturesapp.feature_search_photo.presentation.photos_list.PhotosListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { (handle: SavedStateHandle) ->
        PhotosListViewModel(
            repository = get(),
            state = handle
        )
    }

    viewModel { (handle: SavedStateHandle) ->
        PhotosDetailViewModel(state = handle)
    }
}