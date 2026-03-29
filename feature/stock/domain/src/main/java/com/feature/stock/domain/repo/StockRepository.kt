package com.feature.stock.domain.repo

import com.feature.stock.domain.model.StockData
import com.feature.stock.domain.model.StockDetailsData


interface StockRepository {
    suspend fun getTrendingStocks(): StockData
    suspend fun getStockDetails(stockName: String): StockDetailsData
}