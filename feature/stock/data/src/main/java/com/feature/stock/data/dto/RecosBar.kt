package com.feature.stock.data.dto

data class RecosBar(
    val isDataPresent: Boolean,
    val meanValue: Double,
    val noOfRecommendations: Int,
    val stockAnalyst: List<StockAnalyst>,
    val tickerPercentage: Double,
    val tickerRatingValue: Int
)