package com.feature.stock.ui.di

import com.feature.stock.ui.navigation.StockApi
import com.feature.stock.ui.navigation.StockApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return StockApiImpl()
    }

}