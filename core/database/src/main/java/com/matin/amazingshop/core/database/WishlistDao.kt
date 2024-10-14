package com.matin.amazingshop.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {
    @Query("SELECT * FROM tbl_wishlist")
    fun getWishlist(): Flow<List<WishEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWishlist(wishEntity: WishEntity)

    @Delete
    suspend fun removeFromWishlist(wishEntity: WishEntity)
}
