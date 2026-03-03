package com.saksham.sharma.vesto.features.data.model

data class RecentNew(
    val date: String,
    val headline: String,
    val id: Long,
    val lastPublishedDate: String,
    val leadMedia: LeadMedia,
    val listimage: String,
    val metadata: Metadata,
    val summary: String,
    val thumbnailImage: String,
    val timeToRead: Int,
    val url: String,
    val videoBody: Any
)