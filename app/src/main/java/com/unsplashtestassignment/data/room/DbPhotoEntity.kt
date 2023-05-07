package com.unsplashtestassignment.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "cachedPhotosTable")
@Parcelize
data class DbPhotoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "photo_id")
    var photo_id: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "raw")
    var raw: String,
    @ColumnInfo(name = "full")
    var full: String,
    @ColumnInfo(name = "small")
    var small: String
) : Parcelable