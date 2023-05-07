package com.unsplashtestassignment.data.api.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String
) : Parcelable