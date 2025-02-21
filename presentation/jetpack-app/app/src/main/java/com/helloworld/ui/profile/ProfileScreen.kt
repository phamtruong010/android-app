package com.helloworld.ui.profile

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.helloworld.coordinators.profile.AppCoordinator
import com.helloworld.coordinators.profile.AppRoutes
import com.helloworld.localization.LocalizationProvider
import com.helloworld.localization.setLanguageApp


@Composable
fun ProfileScreen(profileCoordinator: AppCoordinator) {
    val context = LocalContext.current
    val activity = context as? Activity


    @Composable
    fun changeOr() {
        val configuration = LocalConfiguration.current
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back to Main Button
        Button(
            onClick = { profileCoordinator.onBackPressed() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("Back to Dashboard")
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            "Select a Profile",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // List of Donuts
        val donuts = listOf("Profile 1", "Profile 2", "Profile 3", "Profile 4", "Profile 5")
        donuts.forEach { donut ->
            Button(
                onClick = { profileCoordinator.navigationController.navigate(AppRoutes.PROFILE_DETAILS.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(donut)
            }
        }
    }

}
