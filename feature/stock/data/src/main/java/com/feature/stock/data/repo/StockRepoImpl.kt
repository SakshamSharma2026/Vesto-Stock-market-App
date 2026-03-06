package com.feature.stock.data.repo

import com.feature.stock.data.remote.StockDataProviders
import com.feature.stock.domain.model.IndianStockData
import com.feature.stock.domain.model.StockDetailsData
import com.feature.stock.domain.repo.StockRepository
import javax.inject.Inject

import com.feature.stock.data.mapper.toDomain

class StockRepositoryImpl @Inject constructor(private val stockDataProviders: StockDataProviders) :
    StockRepository {
    override suspend fun getIndianTrendingStock(): IndianStockData {
        return stockDataProviders.getStockList().toDomain()
    }

    override suspend fun getStockDetails(stockName: String): StockDetailsData {
        return stockDataProviders.getStockDetails(stockName).toDomain()
    }
}