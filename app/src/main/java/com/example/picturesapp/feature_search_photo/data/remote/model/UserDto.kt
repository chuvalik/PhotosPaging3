package com.example.picturesapp.feature_search_photo.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    val first_name: String,
    val id: String,
    val instagram_username: String,
    @SerializedName("last_name")
    val lastName: String,
    val links: UserLinksDto,
    val name: String,
    val portfolio_url: String,
    val profile_image: ProfileImageDto,
    val twitter_username: String,
    val username: String
)