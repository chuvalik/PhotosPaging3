package com.example.picturesapp.feature_search_photo.data.remote.model

data class ResultDto(
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>?,
    val description: String?,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: LinksDto,
    val urls: UrlsDto,
    val user: UserDto,
    val width: Int
)