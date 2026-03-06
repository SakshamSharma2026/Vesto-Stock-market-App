package com.feature.stock.data.di

import com.feature.stock.data.remote.ApiService
import com.feature.stock.data.remote.StockDataProviders
import com.feature.stock.data.repo.StockRepositoryImpl
import com.feature.stock.domain.repo.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideStockRepo(stockDataProviders: StockDataProviders): StockRepository {
        return StockRepositoryImpl(stockDataProviders)

    }
}