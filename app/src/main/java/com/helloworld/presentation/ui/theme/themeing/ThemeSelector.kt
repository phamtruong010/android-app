package com.helloworld.presentation.ui.theme.themeing

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.helloworld.BuildConfig

import com.helloworld.presentation.ui.theme.themeing.abstract_factory.FontFactory
import com.helloworld.presentation.ui.theme.themeing.abstract_factory.ThemeFactoryProvider
import com.helloworld.presentation.ui.theme.themeing.fonts.TypographyFont
import com.helloworld.presentation.ui.theme.themeing.themes.DarkTheme
import com.helloworld.presentation.ui.theme.themeing.themes.DynamicTheme
import com.helloworld.presentation.ui.theme.themeing.themes.LightTheme
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.*

enum class AppTheme {
    Dark, Light
}

enum class FontApp {
    Small, Medium, Large
}

fun setThemeApp(selectedTheme: AppTheme) {
    ThemeFactoryProvider.colorFactory = when (selectedTheme) {
        AppTheme.Dark -> DarkTheme()
        AppTheme.Light -> com.helloworld.presentation.ui.theme.themeing.themes.LightTheme()
    }
}

fun setThemeDynamicApp() {
    // get config
    //set theme
    ThemeFactoryProvider.colorFactory = DynamicTheme().apply {
        bottomNavBackgroundColor = Color(0xFFFF0000)
    }

}

fun updateFontSize(size: Int = 3) {
    ThemeFactoryProvider.fontFactory = object : FontFactory {
        val oldFactory = ThemeFactoryProvider.fontFactory

        val typographyDefault = TypographyFont()

        override val largeTitle =
            typographyDefault.largeTitle.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading1Regular =
            typographyDefault.heading1Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading1Bold =
            typographyDefault.heading1Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading2Regular =
            typographyDefault.heading2Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading2Bold =
            typographyDefault.heading2Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading3Regular =
            typographyDefault.heading3Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading3Bold =
            typographyDefault.heading3Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading4Regular =
            typographyDefault.heading4Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading4Bold =
            typographyDefault.heading4Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading5Regular =
            typographyDefault.heading4Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val heading5Bold =
            typographyDefault.heading5Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val title1Regular =
            typographyDefault.title1Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val title1Medium =
            typographyDefault.title1Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val title1Bold =
            typographyDefault.title1Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val title2Regular =
            typographyDefault.title2Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val title2Medium =
            typographyDefault.title2Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val title2Bold =
            typographyDefault.title2Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val subtitle1Regular =
            typographyDefault.subtitle1Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val subtitle1Medium =
            typographyDefault.subtitle1Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val subtitle1Bold =
            typographyDefault.subtitle1Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val subtitle2Regular =
            typographyDefault.subtitle2Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val subtitle2Medium =
            typographyDefault.subtitle2Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val subtitle2Bold =
            typographyDefault.subtitle2Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val subtitle3Regular =
            typographyDefault.subtitle3Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val subtitle3Medium =
            typographyDefault.subtitle3Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val subtitle3Bold =
            typographyDefault.subtitle3Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body1Regular =
            typographyDefault.body1Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body1Medium =
            typographyDefault.body1Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body1Bold =
            typographyDefault.body1Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body2Regular =
            typographyDefault.body2Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body2Medium =
            typographyDefault.body2Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body2Bold =
            typographyDefault.body2Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body3Regular =
            typographyDefault.body3Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body3Medium =
            typographyDefault.body3Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body3Bold =
            typographyDefault.body3Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body4Regular =
            typographyDefault.body4Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body4Medium =
            typographyDefault.body4Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body4Bold =
            typographyDefault.body4Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body5Regular =
            typographyDefault.body5Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body5Medium =
            typographyDefault.body5Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body5Bold =
            typographyDefault.body5Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body6Regular =
            typographyDefault.body6Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body6Medium =
            typographyDefault.body6Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body6Bold =
            typographyDefault.body6Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body7Regular =
            typographyDefault.body7Regular.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body7Medium =
            typographyDefault.body7Medium.let { it.copy(fontSize = (it.fontSize.value + size).sp) }
        override val body7Bold =
            typographyDefault.body7Bold.let { it.copy(fontSize = (it.fontSize.value + size).sp) }


        val properties =
            FontFactory::class.members.filterIsInstance<KProperty1<FontFactory, TextStyle?>>()
        val newValues = properties.associateWith { prop ->

            prop.get(oldFactory)?.copy(fontSize = (prop.get(oldFactory)!!.fontSize.value + size).sp)
        }

        init {
            properties.forEach { prop ->
                val field =
                    this::class.members.find { it.name == prop.name } as? KMutableProperty1<Any, Any?>
                field?.set(this, newValues[prop])
            }
        }
    }
}

fun decreaseAllFontSizes() {

}
