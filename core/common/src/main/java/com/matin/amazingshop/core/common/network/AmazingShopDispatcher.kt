package com.matin.amazingshop.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val amazingShopDispatchers: AmazingShopDispatcher)

enum class AmazingShopDispatcher {
    Default,
    IO,
}
