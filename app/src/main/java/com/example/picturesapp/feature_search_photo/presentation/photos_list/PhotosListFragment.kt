package com.example.picturesapp.feature_search_photo.presentation.photos_list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.picturesapp.R
import com.example.picturesapp.core.ui.BaseFragment
import com.example.picturesapp.databinding.FragmentPhotosListBinding
import com.example.picturesapp.feature_search_photo.domain.model.ResultDomain
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotosListFragment : BaseFragment<FragmentPhotosListBinding>() {

    private val viewModel by viewModel<PhotosListViewModel>()
    private var adapter: PhotosListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeData()

        setHasOptionsMenu(true)
    }

    private fun observeData() {
        viewModel.photos.observe(viewLifecycleOwner) { data ->
            adapter?.submitData(viewLifecycleOwner.lifecycle, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)

        val searchItem = menu.findItem(R.id.actionSearch)
        val searchView = searchItem.actionView as SearchView

        setupOnQueryTextListener(searchView)
    }

    private fun setupOnQueryTextListener(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.onSearch(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
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