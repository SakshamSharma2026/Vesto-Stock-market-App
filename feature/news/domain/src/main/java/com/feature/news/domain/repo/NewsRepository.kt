package com.feature.news.domain.repo

import com.feature.news.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(): Flow<List<News>>
}

