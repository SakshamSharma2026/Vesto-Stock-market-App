package com.saksham.sharma.vesto.features.data.model

data class RecosBar(
    val isDataPresent: Boolean,
    val meanValue: Double,
    val noOfRecommendations: Int,
    val stockAnalyst: List<StockAnalyst>,
    val tickerPercentage: Double,
    val tickerRatingValue: Int
)