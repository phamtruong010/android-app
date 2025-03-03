package com.helloworld.presentation.ui.feed

import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import com.helloworld.util.eventemitter.emit
import com.helloworld.util.eventemitter.getId
import com.helloworld.presentation.ui.cache.getValue
import com.helloworld.presentation.ui.cache.setText
import com.helloworld.presentation.ui.cache.setValue
import com.helloworld.presentation.ui.feed.data.DisplayTvShows
import com.helloworld.presentation.ui.theme.themeing.abstract_factory.ThemeFactoryProvider
import com.helloworld.presentation.widget.DatePickerModal
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Timer
import java.util.TimerTask


/**
 * @author by Ali Asadi on 18/04/2023
 */

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun FeedPage(
//    mainRouter: MainRouter,
//    viewModel: FeedViewModel,
//    sharedViewModel: NavigationBarSharedViewModel,
) {

    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val formatted = current.format(formatter)
    var message by remember { mutableStateOf("Hello, Compose!") }
    var showModal by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    var showBottomSheet1 by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(showBottomSheet1) {
        Log.d("showBottomSheet1", showBottomSheet1.toString())
    }
    LaunchedEffect(true) {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val randomNumber = (1..100).random()
                val keyvalue = "myEvent_${randomNumber}"
                val value = getId()
                emit("myEvent_${randomNumber}", value)
                setValue(keyvalue, value)
                Log.d("Timer", "set data")
            }
        }, 0, 500)

    }
    val localDateTime = kotlinx.datetime.LocalDateTime(2024, 12, 6, 22, 29, 33)
//    val formattedDateTime = localDateTime.format1(DatePatterns.YYYY_MM_DD_HH_MM_SS)
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.background(ThemeFactoryProvider.colorFactory.bottomNavBackgroundColor)) {
            Text("FeedPage")
            Text(text = message)

            Button(onClick = {
                showModal = !showModal
            }) {
                Text("Show DatePicker")
            }

            Button(onClick = {
                showBottomSheet = !showBottomSheet
            }) {
                Text("Show bottom sheet")
            }

            Button(onClick = {
                showBottomSheet1 = false
            }) {
                Text("Launch effect")
            }

            Button(onClick = {
                emit("myEvent_1", getId())
                setText("truong ne")

                getValue()
//                val eventId = addListener("myEvent", null) { data ->
//                    message = data as String
//                }

//                deleteListener("myEvent", eventId!!)
            }) {
                Text("Click Me")
            }
            DisplayTvShows()
        }

        Modifier.dragAndDropSource {
            detectTapGestures(onLongPress = {
                startTransfer(
                    DragAndDropTransferData(
                        ClipData.newPlainText(
                            "image Url", "truong"
                        )
                    )
                )
            })
        }

        if (showModal) {
            DatePickerModal(
                onDateSelected = { selectedDate = it },
                onDismiss = { showModal = false }
            )
        }
        if (showBottomSheet) {
            ModalBottomSheet(
//                modifier = Modifier.height(200.dp),
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                // Sheet content
                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text("Hide bottom sheet")
                }
            }
        }

    }


}
