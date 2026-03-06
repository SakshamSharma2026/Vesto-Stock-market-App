package com.feature.stock.data.dto

data class PeerCompany(
    val companyName: String,
    val dividendYieldIndicatedAnnualDividend: Double,
    val imageUrl: String,
    val languageSupport: String,
    val ltDebtPerEquityMostRecentFiscalYear: Double,
    val marketCap: Double,
    val netChange: Double,
    val netProfitMargin5YearAverage: Double,
    val netProfitMarginPercentTrailing12Month: Double,
    val overallRating: String,
    val percentChange: Double,
    val price: Double,
    val priceToBookValueRatio: Double,
    val priceToEarningsValueRatio: Double,
    val returnOnAverageEquity5YearAverage: Double,
    val returnOnAverageEquityTrailing12Month: Double,
    val tickerId: String,
    val totalSharesOutstanding: Double,
    val yhigh: Double,
    val ylow: Double
)