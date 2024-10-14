package com.matin.amazingshop.feature.wishlist.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.matin.amazingshop.feature.wishlist.WishlistScreen
import com.matin.amazingshop.feature.wishlist.WishlistViewModel

const val WISHLIST_ROUTE = "whishlist_route"

fun NavController.navigateToWishlist() = navigate(WISHLIST_ROUTE)

fun NavGraphBuilder.wishlistScreen() {
    return composable(WISHLIST_ROUTE) {
        val viewModel = hiltViewModel<WishlistViewModel>()
        WishlistScreen(viewModel)
    }
}