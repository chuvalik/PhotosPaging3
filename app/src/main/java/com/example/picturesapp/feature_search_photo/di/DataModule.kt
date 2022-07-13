package com.example.picturesapp.feature_search_photo.di

import com.example.picturesapp.feature_search_photo.data.remote.PhotoApi
import com.example.picturesapp.feature_search_photo.data.repository.PhotoRepositoryImpl
import com.example.picturesapp.feature_search_photo.domain.repository.PhotoRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<PhotoRepository> {
        PhotoRepositoryImpl(api = get())
    }

    single<PhotoApi> {
        Retrofit.Builder()
            .baseUrl(PhotoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoApi::class.java)
    }
}