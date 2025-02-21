package com.helloworld.util.preview


import androidx.compose.runtime.Composable
import com.helloworld.ui.theme.AppTheme

@Composable
fun PreviewContainer(
    content: @Composable () -> Unit
) {
    AppTheme {
        content()
    }
}