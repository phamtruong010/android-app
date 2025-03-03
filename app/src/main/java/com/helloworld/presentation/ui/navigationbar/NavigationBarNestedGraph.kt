package com.helloworld.presentation.ui.navigationbar

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.helloworld.presentation.coordinators.home.HomeCoordinator
import com.helloworld.presentation.ui.favorites.FavoritesPage
import com.helloworld.presentation.ui.feed.FeedPage
import com.helloworld.presentation.ui.overview.OverviewScreen
import com.helloworld.presentation.ui.portfolio.PortfolioScreen


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
            BackHandler(onBack = {})
            OverviewScreen(
                navigateToProfile
            )
        }
        composable("Feed") {
            BackHandler(onBack = {})
            FeedPage()
        }
        composable("Favorites") {
            BackHandler(onBack = {})
            FavoritesPage()
        }
        composable("Portfolio") {
            BackHandler(onBack = {})
            PortfolioScreen()
        }
    }
}