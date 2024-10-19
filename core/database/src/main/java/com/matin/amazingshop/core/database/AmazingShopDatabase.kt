package com.matin.amazingshop.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ItemStatusEntity::class], version = 2)
@TypeConverters(BigDecimalTypeConvertor::class)
abstract class AmazingShopDatabase : RoomDatabase() {

    abstract fun itemStatusDao(): ItemStatusDao
}
