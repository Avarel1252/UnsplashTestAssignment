package com.unsplashtestassignment.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DbPhotoEntity::class], version = 1)
abstract class PhotosDatabase : RoomDatabase() {
    abstract val dao: Dao

    companion object {
        fun create(context: Context) = Room
            .databaseBuilder(
                context,
                PhotosDatabase::class.java,
                "photos-database"
            )
            .build()
    }
}