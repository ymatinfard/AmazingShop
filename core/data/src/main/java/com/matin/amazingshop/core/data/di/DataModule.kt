package com.matin.amazingshop.core.data.di

import com.matin.amazingshop.core.data.AmazingShopRepository
import com.matin.amazingshop.core.data.AmazingShopRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun provideRepository(repositoryImpl: AmazingShopRepositoryImpl): AmazingShopRepository
}