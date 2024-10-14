package com.matin.amazingshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matin.amazingshop.feature.catalog.navigation.CATALOG_ROUTE
import com.matin.amazingshop.feature.catalog.navigation.catalogScreen
import com.matin.amazingshop.feature.wishlist.navigation.WISHLIST_ROUTE
import com.matin.amazingshop.feature.wishlist.navigation.wishlistScreen

@Composable
fun AmazingShopNavHost() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = CATALOG_ROUTE) {
        catalogScreen()
        wishlistScreen()
    }
}