package com.helloworld.ui.navigationbar

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.helloworld.coordinators.home.HomeCoordinator
import com.helloworld.ui.favorites.FavoritesPage
import com.helloworld.ui.feed.FeedPage
import com.helloworld.ui.overview.OverviewScreen
import com.helloworld.ui.portfolio.PortfolioScreen


@Composable
fun NavigationBarNestedGraph(
    navController: NavHostController,
    mainNavController: NavHostController,
    parentRoute: String,
    homeCoordinator: HomeCoordinator,
    navigateToProfile: () -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = "Overview",
        route = parentRoute
    ) {

        composable("Overview") {
            BackHandler(onBack = {
                Log.d("BackHandler", "BackHandler")
            })
            OverviewScreen(
                navigateToProfile
            )
        }
        composable("Feed") {
            BackHandler(onBack = {
                Log.d("BackHandler", "BackHandler")
            })
            FeedPage()
        }
        composable("Favorites") {
            BackHandler(onBack = {
                Log.d("BackHandler", "BackHandler")
            })
            FavoritesPage()
        }
        composable("Portfolio") {
            BackHandler(onBack = {
                Log.d("BackHandler", "BackHandler")
            })
            PortfolioScreen()
        }
    }
}