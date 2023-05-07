package com.unsplashtestassignment.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadPhoto(userPhoto: String) {
    Glide.with(this)
        .load(userPhoto)
        .load(userPhoto)
        .into(this)
}