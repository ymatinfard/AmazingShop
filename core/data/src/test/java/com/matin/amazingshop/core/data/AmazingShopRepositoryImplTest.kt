package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.data.testdouble.TestAmazingShopApi
import com.matin.amazingshop.core.data.testdouble.TestItemStatusDao
import com.matin.amazingshop.core.model.Banner
import com.matin.amazingshop.core.model.Catalog
import com.matin.amazingshop.core.model.Image
import com.matin.amazingshop.core.model.Item
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class AmazingShopRepositoryImplTest {

    private val api = TestAmazingShopApi()
    private val dao = TestItemStatusDao()
    private lateinit var repository: AmazingShopRepository

    @Before
    fun setup() {
        repository = AmazingShopRepositoryImpl(api, dao)
    }

    @Test
    fun getCatalog_returns_product_list_from_server() = runTest {
        val result = repository.getCatalog().first()
        assertEquals(catalog, result)
    }

    private val catalog = Catalog(
        title = "New items",
        currency = "euro",
        banner = Banner("banner title", "banner message"),
        items = arrayListOf(
            Item(
                id = "1",
                name = "Item name",
                sku = "sku",
                brand = "Brand name1",
                image = Image(
                    url = "https://example.com/im1",
                    50,
                    60
                ),
                price = "9.99".toBigDecimal(),
                badges = arrayListOf("badge1", "badge2"),
                isInWishlist = false,
                isInCart = false,
            ),
            Item(
                id = "2",
                name = "Item name",
                sku = "sku",
                brand = "Brand name2",
                image = Image(
                    url = "https://example.com/im1",
                    50,
                    60
                ),
                price = "10.99".toBigDecimal(),
                badges = arrayListOf("badge1", "badge2"),
                isInWishlist = false,
                isInCart = false,
            ),
            Item(
                id = "3",
                name = "Item name",
                sku = "sku",
                brand = "Brand name3",
                image = Image(
                    url = "https://example.com/im1",
                    50,
                    60
                ),
                price = "11.99".toBigDecimal(),
                badges = arrayListOf("badge1", "badge2"),
                isInWishlist = false,
                isInCart = false,
            ),
            Item(
                id = "4",
                name = "Item name",
                sku = "sku",
                brand = "Brand name4",
                image = Image(
                    url = "https://example.com/im1",
                    50,
                    60
                ),
                price = "12.99".toBigDecimal(),
                badges = arrayListOf("badge1", "badge2"),
                isInWishlist = false,
                isInCart = false,
            )
        )
    )

}