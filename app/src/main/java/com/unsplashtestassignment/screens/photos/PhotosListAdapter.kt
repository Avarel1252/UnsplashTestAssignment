package com.unsplashtestassignment.screens.photos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unsplashtestassignment.data.room.DbPhotoEntity
import com.unsplashtestassignment.databinding.ListItemBinding
import com.unsplashtestassignment.utils.extensions.loadPhoto


class PhotosListAdapter(private val listener: PhotosAdapterListener) :
    ListAdapter<DbPhotoEntity, PhotosListAdapter.PhotoViewHolder>(PhotosDiffCallback) {

    inner class PhotoViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(photo: DbPhotoEntity) {
            with(binding) {
                ivPhoto.loadPhoto(photo.small)
                tvName.text = "ID: ${photo.photo_id}"
                tvAuthor.text = "Author: ${photo.name}"
                root.setOnClickListener {
                    listener.detailView(photo)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::PhotoViewHolder)

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) =
        holder.onBind(getItem(position))

}

object PhotosDiffCallback : DiffUtil.ItemCallback<DbPhotoEntity>() {
    override fun areItemsTheSame(oldItem: DbPhotoEntity, newItem: DbPhotoEntity) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DbPhotoEntity, newItem: DbPhotoEntity) =
        oldItem == newItem
}
