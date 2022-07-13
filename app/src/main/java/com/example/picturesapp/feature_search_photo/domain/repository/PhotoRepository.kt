package com.example.picturesapp.feature_search_photo.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.picturesapp.feature_search_photo.domain.model.ResultDomain
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    fun searchPhotos(query: String): LiveData<PagingData<ResultDomain>>
}