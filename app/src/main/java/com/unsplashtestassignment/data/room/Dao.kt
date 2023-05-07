package com.unsplashtestassignment.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Query("SELECT * FROM cachedPhotosTable")
    fun getAllUsers(): Flow<List<DbPhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(user: DbPhotoEntity)

    @Delete
    suspend fun delete(user: DbPhotoEntity)

    @Query("DELETE FROM cachedPhotosTable")
    fun deleteAll()
}