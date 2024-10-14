package com.matin.amazingshop.core.database.di

import android.content.Context
import androidx.room.Room
import com.matin.amazingshop.core.database.AmazingShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context,
    ): AmazingShopDatabase = Room.databaseBuilder(context, AmazingShopDatabase::class.java, "amazingshop_db").build()
}