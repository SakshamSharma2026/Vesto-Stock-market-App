package com.feature.stock.data.dto

data class TrendingStocks(
    val top_gainers: List<TopGainer>,
    val top_losers: List<TopLoser>
)