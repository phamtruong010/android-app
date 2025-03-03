package com.helloworld.presentation.coordinators.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.helloworld.presentation.coordinators.auth.AuthCoordinator
import com.helloworld.presentation.coordinators.auth.LoginRoutes
import com.helloworld.presentation.navigation.Coordinator
import com.helloworld.presentation.ui.profile.ProfileDetailScreen
import com.helloworld.presentation.ui.profile.ProfileScreen

enum class AppRoutes(val route: String) {
    PROFILE("profile"),
    PROFILE_DETAILS("profile_details")
}

class AppCoordinator(
    navigationController: NavHostController,
    authController: NavHostController,
) : Coordinator(navigationController, authController) {

    override fun start() {
        // Navigate to the login screen
        navigationController.navigate(AppRoutes.PROFILE.route)
    }

    fun navigateTest() {
//        navigationController.popBackStack(LoginRoutes.LOGIN.route)

//        navigationController.navigate(LoginRoutes.LOGIN.route)
    }

    override fun getRouteRegistrationLambda(): NavGraphBuilder.() -> Unit {
        return {

            composable(AppRoutes.PROFILE.route) {
                // Display the login screen

                ProfileScreen(this@AppCoordinator)
            }

            composable(AppRoutes.PROFILE_DETAILS.route) {
                // Display the login screen
                ProfileDetailScreen(this@AppCoordinator)
            }
        }
    }
}