package com.helloworld.coordinators.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.helloworld.navigation.Coordinator
import com.helloworld.ui.profile.ProfileDetailScreen
import com.helloworld.ui.profile.ProfileScreen

enum class AppRoutes(val route: String) {
    PROFILE("profile"),
    PROFILE_DETAILS("profile_details")
}

class AppCoordinator(
    navigationController: NavHostController,
    authController: NavHostController?,
) : Coordinator(navigationController,authController) {

    override fun start() {
        // Navigate to the login screen
        navigationController.navigate(AppRoutes.PROFILE.route)
    }

    fun navigateTest() {

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