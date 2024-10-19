package com.matin.amazingshop.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity("tbl_item_status")
class ItemStatusEntity(
    @PrimaryKey
    val id: String,
    val image: String,
    val brand: String,
    val name: String,
    val price: BigDecimal,
    val isInCart: Boolean,
    val isInWishlist: Boolean
)

