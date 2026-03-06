package com.saksham.sharma.vesto.di

import com.feature.stock.ui.navigation.StockApi
import com.saksham.sharma.vesto.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideNavigationProvider(
        stockApi: StockApi,
    ): NavigationProvider {
        return NavigationProvider(stockApi)
    }
}