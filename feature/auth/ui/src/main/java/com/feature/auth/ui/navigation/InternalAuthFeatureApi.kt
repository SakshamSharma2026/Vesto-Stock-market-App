package com.feature.auth.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constant.AuthFeature
import com.core.common.navigation_constant.MainFeature
import com.core.feature_api.FeatureApi
import com.feature.auth.ui.screen.AuthScreen
import com.feature.auth.ui.screen.AuthState
import com.feature.auth.ui.screen.AuthViewModel

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
                val viewModel: AuthViewModel = hiltViewModel()
                val authState by viewModel.authState.collectAsState()
                val context = androidx.compose.ui.platform.LocalContext.current

                LaunchedEffect(authState) {
                    when (authState) {
                        is AuthState.Success -> {
                            navController.navigate(MainFeature.MAIN_SCREEN_ROUTE) {
                                popUpTo(AuthFeature.AUTH_SCREEN_ROUTE) { inclusive = true }
                            }
                        }
                        is AuthState.Error -> {
                            android.widget.Toast.makeText(context, (authState as AuthState.Error).message, android.widget.Toast.LENGTH_SHORT).show()
                        }
                        else -> {}
                    }
                }

                AuthScreen(
                    authState = authState,
                    onSignInClick = { viewModel.getGoogleSignInIntent() },
                    onSignInResult = { intent -> viewModel.handleGoogleSignInResult(intent) }
                )
            }
        }
    }
}