package com.feature.news.data.mapper

import com.core.network.model.NewsResponse
import com.feature.news.domain.model.News

fun NewsResponse.toDomain(): List<News> {
    return this.map {
        News(
            imageUrl = it.image_url,
            pubDate = it.pub_date,
            source = it.source,
            summary = it.summary,
            title = it.title,
            topics = it.topics,
            url = it.url
        )
    }
}
