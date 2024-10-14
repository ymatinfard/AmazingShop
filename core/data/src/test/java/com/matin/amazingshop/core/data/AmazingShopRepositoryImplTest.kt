package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.data.testdouble.TestAmazingShopApi
import com.matin.amazingshop.core.model.Banner
import com.matin.amazingshop.core.model.Image
import com.matin.amazingshop.core.model.Item
import com.matin.amazingshop.core.model.ProductsData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class AmazingShopRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()
    private val api = TestAmazingShopApi()
    private lateinit var repository: AmazingShopRepository

    @Before
    fun setup() {
        repository = AmazingShopRepositoryImpl(ioDispatcher = testDispatcher, api)
    }

    @Test
    fun getCatalog_returns_product_list_from_server() = runTest(testDispatcher) {
        val result = repository.getCatalog().first()
        assertEquals(productsData, result)
    }

    private val productsData = ProductsData(
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
            )
        )
    )

}