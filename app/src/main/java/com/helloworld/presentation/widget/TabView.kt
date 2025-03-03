package com.helloworld.presentation.widget


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabView(tabData: List<TabItem>) {
    val pagerState = rememberPagerState(pageCount =  tabData.size )
    Column(modifier = Modifier.fillMaxSize()) {
        TabLayout(tabData, pagerState)
        TabContent(tabData, pagerState)
    }
}

data class TabItem(
    val title: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit
)


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(tabData: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            Spacer(modifier =Modifier.height(5.dp))
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 5.dp,
                color = Color.White
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        tabData.forEachIndexed { index, s ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
//                icon = {
//                    Icon(imageVector = s.icon, contentDescription = null)
//                },
                text = {
                    Text(text = s.title)
                })
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabData: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { index ->
        for (tab in tabData) {
            tab.content()
        }
    }

}


@Preview()
@Composable
fun PreviewContent() {
//    TabView()
}