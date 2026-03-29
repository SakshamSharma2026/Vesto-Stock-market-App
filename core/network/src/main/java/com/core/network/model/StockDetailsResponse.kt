package com.core.network.model

data class StockDetailsResponse(
    val analystView: List<AnalystView>,
    val companyName: String,
    val companyProfile: CompanyProfile,
    val currentPrice: CurrentPrice,
    val industry: String,
    val recentNews: List<RecentNew>,
    val recosBar: RecosBar,
    val shareholding: List<Shareholding>,
    val stockDetailsReusableData: StockDetailsReusableData,
    val stockTechnicalData: List<StockTechnicalData>,
    val yearHigh: String,
    val yearLow: String
)

data class StockTechnicalData(
    val bsePrice: String,
    val days: Int,
    val nsePrice: String
)
data class RecentNew(
    val date: String,
    val headline: String,
    val id: Long,
    val lastPublishedDate: String,
    val listimage: String,
    val metadata: Metadata,
    val summary: String,
    val thumbnailImage: String,
    val timeToRead: Int,
    val url: String,
    val videoBody: Any
)

data class StockAnalyst(
    val colorCode: String,
    val maxValue: Double,
    val minValue: Double,
    val numberOfAnalysts: Int,
    val ratingName: String,
    val ratingValue: Int
)

data class Shareholding(
    val categories: List<Category>,
    val categoryName: String,
    val displayName: String
)

data class RecosBar(
    val isDataPresent: Boolean,
    val meanValue: Double,
    val noOfRecommendations: Int,
    val stockAnalyst: List<StockAnalyst>,
    val tickerPercentage: Double,
    val tickerRatingValue: Int
)

data class Metadata(
    val url: String
)

data class MutualFundShareHolding(
    val holdingDate: String,
    val percentage: String
)

data class CurrentPrice(
    val BSE: String,
    val NSE: String
)

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

data class Category(
    val holdingDate: String,
    val percentage: String
)

data class CompanyProfile(
    val companyDescription: String,
    val exchangeCodeBse: String,
    val exchangeCodeNse: String,
)