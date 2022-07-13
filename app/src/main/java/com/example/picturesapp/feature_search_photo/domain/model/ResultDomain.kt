package com.example.picturesapp.feature_search_photo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultDomain(
    val description: String?,
    val id: String,
    val urls: UrlsDomain,
    val user: UserDomain,
): Parcelable {

    @Parcelize
    data class UrlsDomain(
        val full: String,
        val raw: String,
    ): Parcelable

    @Parcelize
    data class UserDomain(
        val name: String,
    ): Parcelable

}
