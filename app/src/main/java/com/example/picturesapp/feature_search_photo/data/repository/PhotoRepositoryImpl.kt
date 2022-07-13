package com.example.picturesapp.feature_search_photo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.picturesapp.feature_search_photo.data.remote.PhotoApi
import com.example.picturesapp.feature_search_photo.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val api: PhotoApi
) : PhotoRepository {

    override fun searchPhotos(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PhotoPagingSource(api, query) }
    ).liveData
}