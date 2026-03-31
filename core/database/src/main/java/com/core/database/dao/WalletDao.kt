package com.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.core.database.WalletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMoney(wallet: WalletEntity)


    @Query("SELECT * FROM wallet_table WHERE id = 1")
    fun getWalletDetails(): Flow<WalletEntity?>
}