package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.database.ItemStatusDao
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
    private val dao: ItemStatusDao,
) : AmazingShopRepository {

    override fun getCatalog(): Flow<Catalog> = flow {
        emit(api.getCatalog().toDomain())
    }

    override fun getWishlist(): Flow<List<Item>> =
        dao.getWishlist().map { it.map { it.toDomain() } }

    override fun getItemsStatus(): Flow<List<Item>> =
        dao.getItemsStatus().map { it.map { it.toDomain() } }

    override suspend fun toggleWishlist(item: Item) {
        val existingItem = dao.getItemById(item.id)
        if (existingItem != null) {
            val newWishlistStatus = !existingItem.isInWishlist
            dao.updateWishlistStatus(item.id, newWishlistStatus)

            // After toggling, check if we need to remove the item
            if (!existingItem.isInCart && !newWishlistStatus) {
                // Both flags are false, remove the item from the database
                dao.removeItemById(item.id)
            }
        } else {
            dao.insert(item.copy(isInWishlist = true).toEntity())
        }
    }

    override suspend fun toggleCart(item: Item) {
        val existingItem = dao.getItemById(item.id)
        if (existingItem != null) {
            val newCartStatus = !existingItem.isInCart
            dao.updateCartStatus(item.id, newCartStatus)

            // After toggling, check if we need to remove the item
            if (!existingItem.isInWishlist && !newCartStatus) {
                // Both flags are false, remove the item from the database
                dao.removeItemById(item.id)
            }
        } else {
            dao.insert(item.copy(isInCart = true).toEntity())
        }
    }
}