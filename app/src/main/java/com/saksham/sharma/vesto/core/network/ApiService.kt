package com.saksham.sharma.vesto.core.network

import com.saksham.sharma.vesto.features.data.model.IndianStockModelResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    //apiKey = sk-live-GnqspF9r7uYu2Cf9k1t8t6J2CpDCN9FEns5Fyx6N


    @GET("/trending")
    suspend fun getTrendingStocks(
        @Header("x-api-key") apiKey: String
    ): IndianStockModelResponse


}