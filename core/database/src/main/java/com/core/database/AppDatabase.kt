package com.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.database.dao.UserDao
import com.core.database.dao.WalletDao


@Database(entities = [WalletEntity::class, UserAccountEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
    abstract fun userDao(): UserDao
}