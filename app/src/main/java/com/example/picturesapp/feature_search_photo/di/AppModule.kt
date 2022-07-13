package com.example.picturesapp.feature_search_photo.di

import com.example.picturesapp.feature_search_photo.presentation.photos_list.PhotosListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        PhotosListViewModel(repository = get())
    }
}