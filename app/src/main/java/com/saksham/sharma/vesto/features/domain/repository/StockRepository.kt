package com.saksham.sharma.vesto.features.domain.repository

import com.saksham.sharma.vesto.features.data.model.IndianStockResponse
import com.saksham.sharma.vesto.features.data.model.StockDetailsResponse

interface StockRepository  {
    suspend fun getIndianTrendingStock(): IndianStockResponse
    suspend fun getStockDetails(stockName: String): StockDetailsResponse

}