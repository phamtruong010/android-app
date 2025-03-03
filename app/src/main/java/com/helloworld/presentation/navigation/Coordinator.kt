package com.helloworld.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

abstract class Coordinator(
    val navigationController: NavHostController,
    val authController: NavHostController,
) {

    abstract fun start()

    /**
     * Defines the navigation routes for this coordinator.
     */


    abstract fun getRouteRegistrationLambda(): NavGraphBuilder.() -> Unit


    // Example of default behavior
    open fun onBackPressed() {
        navigationController.popBackStack()
    }

}