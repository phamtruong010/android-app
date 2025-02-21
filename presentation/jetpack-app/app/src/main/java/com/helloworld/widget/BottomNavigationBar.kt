package com.helloworld.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.helloworld.navigation.route
import com.helloworld.ui.navigationbar.BottomNavigationBarItem
import com.helloworld.ui.navigationbar.BottomNavigationBarItem.*
import com.helloworld.util.preview.PreviewContainer

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationBarItem>,
    navController: NavController,
    onItemClick: (BottomNavigationBarItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomAppBar(
        elevation = 0.dp,
        modifier = Modifier
            .padding(bottom = 30.dp, start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(20.dp)),
        backgroundColor = Color(0xCCFFFFFF),
    ) {
        items.forEachIndexed { index, item ->
            val selected = item.page == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                modifier = Modifier.height(40.dp),
                selected = selected,
                onClick = { onItemClick(item) },
                enabled = true,
                icon = {
                    Icon(
                        imageVector = item.imageVector, contentDescription = null,
                        tint = Color(
                            0xFF8C8C8C
                        ),
                    )
                },
            )
            if (index === 1)
                Spacer(modifier = Modifier.width(70.dp))
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BottomNavigationBarViewPreview() {
    PreviewContainer {
        BottomNavigationBar(listOf(Feed, MyFavorites), rememberNavController()) {}
    }
}