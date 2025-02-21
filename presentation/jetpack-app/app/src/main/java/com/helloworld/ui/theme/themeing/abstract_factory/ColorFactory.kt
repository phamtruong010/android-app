package com.helloworld.ui.theme.themeing.abstract_factory
import androidx.compose.ui.graphics.Color

interface ColorFactory {

    /************************ Main App Background ************************/
    val appBackgroundColor: Color

    /************************ App Bottom Navigation Bar ************************/
    val bottomNavBackgroundColor: Color

    /************************ Buttons ************************/
    val buttonBackgroundColor: Color
    val buttonTextColor: Color

    /************************ Text Inputs ************************/
    val textBoxTextColor: Color
    val textBoxBackgroundColor: Color

    /************************ App Bar ************************/
    val appBarBackgroundColor: Color
    val appBarTextColor: Color

    /************************ Status Bar ************************/
    val statusBarBackgroundColor: Color

    /************************ Card/Container Backgrounds ************************/
    val cardBackgroundColor: Color

    /************************ Divider/Separator Lines ************************/
    val dividerColor: Color

    /************************ Loading/Progress Indicators ************************/
    val loadingSpinnerColor: Color

    /************************ Normal Text ************************/
    val textColor: Color
    val textBgColor: Color

    val bg: Color
    val bg_surface: Color
    val bg_btn_icon: Color
    val bg_indicator: Color

    val text_normal: Color
    val title: Color
    val title_chart: Color

    val line_title: Color
    val line_chart: Color

    val green: Color
    val red: Color
}
