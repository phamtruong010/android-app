package com.helloworld.ui.favorites

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.helloworld.R
import com.helloworld.eventemitter.emit
import com.helloworld.eventemitter.getId
import com.helloworld.ui.cache.getText
import com.helloworld.ui.cache.setText
import com.helloworld.ui.main.MainRouter
import com.helloworld.util.preview.PreviewContainer
import com.helloworld.widget.TabItem
import com.helloworld.widget.TabView
import com.himanshoe.charty.candle.CandleStickChart
import com.himanshoe.charty.candle.config.CandleStickConfig
import com.himanshoe.charty.candle.model.CandleData
import com.himanshoe.charty.common.ComposeList
import kotlinx.coroutines.flow.flowOf


private fun getTabList(): List<TabItem> {
    return listOf(
        TabItem(
            title = "Home",
            icon = Icons.Default.Home,
            content = { HomeScreen() }
        ),
        TabItem(
            title = "Search",
            icon = Icons.Default.Search,
            content = { HomeScreen() }
        ),
        TabItem(
            title = "Favorites",
            icon = Icons.Default.Favorite,
            content = { HomeScreen() }
        ),
        TabItem(
            title = "Settings",
            icon = Icons.Default.Settings,
            content = { HomeScreen() }
        )
    )
}

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home1", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Red)
        val candleData = listOf(
            CandleData(high = 20f, low = 8f, open = 10f, close = 15f),
            CandleData(high = 22f, low = 16f, open = 18f, close = 20f),
            CandleData(high = 14f, low = 8f, open = 12f, close = 9f),
            CandleData(high = 9f, low = 3f, open = 7f, close = 5f),
            CandleData(high = 10f, low = 4f, open = 6f, close = 8f),
            CandleData(high = 15f, low = 10f, open = 13f, close = 12f),
            CandleData(high = 20f, low = 8f, open = 10f, close = 15f),
            CandleData(high = 22f, low = 16f, open = 18f, close = 20f),
            CandleData(high = 14f, low = 8f, open = 12f, close = 9f),
            CandleData(high = 9f, low = 3f, open = 7f, close = 5f),
            CandleData(high = 10f, low = 4f, open = 6f, close = 8f),
            CandleData(high = 15f, low = 10f, open = 13f, close = 12f),
            CandleData(high = 9f, low = 3f, open = 7f, close = 5f),
            CandleData(high = 10f, low = 4f, open = 6f, close = 8f),
            CandleData(high = 15f, low = 10f, open = 13f, close = 12f),
            CandleData(high = 20f, low = 8f, open = 10f, close = 15f),
            CandleData(high = 22f, low = 16f, open = 18f, close = 20f),
            CandleData(high = 14f, low = 8f, open = 12f, close = 9f),
            CandleData(high = 9f, low = 3f, open = 7f, close = 5f),
            CandleData(high = 10f, low = 4f, open = 6f, close = 8f),
            CandleData(high = 15f, low = 10f, open = 13f, close = 12f),
        )

        CandleStickChart(
            candleData = ComposeList(candleData),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            candleConfig = CandleStickConfig(negativeColor = Color.Blue, positiveColor = Color.Cyan, wickWidthScale = 0.05f, canCandleScale = true, wickColor = Color.Cyan)
        )
    }

}

@Composable
fun FavoritesPage() {
    val tabData = getTabList()
    TabView(tabData)
}
