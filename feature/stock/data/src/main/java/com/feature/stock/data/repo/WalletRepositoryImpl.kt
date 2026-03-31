package com.feature.stock.data.repo

import com.core.database.WalletEntity
import com.feature.stock.data.local.WalletDataProviders
import com.feature.stock.domain.repo.WalletRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(private val walletDataProviders: WalletDataProviders) :
    WalletRepository {
    override suspend fun addMoney(amount: Double) {
        walletDataProviders.addMoney(amount)
    }

    override fun getWalletDetails(): Flow<WalletEntity?> {
        return walletDataProviders.getWalletDetails()
    }
}