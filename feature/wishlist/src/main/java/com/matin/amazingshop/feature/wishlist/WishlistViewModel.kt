package com.matin.amazingshop.feature.wishlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.amazingshop.core.data.AmazingShopRepository
import com.matin.amazingshop.core.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(private val repository: AmazingShopRepository) :
    ViewModel() {

    val wishlist: StateFlow<List<Item>> = repository.getWishlist().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun removeFromWishlist(item: Item) {
        viewModelScope.launch {
            runCatching {
                repository.toggleWishlist(item)
            }.onFailure {
                Log.e("Error in DB", "Cannot remove from wishlist")
            }
        }
    }
}