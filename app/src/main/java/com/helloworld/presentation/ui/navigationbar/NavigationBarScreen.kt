package com.helloworld.presentation.ui.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.helloworld.presentation.coordinators.app.AppRoutes
import com.helloworld.presentation.navigation.route
import com.helloworld.presentation.widget.BottomNavigationBar


@Composable
fun NavigationBarScreen(
    sharedViewModel: NavigationBarSharedViewModel,
//    mainRouter: MainRouter,
//    darkMode: Boolean,
//    onThemeUpdated: () -> Unit,
    nestedNavController: NavHostController,
    navigateSearch: () -> Unit,
    content: @Composable () -> Unit,

) {
    val uiState = NavigationBarUiState()
    Scaffold(
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navigateSearch()
                },
                modifier = Modifier
                    .padding(top = 60.dp)
                    .size(70.dp),
                backgroundColor = Color.White
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(color = Color.Green, shape = RoundedCornerShape(40.dp))
                ) { }
            }

        },
        bottomBar = {
            BottomNavigationBar(
                items = uiState.bottomItems,
                navController = nestedNavController,
                onItemClick = { bottomItem ->
                    val currentPageRoute = nestedNavController.currentDestination?.route
                    val clickedPageRoute = bottomItem.page
                    val notSamePage = currentPageRoute != clickedPageRoute
                    if (notSamePage) {
                        nestedNavController.navigate(clickedPageRoute) {
                            launchSingleTop = true
                            popUpTo(nestedNavController.graph.findStartDestination().id)
                        }
                    }
                    sharedViewModel.onBottomItemClicked(bottomItem)
                }
            )
        }
    ) { paddingValues ->
        content()
    }
}