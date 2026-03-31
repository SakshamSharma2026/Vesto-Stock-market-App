package com.feature.stock.data.di

import com.feature.stock.data.local.WalletDataProviders
import com.feature.stock.data.remote.StockDataProviders
import com.feature.stock.data.repo.StockRepositoryImpl
import com.feature.stock.data.repo.WalletRepositoryImpl
import com.feature.stock.domain.repo.StockRepository
import com.feature.stock.domain.repo.WalletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Singleton
    @Provides
    fun provideStockRepo(stockDataProviders: StockDataProviders): StockRepository {
        return StockRepositoryImpl(stockDataProviders)
    }

    @Singleton
    @Provides
    fun provideWalletRepo(walletDataProviders: WalletDataProviders): WalletRepository {
        return WalletRepositoryImpl(walletDataProviders)
    }

}