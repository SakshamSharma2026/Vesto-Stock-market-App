package com.feature.stock.domain.repo

import com.feature.stock.domain.model.IndianStockData
import com.feature.stock.domain.model.StockDetailsData


interface StockRepository {
    suspend fun getIndianTrendingStock(): IndianStockData
    suspend fun getStockDetails(stockName: String): StockDetailsData
}