package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.model.Banner
import com.matin.amazingshop.core.model.Image
import com.matin.amazingshop.core.model.Items
import com.matin.amazingshop.core.model.ProductsData
import com.matin.amazingshop.core.network.model.NetworkBanner
import com.matin.amazingshop.core.network.model.NetworkImage
import com.matin.amazingshop.core.network.model.NetworkItems
import com.matin.amazingshop.core.network.model.NetworkProductsData

fun NetworkProductsData.toDomain(): ProductsData {
    return ProductsData(
        title = title.orEmpty(),
        currency = currency.orEmpty(),
        banner = banner?.toDomain() ?: Banner("", ""),
        items = items?.map { it.toDomain() } ?: emptyList())
}

fun NetworkBanner.toDomain(): Banner {
    return Banner(
        title = title.orEmpty(),
        message = message.orEmpty()
    )
}

fun NetworkItems.toDomain(): Items {
    return Items(
        id = id.orEmpty(),
        sku = sku.orEmpty(),
        image = image.toDomain(),
        brand = brand.orEmpty(),
        name = name.orEmpty(),
        badges = badges,
        price = price?.toBigDecimal() ?: 0.toBigDecimal()
    )
}

fun NetworkImage.toDomain(): Image {
    return Image(
        url = url.orEmpty(),
        width = width ?: 0,
        height = height ?: 0
    )
}