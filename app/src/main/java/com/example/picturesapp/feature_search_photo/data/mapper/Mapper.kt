package com.example.picturesapp.feature_search_photo.data.mapper

import com.example.picturesapp.feature_search_photo.data.remote.model.ResultDto
import com.example.picturesapp.feature_search_photo.data.remote.model.UrlsDto
import com.example.picturesapp.feature_search_photo.data.remote.model.UserDto
import com.example.picturesapp.feature_search_photo.domain.model.ResultDomain

fun ResultDto.toResultDomain(): ResultDomain {
    return ResultDomain(
        description = description,
        id = id,
        urls = urls.toUrlsDomain(),
        user = user.toUserDomain()
    )
}

fun UserDto.toUserDomain(): ResultDomain.UserDomain {
    return ResultDomain.UserDomain(
        name = name
    )
}

fun UrlsDto.toUrlsDomain(): ResultDomain.UrlsDomain {
    return ResultDomain.UrlsDomain(
        full = full,
        raw = raw
    )
}