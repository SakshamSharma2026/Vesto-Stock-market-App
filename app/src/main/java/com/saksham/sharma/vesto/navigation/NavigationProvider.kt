package com.saksham.sharma.vesto.navigation


import com.feature.auth.ui.navigation.AuthApi
import com.feature.news.ui.navigation.NewsApi
import com.feature.profile.ui.navigation.ProfileApi
import com.feature.stock.ui.navigation.StockApi

data class NavigationProvider(
    val stockApi: StockApi,
    val newsApi: NewsApi,
    val profileApi: ProfileApi,
    val authApi: AuthApi
)