package com.core.network.model

data class StockDetailsReusableData(
    val FiscalYear: String,
    val NetIncome: String,
    val averageRating: String,
    val close: String,
    val currentDividendYieldCommonStockPrimaryIssueLTM: String,
    val date: String,
    val high: String,
    val interimNetIncome: String,
    val low: String,
    val marketCap: String,
    val mutualFundShareHolding: MutualFundShareHolding,
    val pPerEBasicExcludingExtraordinaryItemsTTM: String,
    val percentChange: String,
    val price: String,
    val price5DayPercentChange: String,
    val priceYTDPricePercentChange: String,
    val promoterShareHolding: Any,
    val sectorPriceToEarningsValueRatio: String,
    val time: String,
    val totalDebtPerTotalEquityMostRecentQuarter: String,
    val yhigh: String,
    val ylow: String
)