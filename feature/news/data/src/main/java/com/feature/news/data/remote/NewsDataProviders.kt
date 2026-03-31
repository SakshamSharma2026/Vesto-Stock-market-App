package com.feature.news.data.remote

import com.core.network.ApiService
import javax.inject.Inject

class NewsDataProviders @Inject constructor(private val apiService: ApiService) {

    suspend fun getNews() = apiService.getNews()
}