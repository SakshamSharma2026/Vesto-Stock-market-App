package com.feature.stock.data.mapper

import com.feature.stock.data.dto.*
import com.feature.stock.domain.model.*

fun IndianStockResponse.toDomain(): IndianStockData {
    return IndianStockData(
        gainers = trending_stocks.top_gainers.map { it.toDomain() },
        losers = trending_stocks.top_losers.map { it.toDomain() }
    )
}

fun TopGainer.toDomain(): StockItem {
    return StockItem(
        companyName = company_name ?: "",
        ticker = ric ?: "",
        price = price?.toString() ?: "",
        percentChange = percent_change?.toString()?.toDoubleOrNull() ?: 0.0,
        low = low?.toString() ?: "",
        high = high?.toString() ?: ""
    )
}

fun TopLoser.toDomain(): StockItem {
    return StockItem(
        companyName = company_name ?: "",
        ticker = ric ?: "",
        price = price?.toString() ?: "",
        percentChange = percent_change?.toString()?.toDoubleOrNull() ?: 0.0,
        low = low?.toString() ?: "",
        high = high?.toString() ?: ""
    )
}

fun StockDetailsResponse.toDomain(): StockDetailsData {
    val currentPriceStr = (stockDetailsReusableData.close ?: "0.0").replace(",", "")
    val priceVal = currentPriceStr.toDoubleOrNull() ?: 0.0
    val priceParts = "%.2f".format(priceVal).split(".")

    val isGainerFlag = (stockDetailsReusableData.percentChange?.toString()?.toDoubleOrNull() ?: 0.0) >= 0.0
    
    val technicals = stockTechnicalData ?: emptyList()
    val rawPrices = technicals.mapNotNull { it.nsePrice?.toString()?.toDoubleOrNull() }
    val prices = if (rawPrices.isEmpty()) listOf(priceVal) else rawPrices.takeLast(30)
    
    val sentiment = if (recosBar != null && recosBar.isDataPresent) {
        AnalystSentiment(
            totalAnalysts = recosBar.noOfRecommendations ?: 0,
            tickerPercentage = recosBar.tickerPercentage ?: 0.0,
            analysts = recosBar.stockAnalyst.map { 
                AnalystInfo(
                    numberOfAnalysts = it.numberOfAnalysts ?: 0,
                    colorCode = it.colorCode ?: "",
                    ratingName = it.ratingName ?: ""
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
        percentChange = stockDetailsReusableData.percentChange?.toString()?.toDoubleOrNull() ?: 0.0,
        percentText = "${if (isGainerFlag) "+" else ""}${String.format("%.2f", stockDetailsReusableData.percentChange?.toString()?.toDoubleOrNull() ?: 0.0)}%",
        chartPrices = prices,
        stats = StockStats(
            high = stockDetailsReusableData.high ?: "",
            low = stockDetailsReusableData.low ?: "",
            close = stockDetailsReusableData.close ?: "",
            marketCap = stockDetailsReusableData.marketCap ?: "",
            peRatio = stockDetailsReusableData.pPerEBasicExcludingExtraordinaryItemsTTM ?: "",
            divYield = stockDetailsReusableData.currentDividendYieldCommonStockPrimaryIssueLTM ?: ""
        ),
        news = recentNews?.map { 
            StockNews(
                headline = it.headline,
                date = it.date
            )
        } ?: emptyList(),
        analystSentiment = sentiment,
        shareholding = shareholding?.map { 
            ShareholdingPattern(
                name = it.displayName,
                latestPercentage = it.categories.firstOrNull()?.percentage ?: "0",
                previousPercentage = it.categories.getOrNull(1)?.percentage ?: "0",
                type = it.categoryName
            )
        } ?: emptyList()
    )
}
