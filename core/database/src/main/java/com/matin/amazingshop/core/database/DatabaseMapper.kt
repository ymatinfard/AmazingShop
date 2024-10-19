package com.matin.amazingshop.core.database

import com.matin.amazingshop.core.model.Image
import com.matin.amazingshop.core.model.Item

fun Item.toEntity(): ItemStatusEntity {
    return ItemStatusEntity(
        id = id,
        name = name,
        brand = brand,
        image = image.url,
        price = price,
        isInWishlist = isInWishlist,
        isInCart = isInCart
    )
}

fun ItemStatusEntity.toDomain(): Item {
    return Item(
        id = id,
        name = name,
        brand = brand,
        image = Image(image, 0, 0),
        price = price,
        sku = "",
        badges = arrayListOf(),
        isInWishlist = isInWishlist,
        isInCart = isInCart
    )
}