package com.helloworld.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.helloworld.coordinators.home.HomeCoordinator


@Composable
fun HomeScreen(navigateToProfile: () -> Unit,homeCoordinator: HomeCoordinator)
{
    Box(modifier = Modifier.fillMaxSize()) {
        // Logout button at the top-left corner
        IconButton(
            onClick = { homeCoordinator.onBackPressed() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Logout"
            )
        }

        // Title at the top center
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 24.dp)
        )


        // Go to Orders button at the bottom center
        Button(
            onClick = { navigateToProfile() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Go to Profiles")
        }
    }
}