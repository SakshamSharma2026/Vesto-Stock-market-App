package com.saksham.sharma.vesto.features.domain.repository

import com.saksham.sharma.vesto.features.data.model.IndianStockModelResponse

interface StockRepository  {
    suspend fun getIndianTrendingStock(apiKey: String): IndianStockModelResponse
}