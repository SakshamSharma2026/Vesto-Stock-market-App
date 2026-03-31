package com.feature.news.ui.di

import com.feature.news.ui.navigation.NewsApi
import com.feature.news.ui.navigation.NewsApiImpl
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
    fun provideNewsApi(): NewsApi {
        return NewsApiImpl()
    }

}
