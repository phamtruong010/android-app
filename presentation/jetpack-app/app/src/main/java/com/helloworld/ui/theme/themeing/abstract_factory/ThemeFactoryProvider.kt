package com.helloworld.ui.theme.themeing.abstract_factory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.helloworld.ui.theme.themeing.fonts.TypographyFont
import com.helloworld.ui.theme.themeing.themes.LightTheme

object ThemeFactoryProvider {
    var colorFactory by mutableStateOf<ColorFactory>(LightTheme())
    var fontFactory by mutableStateOf<FontFactory>(TypographyFont())
}