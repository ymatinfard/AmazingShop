package com.matin.amazingshop.core.data

import com.matin.amazingshop.core.common.network.AmazingShopDispatcher.IO
import com.matin.amazingshop.core.common.network.Dispatcher
import com.matin.amazingshop.core.model.ProductsData
import com.matin.amazingshop.core.network.AmazingShopApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AmazingShopRepositoryImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val api: AmazingShopApi
) : AmazingShopRepository {
    override fun getCatalog(): Flow<ProductsData> = flow {
        emit(api.getCatalog().toDomain())
    }
}