package com.feature.stock.data.mapper

import com.core.network.model.Gainer
import com.core.network.model.IndianStockResponse
import com.core.network.model.StockDetailsResponse
import com.feature.stock.domain.model.AnalystInfo
import com.feature.stock.domain.model.AnalystSentiment
import com.feature.stock.domain.model.ShareholdingPattern
import com.feature.stock.domain.model.StockData
import com.feature.stock.domain.model.StockDetailsData
import com.feature.stock.domain.model.StockItem
import com.feature.stock.domain.model.StockNews
import com.feature.stock.domain.model.StockStats

fun IndianStockResponse.toDomain(): StockData {
    return StockData(
        gainers = trending_stocks.top_gainers.map { it.toDomain() },
        losers = trending_stocks.top_losers.map { it.toDomain() }
    )
}

fun Gainer.toDomain(): StockItem {
    return StockItem(
        companyName = company_name,
        ticker = ric,
        price = price,
        percentChange = percent_change.toDoubleOrNull() ?: 0.0,
        low = low,
        high = high
    )
}

fun StockDetailsResponse.toDomain(): StockDetailsData {
    val currentPriceStr = stockDetailsReusableData.close.replace(",", "")
    val priceVal = currentPriceStr.toDoubleOrNull() ?: 0.0
    val priceParts = "%.2f".format(priceVal).split(".")

    val isGainerFlag = (stockDetailsReusableData.percentChange.toDoubleOrNull() ?: 0.0) >= 0.0

    val technicals = stockTechnicalData
    val rawPrices = technicals.mapNotNull { it.nsePrice.toDoubleOrNull() }
    val prices = if (rawPrices.isEmpty()) listOf(priceVal) else rawPrices.takeLast(30)

    val sentiment = if (recosBar.isDataPresent) {
        AnalystSentiment(
            totalAnalysts = recosBar.noOfRecommendations,
            tickerPercentage = recosBar.tickerPercentage,
            analysts = recosBar.stockAnalyst.map {
                AnalystInfo(
                    numberOfAnalysts = it.numberOfAnalysts,
                    colorCode = it.colorCode,
                    ratingName = it.ratingName
                )
            }
        )
    } else null

    return StockDetailsData(
        companyName = companyName ?: "",
        ticker = companyProfile?.exchangeCodeNse ?: "",
        industry = industry ?: "",
        yearHigh = yearHigh ?: "",
        yearLow = yearLow ?: "",
        description = companyProfile.companyDescription,
        exchange = companyProfile.exchangeCodeNse ?: "",
        currentPrice = priceVal,
        priceWhole = priceParts.getOrNull(0) ?: "0",
        priceDecimal = "." + (priceParts.getOrNull(1) ?: "00"),
        isGainer = isGainerFlag,
        percentChange = stockDetailsReusableData.percentChange.toDoubleOrNull() ?: 0.0,
        percentText = "${if (isGainerFlag) "+" else ""}${
            String.format(
                "%.2f",
                stockDetailsReusableData.percentChange?.toDoubleOrNull() ?: 0.0
            )
        }%",
        chartPrices = prices,
        stats = StockStats(
            high = stockDetailsReusableData.high ?: "",
            low = stockDetailsReusableData.low ?: "",
            close = stockDetailsReusableData.close ?: "",
            marketCap = stockDetailsReusableData.marketCap ?: "",
            peRatio = stockDetailsReusableData.pPerEBasicExcludingExtraordinaryItemsTTM ?: "",
            divYield = stockDetailsReusableData.currentDividendYieldCommonStockPrimaryIssueLTM ?: ""
        ),
        news = recentNews.map {
            StockNews(
                headline = it.headline,
                date = it.date
            )
        },
        analystSentiment = sentiment,
        shareholding = shareholding.map {
            ShareholdingPattern(
                name = it.displayName,
                latestPercentage = it.categories.firstOrNull()?.percentage ?: "0",
                previousPercentage = it.categories.getOrNull(1)?.percentage ?: "0",
                type = it.categoryName
            )
        }
    )
}
