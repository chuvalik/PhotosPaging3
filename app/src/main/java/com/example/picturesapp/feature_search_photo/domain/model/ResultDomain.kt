package com.example.picturesapp.feature_search_photo.domain.model

data class ResultDomain(
    val description: String?,
    val id: String,
    val urls: UrlsDomain,
    val user: UserDomain,
) {

    data class UrlsDomain(
        val full: String,
        val raw: String,
    )

    data class UserDomain(
        val name: String,
    )

}
