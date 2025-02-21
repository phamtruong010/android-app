package com.helloworld.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.helloworld.widget.AllDestinations
import com.helloworld.widget.AppDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DynamicNavigationHost(
    navController: NavHostController,
    startDestination: String,
    registerRoutesFunctions: List<NavGraphBuilder.() -> Unit>
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestinations.HOME

    // Attach the navController to each coordinator (if necessary)
    // You can skip this if coordinators are already handling their own navigation
    // Example: coordinators.forEach { it.navigationController.attachNavController(navController) }
    ModalNavigationDrawer(drawerContent = {
        AppDrawer(
            route = currentRoute,
            closeDrawer = { coroutineScope.launch { drawerState.close() } },
        )
    }, drawerState = drawerState ) {
        NavHost(navController = navController, startDestination = startDestination) {
            // Dynamically register the routes using the passed functions
            registerRoutesFunctions.forEach { registerRoutes ->
                // Call the function to register the routes
                registerRoutes.invoke(this)
            }
        }
    }

}
