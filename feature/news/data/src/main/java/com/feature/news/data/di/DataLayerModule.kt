package com.feature.news.data.di

import com.feature.news.data.remote.NewsDataProviders
import com.feature.news.data.repo.NewsRepositoryImpl
import com.feature.news.domain.repo.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Provides
    fun providesNewsRepo(newsDataProviders: NewsDataProviders): NewsRepository {
        return NewsRepositoryImpl(newsDataProviders)
    }
}