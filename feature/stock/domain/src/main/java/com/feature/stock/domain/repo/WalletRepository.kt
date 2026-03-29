package com.feature.stock.domain.repo

import com.core.database.WalletEntity
import kotlinx.coroutines.flow.Flow

interface WalletRepository {

    fun addMoney(amount: Double)

    fun getWalletDetails(): Flow<WalletEntity?>

}