package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.common.Result
import com.matin.amazingshop.core.model.ProductsData

interface AmazingShopRepository {
    suspend fun getProducts(): Result<ProductsData>
}