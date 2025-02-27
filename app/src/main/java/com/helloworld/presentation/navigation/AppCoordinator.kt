package com.helloworld.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.helloworld.presentation.coordinators.app.AppCoordinator
import com.helloworld.presentation.coordinators.auth.AuthCoordinator
import com.helloworld.presentation.coordinators.home.HomeCoordinator


class AppCoordinator(navigationController: NavHostController, authController: NavHostController) :
    Coordinator(
        navigationController, authController
    ) {


    private val authCoordinator = AuthCoordinator(
        navigationController,
        authController = authController,
        onLoginSuccess = {
            switchToHomeCoordinator()
        }
    )

    private val homeCoordinator = HomeCoordinator(
        navigationController,
        authController,
        navigateToProfile = {
            switchToProfileCoordinator()
        }
    )

    private val profileCoordinator = AppCoordinator(
        navigationController,
        authController
    )

    override fun start() {}

    override fun getRouteRegistrationLambda(): NavGraphBuilder.() -> Unit {
        return {
            // Register routes for all the coordinators
            authCoordinator.getRouteRegistrationLambda().invoke(this)
            homeCoordinator.getRouteRegistrationLambda().invoke(this)
            profileCoordinator.getRouteRegistrationLambda().invoke(this)
        }
    }

    private fun switchToHomeCoordinator() {
        homeCoordinator.start()
    }

    private fun switchToProfileCoordinator() {
        profileCoordinator.start()
    }

    val coordinators: List<Coordinator>
        get() = listOf(authCoordinator, homeCoordinator, profileCoordinator)
}
