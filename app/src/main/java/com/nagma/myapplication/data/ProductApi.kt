package com.nagma.myapplication.data

import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("olive_oils_data.json")
    suspend fun getProducts(): Response<List<Product>>
}