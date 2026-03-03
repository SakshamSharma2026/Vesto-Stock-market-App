package com.saksham.sharma.vesto.features.data.model

data class StockAnalyst(
    val colorCode: String,
    val maxValue: Double,
    val minValue: Double,
    val numberOfAnalysts: Int,
    val ratingName: String,
    val ratingValue: Int
)