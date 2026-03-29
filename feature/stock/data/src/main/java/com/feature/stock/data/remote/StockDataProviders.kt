package com.feature.stock.data.remote

import com.core.network.ApiService
import javax.inject.Inject

class StockDataProviders @Inject constructor(private val apiService: ApiService) {

    suspend fun getStockList() = apiService.getTrendingStocks()

    suspend fun getStockDetails(stockName: String) = apiService.getStockDetails(stockName)
}