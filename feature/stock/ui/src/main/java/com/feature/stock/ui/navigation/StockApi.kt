package com.feature.stock.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.feature_api.FeatureApi

interface StockApi : FeatureApi


class StockApiImpl : StockApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalStockFeatureApi.registerGraph(navController, navGraphBuilder)
    }

}