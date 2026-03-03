package com.saksham.sharma.vesto.features.data.model

data class TrendingStocks(
    val top_gainers: List<TopGainer>,
    val top_losers: List<TopLoser>
)