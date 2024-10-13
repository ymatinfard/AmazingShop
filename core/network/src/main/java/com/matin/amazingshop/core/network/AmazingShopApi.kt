package com.matin.amazingshop.core.network

import com.matin.amazingshop.core.network.model.NetworkProductsData
import retrofit2.http.GET

interface AmazingShopApi {
    @GET("v3/3508850b-c63d-47b2-b1f8-b559cdd5327d")
    suspend fun getProducts(): NetworkProductsData
}