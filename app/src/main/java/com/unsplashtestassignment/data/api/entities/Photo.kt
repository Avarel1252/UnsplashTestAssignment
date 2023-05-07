package com.unsplashtestassignment.data.api.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    var id: String,
    var urls: Urls,
    var user: User
) : Parcelable