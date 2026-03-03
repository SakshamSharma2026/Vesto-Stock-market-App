package com.saksham.sharma.vesto.features.presentation.screens

sealed class Screen (val route: String){
    data object IntroScreen : Screen(route = "intro_screen")
    data object HomeScreen : Screen(route = "home_screen")

}