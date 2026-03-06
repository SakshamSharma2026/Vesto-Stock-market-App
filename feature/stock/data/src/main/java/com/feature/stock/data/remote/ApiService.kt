package com.feature.stock.data.remote

import com.feature.stock.data.dto.IndianStockResponse
import com.feature.stock.data.dto.StockDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/trending")
    suspend fun getTrendingStocks(
    ): IndianStockResponse


    @GET("/stock")
    suspend fun getStockDetails(
        @Query("name") stockName: String
    ): StockDetailsResponse


}