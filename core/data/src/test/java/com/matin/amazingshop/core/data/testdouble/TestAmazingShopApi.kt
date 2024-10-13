package com.matin.amazingshop.core.data.testdouble

import com.matin.amazingshop.core.network.AmazingShopApi
import com.matin.amazingshop.core.network.model.NetworkBanner
import com.matin.amazingshop.core.network.model.NetworkImage
import com.matin.amazingshop.core.network.model.NetworkItem
import com.matin.amazingshop.core.network.model.NetworkCatalog

class TestAmazingShopApi : AmazingShopApi {

    override suspend fun getCatalog(): NetworkCatalog {
        return fakeNetworkCatalog
    }

    val fakeNetworkCatalog = NetworkCatalog(
        title = "New items",
        currency = "euro",
        banner = NetworkBanner("banner title", "banner message"),
        items = arrayListOf(
            NetworkItem(
                id = "1",
                name = "Item name",
                sku = "sku",
                brand = "Brand name1",
                image = NetworkImage(
                    url = "https://example.com/im1",
                    50,
                    60
                ),
                price = 9.99,
                badges = arrayListOf("badge1", "badge2"),
            ),
            NetworkItem(
                id = "2",
                name = "Item name",
                sku = "sku",
                brand = "Brand name2",
                image = NetworkImage(
                    url = "https://example.com/im1",
                    50,
                    60
                ),
                price = 10.99,
                badges = arrayListOf("badge1", "badge2"),
            ),
            NetworkItem(
                id = "3",
                name = "Item name",
                sku = "sku",
                brand = "Brand name3",
                image = NetworkImage(
                    url = "https://example.com/im1",
                    50,
                    60
                ),
                price = 11.99,
                badges = arrayListOf("badge1", "badge2"),
            ),
            NetworkItem(
                id = "4",
                name = "Item name",
                sku = "sku",
                brand = "Brand name4",
                image = NetworkImage(
                    url = "https://example.com/im1",
                    50,
                    60
                ),
                price = 12.99,
                badges = arrayListOf("badge1", "badge2"),
            )
        )
    )
}