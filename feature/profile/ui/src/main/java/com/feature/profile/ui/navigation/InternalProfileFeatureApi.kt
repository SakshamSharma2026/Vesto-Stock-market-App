package com.feature.profile.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constant.AuthFeature
import com.core.common.navigation_constant.ProfileFeature
import com.core.feature_api.FeatureApi
import com.feature.profile.ui.screen.ProfileScreen
import com.feature.profile.ui.screen.ProfileViewModel

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
                val viewModel: ProfileViewModel = hiltViewModel()
                ProfileScreen(
                    viewModel = viewModel,
                    onLogout = {
                        navController.navigate(AuthFeature.AUTH_SCREEN_ROUTE) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}