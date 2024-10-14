package com.matin.amazingshop.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [WishlistEntity::class], version = 1)
@TypeConverters(BigDecimalTypeConvertor::class)
abstract class AmazingShopDatabase : RoomDatabase() {

    abstract fun wishlistDao(): WishlistDao
}
