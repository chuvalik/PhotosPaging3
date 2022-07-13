package com.example.picturesapp.feature_search_photo.presentation.photos_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.picturesapp.databinding.PhotoLoadStateFooterBinding

class PhotosLoadStateAdapter : LoadStateAdapter<PhotosLoadStateAdapter.PhotoLoadStateViewHolder>() {

    class PhotoLoadStateViewHolder(
        private val binding: PhotoLoadStateFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            Log.d("TestState", "$loadState bind")
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.btnRetry.isVisible = loadState is LoadState.Error
            binding.tvError.isVisible = loadState is LoadState.Error
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PhotoLoadStateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PhotoLoadStateViewHolder(
            PhotoLoadStateFooterBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotoLoadStateViewHolder, loadState: LoadState) {
        Log.d("TestState", "$loadState onBind")
        holder.bind(loadState)
    }

}