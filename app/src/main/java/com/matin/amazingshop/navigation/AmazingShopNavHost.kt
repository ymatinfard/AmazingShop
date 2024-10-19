package com.matin.amazingshop.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matin.amazingshop.feature.catalog.CatalogViewModel
import com.matin.amazingshop.feature.catalog.navigation.CATALOG_ROUTE
import com.matin.amazingshop.feature.catalog.navigation.catalogScreen
import com.matin.amazingshop.feature.catalog.navigation.itemDetailScreen
import com.matin.amazingshop.feature.catalog.navigation.navigateToItemDetail
import com.matin.amazingshop.feature.wishlist.navigation.navigateToWishlist
import com.matin.amazingshop.feature.wishlist.navigation.wishlistScreen

@Composable
fun AmazingShopNavHost() {
    val navController: NavHostController = rememberNavController()
    val catalogViewModel: CatalogViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = CATALOG_ROUTE) {
        catalogScreen(
            sharedViewModel = catalogViewModel,
            onWishlistClick = { navController.navigateToWishlist() },
            onItemClick = { navController.navigateToItemDetail() })
        wishlistScreen { navController.popBackStack() }
        itemDetailScreen(catalogViewModel) { navController.popBackStack() }
    }
}