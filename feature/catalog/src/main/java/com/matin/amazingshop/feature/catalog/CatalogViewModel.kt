package com.matin.amazingshop.feature.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.amazingshop.core.data.AmazingShopRepository
import com.matin.amazingshop.core.model.Catalog
import com.matin.amazingshop.core.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: AmazingShopRepository,
    private val catalogStateMapper: CatalogStateMapper
) : ViewModel() {

    val catalogUiState: StateFlow<CatalogUiState> =
        catalogStateMapper.map(repository.getCatalog(), repository.getItemsStatus())
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CatalogUiState.Loading
            )

    var selectedItem = MutableStateFlow<Item?>(null)
        private set

    fun selectItem(item: Item) {
        selectedItem.value = item
    }

    fun onWishlistClick(item: Item) = onItemActionClick(item, repository::toggleWishlist)

    fun onAddToCartClick(item: Item) = onItemActionClick(item, repository::toggleCart)

    private fun onItemActionClick(item: Item, action: suspend (Item) -> Unit) {
        viewModelScope.launch {
            action(item)
        }
    }
}

sealed interface CatalogUiState {
    data class Success(val data: Catalog) : CatalogUiState
    data class Error(val message: String) : CatalogUiState
    data object Loading : CatalogUiState
}