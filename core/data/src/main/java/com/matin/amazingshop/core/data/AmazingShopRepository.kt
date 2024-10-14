package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.model.Catalog
import com.matin.amazingshop.core.model.Item
import kotlinx.coroutines.flow.Flow

interface AmazingShopRepository {
    fun getCatalog(): Flow<Catalog>
    fun getWishlist(): Flow<List<Item>>
    suspend fun addToWishlist(item: Item)
    suspend fun removeFromWishlist(item: Item)
}