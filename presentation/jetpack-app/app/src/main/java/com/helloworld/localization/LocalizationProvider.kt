package com.helloworld.localization

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object LocalizationProvider {
    var languageFactory by mutableStateOf<LocalizationFactory>(Language())
}

fun setLanguageApp() {
    // get config

    LocalizationProvider.languageFactory = Language().apply {
        login = "1231"
    }

}