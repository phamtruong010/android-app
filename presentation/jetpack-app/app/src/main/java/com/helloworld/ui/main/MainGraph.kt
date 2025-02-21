package com.helloworld.ui.main

import android.annotation.SuppressLint
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.helloworld.navigation.Graph
import com.helloworld.navigation.Page
import com.helloworld.ui.navigationbar.NavigationBarNestedGraph
import com.helloworld.ui.navigationbar.NavigationBarScreen
import com.helloworld.util.composableHorizontalSlide
import com.helloworld.util.sharedViewModel
import com.helloworld.widget.AllDestinations
import com.helloworld.widget.AppDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.navigation.compose.composable
import com.helloworld.ui.search.SearchScreen
import androidx.compose.material3.rememberDrawerState
import com.helloworld.coordinators.home.HomeRoutes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainGraph(
    mainNavController: NavHostController,
    darkMode: Boolean,
    onThemeUpdated: () -> Unit,
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
    val currentNavBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestinations.HOME

    ModalNavigationDrawer(drawerContent = {
        AppDrawer(
            route = currentRoute,
            closeDrawer = { coroutineScope.launch { drawerState.close() } },
        )
    }, drawerState = drawerState) {

        NavHost(
            navController = mainNavController,
            startDestination = Page.NavigationBar,
            route = Graph.Main::class,
        ) {

//            composableHorizontalSlide<Page.NavigationBar> { backStack ->
//                val nestedNavController = rememberNavController()
//                NavigationBarScreen(
//                    sharedViewModel = backStack.sharedViewModel(navController = mainNavController),
////                    mainRouter = MainRouter(mainNavController),
////                    darkMode = darkMode,
////                    onThemeUpdated = onThemeUpdated,
//                    nestedNavController = nestedNavController
//                ) {
//                    NavigationBarNestedGraph(
//                        navController = nestedNavController,
//                        mainNavController = mainNavController,
//                        parentRoute = HomeRoutes.HOME.route
//                    )
//                }
//            }
//
//            composable<Page.Search> {
////                    val viewModel = hiltViewModel<SearchViewModel>()
//                SearchScreen()
//            }
        }
    }

}