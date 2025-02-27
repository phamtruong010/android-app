package com.helloworld.presentation.coordinators.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.helloworld.presentation.navigation.Coordinator
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.helloworld.presentation.ui.login.LoginScreen

enum class LoginRoutes(val route: String) {
    LOGIN("login")
}

class AuthCoordinator(
    navigationController: NavHostController,
    authController: NavHostController,
    private val onLoginSuccess: () -> Unit
) : Coordinator(navigationController,authController ) {

    override fun start() {
        // Navigate to the login screen
        navigationController.navigate(LoginRoutes.LOGIN.route)
    }

    override fun getRouteRegistrationLambda(): NavGraphBuilder.() -> Unit {

        return {
            composable(LoginRoutes.LOGIN.route) { backstack ->
//                // Display the login screen
//                LoginScreen(onLoginSuccess = {
//                    Log.d("Nibin","Authcoordinator getRouteRegistraionLambda fn invoked")
//                    onLoginSuccess()
//                })

                val navAuthController = rememberNavController()
                NavHost(
                    navController = navAuthController,
                    startDestination = LoginRoutes.LOGIN.route
                ) {
                    composable(LoginRoutes.LOGIN.route) { backstack ->
                        // Display the login screen
                        LoginScreen(onLoginSuccess = {
                            onLoginSuccess()
                        })
                    }
//                    composable(route = "home") { MainScreen(navController, drawerState) }
//                    composable(route = "info") { InfoScreen(navController) }
//                    composable(route = "settings") { SettingsScreen(navController) }
//                    composable(route = "favorite") { FavoriteScreen(navController, drawerState)  }
//                    composable(route = "search") { SearchScreen(navController, drawerState)  }

                }

            }
        }
    }
}