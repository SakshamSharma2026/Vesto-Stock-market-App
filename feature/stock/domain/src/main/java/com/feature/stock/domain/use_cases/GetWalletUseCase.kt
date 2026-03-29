package com.feature.stock.domain.use_cases

import com.core.database.WalletEntity
import com.feature.stock.domain.repo.WalletRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWalletUseCase @Inject constructor(private val walletRepository: WalletRepository) {

    suspend fun addMoney(amount: Double) {
        withContext(Dispatchers.IO) {
            walletRepository.addMoney(amount)
        }
    }

    fun getWalletDetails(): Flow<WalletEntity?> {
        return walletRepository.getWalletDetails()
    }

}