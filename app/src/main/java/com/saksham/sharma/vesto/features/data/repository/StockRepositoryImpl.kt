package com.saksham.sharma.vesto.features.data.repository

import com.saksham.sharma.vesto.core.network.ApiService
import com.saksham.sharma.vesto.features.data.model.IndianStockResponse
import com.saksham.sharma.vesto.features.data.model.StockDetailsResponse
import com.saksham.sharma.vesto.features.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    StockRepository {
    override suspend fun getIndianTrendingStock(): IndianStockResponse {
        return apiService.getTrendingStocks()
    }

    override suspend fun getStockDetails(stockName: String): StockDetailsResponse {
        return apiService.getStockDetails(stockName)
    }
}