package com.matin.amazingshop.feature.catalog

import com.matin.amazingshop.core.common.asResult
import com.matin.amazingshop.core.model.Catalog
import com.matin.amazingshop.core.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import com.matin.amazingshop.core.common.Result
import javax.inject.Inject

class CatalogStateMapper @Inject constructor() {
    fun map(catalog: Flow<Catalog>, itemsStatus: Flow<List<Item>>): Flow<CatalogUiState> {
        return combine(catalog, itemsStatus, ::Pair).asResult().map { catalogToItemsStatus ->
            when (catalogToItemsStatus) {
                is Result.Success -> {
                    val catalogUi = catalogToItemsStatus.data.first
                    val itemsStatusMap = catalogToItemsStatus.data.second.associateBy { it.id }
                    val catalogItemsUi = catalogUi.items.map { catalogItem ->
                        itemsStatusMap[catalogItem.id]?.let {
                            catalogItem.copy(isInWishlist = it.isInWishlist, isInCart = it.isInCart)
                        } ?: catalogItem
                    }
                    CatalogUiState.Success(catalogUi.copy(items = catalogItemsUi))
                }

                is Result.Error -> CatalogUiState.Error(catalogToItemsStatus.exception.message.toString())
                is Result.Loading -> CatalogUiState.Loading
                else -> CatalogUiState.Loading
            }
        }
    }
}