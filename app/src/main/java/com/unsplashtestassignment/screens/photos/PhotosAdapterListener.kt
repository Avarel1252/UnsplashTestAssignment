package com.unsplashtestassignment.screens.photos

import com.unsplashtestassignment.data.room.DbPhotoEntity

interface PhotosAdapterListener {
    fun detailView(photo: DbPhotoEntity)
}