package com.example.picturesapp.feature_search_photo.presentation.photos_detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.picturesapp.core.ui.BaseFragment
import com.example.picturesapp.databinding.FragmentPhotosDetailBinding
import com.example.picturesapp.feature_search_photo.domain.model.ResultDomain
import org.koin.androidx.viewmodel.ext.android.stateViewModel


class PhotosDetailFragment : BaseFragment<FragmentPhotosDetailBinding>() {

    private val viewModel by stateViewModel<PhotosDetailViewModel>(state = {
        val bundle = Bundle()
        bundle.putParcelable("photo", arguments?.getParcelable("photo"))
        bundle
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observePhoto()
    }

    private fun observePhoto() {
        viewModel.photo.observe(viewLifecycleOwner) { photo ->
            processPhoto(photo)
        }
    }

    private fun processPhoto(photo: ResultDomain) {
        Glide.with(this@PhotosDetailFragment)
            .load(photo.urls.full)
            .listener(setupGlideRequestListener())
            .into(binding.ivPhoto)
        binding.tvName.text = photo.user.name
        binding.tvContent.text = photo.description
    }

    private fun setupGlideRequestListener() = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            binding.progressBar.isVisible = false
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            binding.progressBar.isVisible = false
            binding.tvName.isVisible = true
            binding.tvContent.isVisible = true
            return false
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPhotosDetailBinding.inflate(inflater, container, false)
}