package com.matin.amazingshop.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matin.amazingshop.feature.catalog.CatalogScreen
import com.matin.amazingshop.feature.catalog.CatalogViewModel
import com.matin.amazingshop.feature.catalog.ItemDetailScreen
import com.matin.amazingshop.feature.catalog.navigation.CATALOG_ROUTE
import com.matin.amazingshop.feature.catalog.navigation.ITEM_DETAIL_ROUTE
import com.matin.amazingshop.feature.catalog.navigation.navigateToItemDetail
import com.matin.amazingshop.feature.wishlist.WishlistScreen
import com.matin.amazingshop.feature.wishlist.navigation.WISHLIST_ROUTE
import com.matin.amazingshop.feature.wishlist.navigation.navigateToWishlist

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AmazingShopNavHost() {
    val navController: NavHostController = rememberNavController()
    val catalogViewModel: CatalogViewModel = hiltViewModel()
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = CATALOG_ROUTE) {
            composable(CATALOG_ROUTE) {
                CatalogScreen(
                    viewModel = catalogViewModel,
                    onWishlistClick = { navController.navigateToWishlist() },
                    onItemClick = { navController.navigateToItemDetail() },
                    shardTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable
                )
            }
            composable(WISHLIST_ROUTE) {
                WishlistScreen { navController.popBackStack() }
            }
            composable(ITEM_DETAIL_ROUTE) {
                ItemDetailScreen(
                    catalogViewModel,
                    { navController.popBackStack() },
                    this@SharedTransitionLayout,
                    this@composable
                )
            }
        }
    }
}