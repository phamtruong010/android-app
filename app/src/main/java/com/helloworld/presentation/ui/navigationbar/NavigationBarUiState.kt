package com.helloworld.presentation.ui.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DynamicFeed
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector
import com.helloworld.presentation.navigation.Page

data class NavigationBarUiState(
    val bottomItems: List<BottomNavigationBarItem> = listOf(
        BottomNavigationBarItem.Overview,
        BottomNavigationBarItem.Feed,
        BottomNavigationBarItem.MyFavorites,
        BottomNavigationBarItem.Portfolio
    )
)

sealed class BottomNavigationBarItem(
    val tabName: String,
    val imageVector: ImageVector,
    val page: String,
) {
    data object Overview : BottomNavigationBarItem("Overview", imageVector = Icons.Default.DynamicFeed, "Overview")
    data object Feed : BottomNavigationBarItem("Feed", imageVector = Icons.Default.DynamicFeed, "Feed")
    data object MyFavorites : BottomNavigationBarItem("My Favorites", imageVector = Icons.Default.FavoriteBorder, "Favorites")
    data object Portfolio : BottomNavigationBarItem("Portfolio", imageVector = Icons.Default.FavoriteBorder, "Portfolio")
}