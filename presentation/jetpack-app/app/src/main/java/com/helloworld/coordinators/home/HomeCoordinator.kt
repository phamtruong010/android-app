package com.helloworld.coordinators.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.helloworld.navigation.Coordinator
import com.helloworld.ui.navigationbar.NavigationBarNestedGraph
import com.helloworld.ui.navigationbar.NavigationBarScreen
import com.helloworld.util.sharedViewModel


enum class HomeRoutes(val route: String) {
    HOME("home")
}

class HomeCoordinator(
    navigationController: NavHostController,
    authController: NavHostController?,
    private val navigateToProfile: () -> Unit
) : Coordinator(navigationController,authController) {
    override fun start() {
        navigationController.navigate(HomeRoutes.HOME.route)
    }

    override fun getRouteRegistrationLambda(): NavGraphBuilder.() -> Unit {

        return {

            composable(HomeRoutes.HOME.route) { backStack ->

                val nestedNavController = rememberNavController()
                NavigationBarScreen(
                    sharedViewModel = backStack.sharedViewModel(navController = navigationController),
                    nestedNavController = nestedNavController
                ) {
                    NavigationBarNestedGraph(
                        navController = nestedNavController,
                        mainNavController = navigationController,
                        parentRoute = HomeRoutes.HOME.route,
                        homeCoordinator = this@HomeCoordinator,
                        navigateToProfile,
                    )
                }
            }

        }
    }
}