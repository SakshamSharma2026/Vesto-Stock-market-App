package com.saksham.sharma.vesto.features.data.repository

import com.saksham.sharma.vesto.core.network.ApiService
import com.saksham.sharma.vesto.features.data.model.IndianStockModelResponse
import com.saksham.sharma.vesto.features.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    StockRepository {
    override suspend fun getIndianTrendingStock(apiKey: String): IndianStockModelResponse {
        return apiService.getTrendingStocks(apiKey)
    }
}