package com.helloworld.presentation.widget

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HighlightMultipleWords(text: String, wordsToHighlight: List<String>, colors: List<androidx.compose.ui.graphics.Color>) {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        var currentIndex = 0
        while (currentIndex < text.length) {
            var found = false
            for ((wordIndex, word) in wordsToHighlight.withIndex()) {
                if (text.startsWith(word, currentIndex)) {
                    pushStringAnnotation(tag = "CLICKABLE", annotation = word)
                    withStyle(
                        style = SpanStyle(
                            color = colors[wordIndex % colors.size],
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(word)
                    }
                    pop()
                    currentIndex += word.length
                    found = true
                    break
                }
            }
            if (!found) {
                append(text[currentIndex].toString())
                currentIndex++
            }
        }
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "CLICKABLE", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    Toast.makeText(context, "Bạn đã nhấn vào: ${annotation.item}", Toast.LENGTH_SHORT).show()
                }
        },
        modifier = Modifier.padding(16.dp)
    )
}