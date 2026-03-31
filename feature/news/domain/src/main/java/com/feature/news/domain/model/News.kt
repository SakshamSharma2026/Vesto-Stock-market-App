package com.feature.news.domain.model

data class News(
    val imageUrl: String,
    val pubDate: String,
    val source: String,
    val summary: String,
    val title: String,
    val topics: List<String>,
    val url: String
)