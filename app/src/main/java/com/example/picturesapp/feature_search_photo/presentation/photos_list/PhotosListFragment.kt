package com.example.picturesapp.feature_search_photo.presentation.photos_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.picturesapp.R
import com.example.picturesapp.core.ui.BaseFragment
import com.example.picturesapp.databinding.FragmentPhotosListBinding
import com.example.picturesapp.feature_search_photo.domain.model.ResultDomain
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotosListFragment : BaseFragment<FragmentPhotosListBinding>() {

    private val viewModel by viewModel<PhotosListViewModel>()
    private var adapter: PhotosListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.photos.collect { data ->
                processData(data)
            }
        }
    }

    private suspend fun processData(data: PagingData<ResultDomain>) {
        adapter?.submitData(data)
    }

    private fun setupAdapter() {
        adapter = PhotosListAdapter()

        binding.recyclerView.adapter = adapter?.withLoadStateHeaderAndFooter(
            footer = PhotoLoadStateAdapter(),
            header = PhotoLoadStateAdapter()
        )

        adapter?.addLoadStateListener { state ->
            binding.progressBar.isVisible = state.refresh == LoadState.Loading
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPhotosListBinding.inflate(inflater, container, false)
}