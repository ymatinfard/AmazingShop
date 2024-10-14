package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.database.WishlistDao
import com.matin.amazingshop.core.database.toDomain
import com.matin.amazingshop.core.database.toEntity
import com.matin.amazingshop.core.model.Catalog
import com.matin.amazingshop.core.model.Item
import com.matin.amazingshop.core.network.AmazingShopApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmazingShopRepositoryImpl @Inject constructor(
    private val api: AmazingShopApi,
    private val dao: WishlistDao,
) : AmazingShopRepository {

    override fun getCatalog(): Flow<Catalog> = flow {
        emit(api.getCatalog().toDomain())
    }

    override fun getWishlist(): Flow<List<Item>> =
        dao.getWishlist().map { it.map { it.toDomain() } }


    override suspend fun addToWishlist(item: Item) {
        dao.addToWishlist(item.toEntity())
    }

    override suspend fun removeFromWishlist(item: Item) {
        dao.removeFromWishlist(item.toEntity())
    }
}