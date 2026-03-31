package com.feature.news.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.feature_api.FeatureApi

interface NewsApi : FeatureApi


class NewsApiImpl : NewsApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalNewsFeatureApi.registerGraph(navController, navGraphBuilder)
    }

}
