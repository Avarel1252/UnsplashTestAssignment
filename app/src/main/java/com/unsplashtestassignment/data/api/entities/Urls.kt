package com.unsplashtestassignment.data.api.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Urls(
    var raw: String,
    var full: String,
    var small: String
) : Parcelable