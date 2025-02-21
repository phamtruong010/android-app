package com.helloworld.ui.feed.data

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.helloworld.eventemitter.addListener
import com.helloworld.eventemitter.deleteEvent
import com.helloworld.ui.cache.getValueByKey
import kotlin.random.Random

fun generateRandomTvShows(count: Int): List<TvShow> {
    val random = Random
    return (1..count).map {
        TvShow(
            id = it,
            name = "Show $it",
            year = random.nextInt(2023) + 1900, // Năm từ 1900 đến 2023
            rating = random.nextDouble(1.0, 10.0), // Rating từ 1.0 đến 10.0
            overview = "A random TV show about something." // Bạn có thể tạo hàm riêng để tạo overview ngẫu nhiên
        )
    }
}

@Composable
fun DisplayTvShows() {


    val tvShows = remember { TvShowList.tvShows }
    val randomTvShows = generateRandomTvShows(100)



    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        itemsIndexed(randomTvShows) { index, item ->
            TvShowListItem(tvShow = item, index)
        }
//        items(
//            items = tvShows,
//            itemContent = {
//                TvShowListItem(tvShow = it)
//            }
//        )
    }

}


@Composable
fun TvShowListItem(tvShow: TvShow, index: Number) {
    var data1 by remember { mutableStateOf("tvShow") }
    var id = "listener_${index}"
    val keyRealtime = "myEvent_${index}"

    LaunchedEffect(false) {
        val value = getValueByKey(keyRealtime)
        if (value != null) {
            data1 = value
        }
        var eventId = addListener(keyRealtime, id) { data ->
            data1 = data as String
        }

    }

    DisposableEffect(Unit) {
        // Code to run when the composable is first composed

        onDispose {
            deleteEvent(keyRealtime)
//            Log.d("helllo DisposableEffect",id)
            // Code to run when the composable is disposed
        }
    }



    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(text = "${data1}_${index}", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tvShow.overview,
                    style = MaterialTheme.typography.body1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = tvShow.year.toString(), style = MaterialTheme.typography.h5)
                    Text(text = tvShow.rating.toString(), style = MaterialTheme.typography.h5)
                }

            }

        }

    }
}