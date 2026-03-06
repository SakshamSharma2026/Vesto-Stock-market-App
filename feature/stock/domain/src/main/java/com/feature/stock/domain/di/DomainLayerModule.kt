package com.feature.stock.domain.di

import com.feature.stock.domain.repo.StockRepository
import com.feature.stock.domain.use_cases.GetStockUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {

    @Provides
    @Singleton
    fun provideStockUseCase(stockRepository: StockRepository): GetStockUseCase {
        return GetStockUseCase(stockRepository)
    }

}