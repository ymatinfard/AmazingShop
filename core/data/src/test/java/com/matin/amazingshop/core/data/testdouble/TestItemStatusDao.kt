package com.matin.amazingshop.core.data.testdouble

import com.matin.amazingshop.core.database.ItemStatusDao
import com.matin.amazingshop.core.database.ItemStatusEntity
import kotlinx.coroutines.flow.Flow

class TestItemStatusDao: ItemStatusDao {
    override fun getWishlist(): Flow<List<ItemStatusEntity>> {
        TODO("Not yet implemented")
    }

    override fun getItemsStatus(): Flow<List<ItemStatusEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getItemById(id: String): ItemStatusEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun insert(itemStatusEntity: ItemStatusEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun removeItemById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateWishlistStatus(id: String, isInWishlist: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCartStatus(id: String, isInCart: Boolean) {
        TODO("Not yet implemented")
    }
}