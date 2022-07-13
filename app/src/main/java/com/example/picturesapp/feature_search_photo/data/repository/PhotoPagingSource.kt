package com.example.picturesapp.feature_search_photo.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.picturesapp.feature_search_photo.data.mapper.toResultDomain
import com.example.picturesapp.feature_search_photo.data.remote.PhotoApi
import com.example.picturesapp.feature_search_photo.domain.model.ResultDomain
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class PhotoPagingSource(
    private val api: PhotoApi,
    private val query: String
) : PagingSource<Int, ResultDomain>() {

    override fun getRefreshKey(state: PagingState<Int, ResultDomain>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null

        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDomain> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val response = api.searchPhotos(query, page, pageSize)
            val domainResult = response.results.map { it.toResultDomain() }

            LoadResult.Page(
                data = domainResult,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (domainResult.isEmpty()) null else page + 1
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}