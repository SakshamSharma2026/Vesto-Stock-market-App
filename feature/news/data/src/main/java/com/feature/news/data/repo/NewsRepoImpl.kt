package com.feature.news.data.repo

import com.feature.news.data.mapper.toDomain
import com.feature.news.data.remote.NewsDataProviders
import com.feature.news.domain.model.News
import com.feature.news.domain.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsDataProviders: NewsDataProviders): NewsRepository {
    override fun getNews(): Flow<List<News>> = flow {
        emit(newsDataProviders.getNews().toDomain())
    }
}