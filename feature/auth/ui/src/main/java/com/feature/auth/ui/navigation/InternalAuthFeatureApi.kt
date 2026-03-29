package com.feature.auth.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constant.AuthFeature
import com.core.feature_api.FeatureApi
import com.feature.auth.ui.screen.AuthScreen

internal object InternalAuthFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = AuthFeature.AUTH_NESTED_ROUTE,
            route = AuthFeature.AUTH_SCREEN_ROUTE
        ) {
            composable(AuthFeature.AUTH_NESTED_ROUTE) {
                AuthScreen()
            }
        }
    }
}