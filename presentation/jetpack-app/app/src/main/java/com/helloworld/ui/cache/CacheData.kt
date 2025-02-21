package com.helloworld.ui.cache

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.helloworld.eventemitter.Listener

val dicFunc = mutableMapOf<String, MutableList<Listener>>()
val dicMidd = mutableMapOf<String, Any>()

var text = mutableStateOf("hello")

fun getText(): String {
    return text.value
}

fun setText(string: String) {
    text.value = string
}

fun setValue(key : String ,value :Any) {
    dicMidd[key] = value
}

fun getValue() {
    dicMidd
}

fun getValueByKey(key: String): String? { // Return String? to handle nulls
    val value = dicMidd[key]
    return value as? String // Safe cast using as?
}