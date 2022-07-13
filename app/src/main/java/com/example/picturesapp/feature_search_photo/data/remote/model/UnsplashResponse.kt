package com.example.picturesapp.feature_search_photo.data.remote.model

data class UnsplashResponse(
    val results: List<ResultDto>,
    val total: Int,
    val total_pages: Int
)