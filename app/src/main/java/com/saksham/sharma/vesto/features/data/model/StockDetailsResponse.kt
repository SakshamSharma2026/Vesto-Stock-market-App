package com.saksham.sharma.vesto.features.data.model

data class StockDetailsResponse(
    val analystView: List<AnalystView>,
    val companyName: String,
    val companyProfile: CompanyProfile,
    val currentPrice: CurrentPrice,
    val financials: List<Financial>,
    val futureExpiryDates: Any,
    val futureOverviewData: Any,
    val industry: String,
    val initialStockFinancialData: Any,
    val keyMetrics: Any,
    val percentChange: String,
    val recentNews: List<RecentNew>,
    val recosBar: RecosBar,
    val riskMeter: RiskMeter,
    val shareholding: List<Shareholding>,
    val stockCorporateActionData: StockCorporateActionData,
    val stockDetailsReusableData: StockDetailsReusableData,
    val stockFinancialData: List<StockFinancialData>,
    val stockTechnicalData: List<StockTechnicalData>,
    val yearHigh: String,
    val yearLow: String
)