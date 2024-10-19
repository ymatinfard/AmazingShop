package com.matin.amazingshop.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemStatusDao {
    @Query("SELECT * FROM tbl_item_status WHERE isInWishlist = 1")
    fun getWishlist(): Flow<List<ItemStatusEntity>>

    @Query("SELECT * FROM tbl_item_status")
    fun getItemsStatus(): Flow<List<ItemStatusEntity>>

    @Query("SELECT * FROM tbl_item_status WHERE id = :id LIMIT 1")
    suspend fun getItemById(id: String): ItemStatusEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemStatusEntity: ItemStatusEntity)

    @Query("DELETE FROM tbl_item_status WHERE id = :id")
    suspend fun removeItemById(id: String)

    @Query("UPDATE tbl_item_status SET isInWishlist = :isInWishlist WHERE id = :id")
    suspend fun updateWishlistStatus(id: String, isInWishlist: Boolean)

    @Query("UPDATE tbl_item_status SET isInCart = :isInCart WHERE id = :id")
    suspend fun updateCartStatus(id: String, isInCart: Boolean)
}
