package com.feature.news.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constant.NewsFeature
import com.core.feature_api.FeatureApi
import com.feature.news.ui.screen.news.NewsScreen
import com.feature.news.ui.screen.news.NewsViewModel

internal object InternalNewsFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = NewsFeature.NEWS_NESTED_ROUTE,
            route = NewsFeature.NEWS_SCREEN_ROUTE
        ) {
            composable(NewsFeature.NEWS_NESTED_ROUTE) {
                val viewModel = hiltViewModel<NewsViewModel>()
                NewsScreen(viewModel = viewModel)
            }
        }
    }
}
