package com.saksham.sharma.vesto.core.network

import com.saksham.sharma.vesto.features.data.model.IndianStockResponse
import com.saksham.sharma.vesto.features.data.model.StockDetailsResponse
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