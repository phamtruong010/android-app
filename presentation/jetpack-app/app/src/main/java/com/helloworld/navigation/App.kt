package com.helloworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.helloworld.coordinators.auth.LoginRoutes
import com.helloworld.ui.theme.themeing.themes.LightTheme


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authController = rememberNavController()

    LightTheme()
    val appCoordinator = AppCoordinator(navController, authController)

    // Collect the registerRoutes functions from each coordinator (AppCoordinator and AuthCoordinator)
    val registerRoutesFunctions =
        appCoordinator.coordinators.map { it.getRouteRegistrationLambda() }

    // Pass the functions to the DynamicNavigationHost
    DynamicNavigationHost(
        navController,
        startDestination = LoginRoutes.LOGIN.route,  // Directly start at the "login" route
        registerRoutesFunctions = registerRoutesFunctions
    )
}