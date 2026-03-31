package com.saksham.sharma.vesto.di

import com.feature.auth.ui.navigation.AuthApi
import com.feature.news.ui.navigation.NewsApi
import com.feature.profile.ui.navigation.ProfileApi
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
        newsApi: NewsApi,
        profileApi: ProfileApi,
        authApi: AuthApi
    ): NavigationProvider {
        return NavigationProvider(stockApi, newsApi, profileApi, authApi)
    }
}