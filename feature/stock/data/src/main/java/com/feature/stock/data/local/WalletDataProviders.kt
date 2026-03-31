package com.feature.stock.data.local

import com.core.database.WalletEntity
import com.core.database.dao.WalletDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WalletDataProviders @Inject constructor(private val walletDao: WalletDao) {
    suspend fun addMoney(amount: Double) = walletDao.addMoney(WalletEntity(balance = amount))
    fun getWalletDetails(): Flow<WalletEntity?> = walletDao.getWalletDetails()
}