package com.helloworld.ui.portfolio

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Handler
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PortfolioScreen() {
    val mUrl = "https://www.google.com.vn/"

    val context = LocalContext.current
    val activity = context as? Activity

    Column {
        Button(onClick = {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }) {
            Text("Chuyển sang Landscape")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }) {
            Text("Chuyển sang Portrait")
        }
    }

    // Adding a WebView inside AndroidView
    // with layout as full screen
//    AndroidView( modifier = Modifier, factory = {
//        WebView(it).apply {
//            layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
//            )
//            webViewClient = WebViewClient()
//            loadUrl(mUrl)
//            settings.javaScriptEnabled = true
//
//
//        }
//    }, update = {
////        it.loadUrl(mUrl)
//
//        Handler().postDelayed({
//            val script = "alert(\"Hello! I am an alert box!!\");"
//            it.evaluateJavascript(script, null)
//        }, 1000)
//
//
//    })

}