package com.example.picturesapp.feature_search_photo.presentation.photos_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.picturesapp.R
import com.example.picturesapp.databinding.PhotoItemBinding
import com.example.picturesapp.feature_search_photo.domain.model.ResultDomain

class PhotosListAdapter(
    private val onGoToDetail: (ResultDomain) -> Unit
) :
    PagingDataAdapter<ResultDomain, PhotosListAdapter.PhotosViewHolder>(DiffCallback) {

    class PhotosViewHolder(
        private val onGoToDetail: (ResultDomain) -> Unit,
        private val binding: PhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(resultDomain: ResultDomain) {
            Glide.with(itemView)
                .load(resultDomain.urls.raw)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivPhoto)
            binding.tvUsername.text = resultDomain.user.name

            binding.root.setOnClickListener { onGoToDetail(resultDomain) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PhotosViewHolder(
            onGoToDetail,
            PhotoItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResultDomain>() {
        override fun areItemsTheSame(oldItem: ResultDomain, newItem: ResultDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultDomain, newItem: ResultDomain): Boolean {
            return oldItem == newItem
        }
    }
}