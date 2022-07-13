package com.example.picturesapp.feature_search_photo.presentation.photos_list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
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
            processData(data)
        }
    }

    private fun processData(data: PagingData<ResultDomain>) {
        adapter?.submitData(viewLifecycleOwner.lifecycle, data)
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
        adapter = PhotosListAdapter { result ->
            processNavigationToDetail(result)
        }

        binding.recyclerView.adapter = adapter?.withLoadStateHeaderAndFooter(
            footer = PhotosLoadStateAdapter(),
            header = PhotosLoadStateAdapter()
        )
        binding.recyclerView.itemAnimator = null

        adapter?.addLoadStateListener { state ->
            processLoadState(state)
        }
    }

    private fun processNavigationToDetail(result: ResultDomain) {
        val action = PhotosListFragmentDirections.photosToDetail(result)
        findNavController().navigate(action)
    }

    private fun processLoadState(state: CombinedLoadStates) {
        binding.progressBar.isVisible = state.refresh is LoadState.Loading
        binding.recyclerView.isVisible = state.refresh is LoadState.NotLoading
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPhotosListBinding.inflate(inflater, container, false)
}