package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.model.Banner
import com.matin.amazingshop.core.model.Catalog
import com.matin.amazingshop.core.model.Image
import com.matin.amazingshop.core.model.Item
import com.matin.amazingshop.core.network.model.NetworkBanner
import com.matin.amazingshop.core.network.model.NetworkCatalog
import com.matin.amazingshop.core.network.model.NetworkImage
import com.matin.amazingshop.core.network.model.NetworkItem

fun NetworkCatalog.toDomain(): Catalog {
    return Catalog(
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

fun NetworkItem.toDomain(): Item {
    return Item(
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