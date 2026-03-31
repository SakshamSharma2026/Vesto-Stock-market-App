package com.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet_table")
data class WalletEntity(
    @PrimaryKey val id: Int = 1,
    val balance: Double = 0.0,
    val timeStamp: Long = System.currentTimeMillis()
)