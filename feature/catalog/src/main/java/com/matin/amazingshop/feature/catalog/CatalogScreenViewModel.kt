package com.matin.amazingshop.feature.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.amazingshop.core.common.Result
import com.matin.amazingshop.core.common.asResult
import com.matin.amazingshop.core.data.AmazingShopRepository
import com.matin.amazingshop.core.model.Catalog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor(private val repository: AmazingShopRepository) :
    ViewModel() {

    val catalogUiState: StateFlow<CatalogUiState> =
        repository.getCatalog().asResult().map {
            when (it) {
                is Result.Success -> CatalogUiState.Success(it.data)
                is Result.Error -> CatalogUiState.Error(it.exception.message.toString())
                is Result.Loading -> CatalogUiState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CatalogUiState.Loading
        )
}

sealed interface CatalogUiState {
    data class Success(val data: Catalog) : CatalogUiState
    data class Error(val message: String) : CatalogUiState
    data object Loading : CatalogUiState
}