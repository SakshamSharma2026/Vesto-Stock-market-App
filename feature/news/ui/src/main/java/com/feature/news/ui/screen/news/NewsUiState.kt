package com.feature.news.ui.screen.news

import com.feature.news.domain.model.News

data class NewsUiState(
    val isLoading: Boolean = false,
    val news: List<News> = emptyList(),
    val error: String? = null,
    val isOffline: Boolean = false
)
