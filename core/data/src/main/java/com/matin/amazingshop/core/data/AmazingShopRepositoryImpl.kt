package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.common.network.AmazingShopDispatcher.IO
import com.matin.amazingshop.core.common.network.Dispatcher
import com.matin.amazingshop.core.model.ProductsData
import com.matin.amazingshop.core.network.AmazingShopApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.matin.amazingshop.core.common.Result
import javax.inject.Inject

class AmazingShopRepositoryImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val api: AmazingShopApi
) : AmazingShopRepository {
    override suspend fun getProducts(): Result<ProductsData> = withContext(ioDispatcher) {
        try {
            val productsData = api.getProducts().toDomain()
            Result.Success(productsData)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}