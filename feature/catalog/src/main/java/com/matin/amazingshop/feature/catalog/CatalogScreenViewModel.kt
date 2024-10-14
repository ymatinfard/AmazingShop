package com.matin.amazingshop.feature.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.amazingshop.core.common.Result
import com.matin.amazingshop.core.common.asResult
import com.matin.amazingshop.core.data.AmazingShopRepository
import com.matin.amazingshop.core.model.Catalog
import com.matin.amazingshop.core.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor(private val repository: AmazingShopRepository) :
    ViewModel() {

    val catalogUiState: StateFlow<CatalogUiState> =
        catalogUiState(repository.getCatalog(), repository.getWishlist())
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CatalogUiState.Loading
            )

    private fun catalogUiState(
        catalog: Flow<Catalog>,
        wishlist: Flow<List<Item>>
    ): Flow<CatalogUiState> {
        return combine(catalog, wishlist, ::Pair).asResult()
            .map { catalogToWishlist ->
                when (catalogToWishlist) {
                    is Result.Success -> {
                        val catalogUi = catalogToWishlist.data.first
                        val catalogItemsUi = catalogUi.items.map { catalogItem ->
                            if (catalogToWishlist.data.second.any { it.id == catalogItem.id }) {
                                catalogItem.copy(isInWishlist = true)
                            } else {
                                catalogItem
                            }
                        }
                        CatalogUiState.Success(catalogUi.copy(items = catalogItemsUi))
                    }

                    is Result.Error -> CatalogUiState.Error(catalogToWishlist.exception.message.toString())
                    is Result.Loading -> CatalogUiState.Loading
                    else -> CatalogUiState.Loading
                }
            }
    }

    fun addToWishList(item: Item) {
        viewModelScope.launch {
            repository.addToWishlist(item)
        }
    }

    fun removeFromWishList(item: Item) {
        viewModelScope.launch {
            repository.removeFromWishlist(item)
        }
    }
}

sealed interface CatalogUiState {
    data class Success(val data: Catalog) : CatalogUiState
    data class Error(val message: String) : CatalogUiState
    data object Loading : CatalogUiState
}