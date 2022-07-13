package com.example.picturesapp.feature_search_photo.presentation.photos_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.picturesapp.core.ui.BaseFragment
import com.example.picturesapp.databinding.FragmentPhotosDetailBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel


class PhotosDetailFragment : BaseFragment<FragmentPhotosDetailBinding>() {

    private val viewModel by stateViewModel<PhotosDetailViewModel>(state = {
        val bundle = Bundle()
        bundle.putParcelable("photo", arguments?.getParcelable("photo"))
        bundle
    })

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPhotosDetailBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.photo.observe(viewLifecycleOwner) { photo ->
            binding.tvName.text = photo.user.name
        }
    }
}