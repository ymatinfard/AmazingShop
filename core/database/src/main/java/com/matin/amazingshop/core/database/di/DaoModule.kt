package com.matin.amazingshop.core.database.di

import com.matin.amazingshop.core.database.AmazingShopDatabase
import com.matin.amazingshop.core.database.WishlistDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideWishlistDao(database: AmazingShopDatabase): WishlistDao = database.wishlistDao()
}
