package com.core.network.model

class NewsResponse : ArrayList<NewsResponseItem>()

data class NewsResponseItem(
    val image_url: String,
    val pub_date: String,
    val source: String,
    val summary: String,
    val title: String,
    val topics: List<String>,
    val url: String
)