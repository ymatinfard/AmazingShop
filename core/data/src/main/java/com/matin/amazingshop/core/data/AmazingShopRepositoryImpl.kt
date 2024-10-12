package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.model.ProductsData
import com.matin.amazingshop.core.network.AmazingShopApi
import com.matin.happystore.core.common.network.AmazingShopDispatcher
import com.matin.happystore.core.common.network.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AmazingShopRepositoryImpl @Inject constructor(
    @Dispatcher(AmazingShopDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val api: AmazingShopApi
) : AmazingShopRepository {
    override suspend fun getProducts(): ProductsData = withContext(ioDispatcher) {
        api.getProducts().toDomain()
    }
}