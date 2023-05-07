package com.unsplashtestassignment.data

import android.util.Log
import com.unsplashtestassignment.data.api.RetrofitInstance
import com.unsplashtestassignment.data.room.DbPhotoEntity
import com.unsplashtestassignment.data.room.IPhotosRepository
import com.unsplashtestassignment.data.room.PhotosDatabase
import kotlinx.coroutines.flow.Flow


class PhotosRepository(private val database: PhotosDatabase) : IPhotosRepository {

    override fun getPhotos(): Flow<List<DbPhotoEntity>> {
        return database.dao.getAllUsers()
    }

    override suspend fun delete(photo: DbPhotoEntity) {
        database.dao.delete(photo)
    }

    override suspend fun add(photo: DbPhotoEntity) {
        database.dao.add(photo)
    }

    override suspend fun deleteAllPhotos() {
        database.dao.deleteAll()
    }

    override suspend fun uploadNewPhotos(count: Int) {
        val response = RetrofitInstance.api.getRandomPhotos(count)
        if (response.isSuccessful) {
            response.body()?.onEach { photoFromAPI ->
                add(
                    DbPhotoEntity(
                        photo_id = photoFromAPI.id,
                        name = photoFromAPI.user.name,
                        raw = photoFromAPI.urls.raw,
                        full = photoFromAPI.urls.full,
                        small = photoFromAPI.urls.small
                    )
                )
            }
        } else {
            Log.e("RESPONSE", response.errorBody().toString())
        }
    }
}