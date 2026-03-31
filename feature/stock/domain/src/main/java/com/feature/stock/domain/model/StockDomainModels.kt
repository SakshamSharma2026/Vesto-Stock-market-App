package com.feature.stock.domain.model

data class StockItem(
    val companyName: String,
    val ticker: String,
    val price: String,
    val percentChange: Double,
    val low: String,
    val high: String
)

data class StockData(
    val gainers: List<StockItem>,
    val losers: List<StockItem>
)

data class StockDetailsData(
    val companyName: String,
    val ticker: String,
    val industry: String,
    val yearHigh: String,
    val yearLow: String,
    val description: String?,
    val exchange: String,
    val currentPrice: Double,
    val priceWhole: String,
    val priceDecimal: String,
    val isGainer: Boolean,
    val percentChange: Double,
    val percentText: String,
    val chartPrices: List<Double>,
    val stats: StockStats,
    val news: List<StockNews>,
    val analystSentiment: AnalystSentiment?,
    val shareholding: List<ShareholdingPattern>
)

data class StockStats(
    val high: String,
    val low: String,
    val close: String,
    val marketCap: String,
    val peRatio: String,
    val divYield: String
)

data class StockNews(
    val headline: String,
    val date: String
)

data class AnalystSentiment(
    val totalAnalysts: Int,
    val tickerPercentage: Double,
    val analysts: List<AnalystInfo>
)

data class AnalystInfo(
    val numberOfAnalysts: Int,
    val colorCode: String,
    val ratingName: String
)

data class ShareholdingPattern(
    val name: String,
    val latestPercentage: String,
    val previousPercentage: String,
    val type: String
)
