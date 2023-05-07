package com.unsplashtestassignment.data.room

import kotlinx.coroutines.flow.Flow

interface IPhotosRepository {
    fun getPhotos(): Flow<List<DbPhotoEntity>>
    suspend fun add(photo: DbPhotoEntity)
    suspend fun delete(photo: DbPhotoEntity)
    suspend fun deleteAllPhotos()

    suspend fun uploadNewPhotos(count: Int)
}