package com.matin.amazingshop.core.model

import java.math.BigDecimal

data class Item(
    val id: String,
    val sku: String,
    val image: Image,
    val brand: String,
    val name: String,
    val price: BigDecimal,
    val badges: ArrayList<String>,
    var isInWishlist: Boolean
)