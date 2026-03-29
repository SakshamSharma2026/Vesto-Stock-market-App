package com.core.network

import com.core.network.model.IndianStockResponse
import com.core.network.model.NewsResponse
import com.core.network.model.StockDetailsResponse
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


    @GET("/news")
    suspend fun getNews(): NewsResponse

}