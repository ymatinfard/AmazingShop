package com.matin.amazingshop.core.database

import com.matin.amazingshop.core.model.Image
import com.matin.amazingshop.core.model.Item

fun Item.toEntity(): WishEntity {
    return WishEntity(
        id = id,
        name = name,
        brand = brand,
        image = image.url,
        price = price,
    )
}

fun WishEntity.toDomain(): Item {
    return Item(
        id = id,
        name = name,
        brand = brand,
        image = Image(image, 0, 0),
        price = price,
        sku = "",
        badges = arrayListOf(),
        isInWishlist = true
    )
}