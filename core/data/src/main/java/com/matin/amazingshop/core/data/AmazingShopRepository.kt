package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.model.ProductsData
import kotlinx.coroutines.flow.Flow

interface AmazingShopRepository {
    fun getCatalog(): Flow<ProductsData>
}