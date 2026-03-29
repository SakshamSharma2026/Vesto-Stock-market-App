package com.feature.profile.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constant.ProfileFeature
import com.core.feature_api.FeatureApi
import com.feature.profile.ui.screen.ProfileScreen

internal object InternalProfileFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = ProfileFeature.PROFILE_NESTED_ROUTE,
            route = ProfileFeature.PROFILE_SCREEN_ROUTE
        ) {
            composable(ProfileFeature.PROFILE_NESTED_ROUTE) {
                ProfileScreen()
            }
        }
    }
}