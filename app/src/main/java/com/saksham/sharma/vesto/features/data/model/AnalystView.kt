package com.saksham.sharma.vesto.features.data.model

data class AnalystView(
    val colorCode: String,
    val numberOfAnalysts1MonthAgo: String,
    val numberOfAnalysts1WeekAgo: String,
    val numberOfAnalysts2MonthAgo: String,
    val numberOfAnalysts3MonthAgo: String,
    val numberOfAnalystsLatest: String,
    val ratingName: String,
    val ratingValue: Int
)