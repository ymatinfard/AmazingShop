package com.matin.amazingshop.feature.wishlist.navigation

import androidx.navigation.NavController

const val WISHLIST_ROUTE = "whishlist_route"

fun NavController.navigateToWishlist() = navigate(WISHLIST_ROUTE)