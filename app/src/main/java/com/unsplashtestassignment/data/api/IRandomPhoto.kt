package com.unsplashtestassignment.data.api

import com.unsplashtestassignment.data.api.entities.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRandomPhoto {
    @GET("photos/random?${RetrofitConstants.param_client_id}&${RetrofitConstants.param_orientation}")
    suspend fun getRandomPhotos(@Query("count") count: Int): Response<List<Photo>>
}