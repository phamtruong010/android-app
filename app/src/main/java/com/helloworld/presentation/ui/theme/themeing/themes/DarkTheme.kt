package com.helloworld.presentation.ui.theme.themeing.themes

import androidx.compose.ui.graphics.Color
import com.helloworld.presentation.ui.theme.AppColor
import com.helloworld.presentation.ui.theme.themeing.abstract_factory.ColorFactory

class DarkTheme : ColorFactory {
    /************************ Main App Background ************************/
    override val appBackgroundColor = Color.Black

    /************************ App Bottom Navigation Bar ************************/
    override val bottomNavBackgroundColor = Color.DarkGray

    /************************ Buttons ************************/
    override val buttonBackgroundColor = Color.White
    override val buttonTextColor = Color.Black

    /************************ Text Inputs ************************/
    override val textBoxTextColor = Color.White
    override val textBoxBackgroundColor = Color.Black

    /************************ App Bar ************************/
    override val appBarBackgroundColor = Color.Gray
    override val appBarTextColor = Color.White

    /************************ Status Bar ************************/
    override val statusBarBackgroundColor = Color.Black

    /************************ Card/Container Backgrounds ************************/
    override val cardBackgroundColor = Color.DarkGray

    /************************ Divider/Separator Lines ************************/
    override val dividerColor = Color.Gray

    /************************ Loading/Progress Indicators ************************/
    override val loadingSpinnerColor = Color.White

    /************************ Normal Text ************************/
    override val textColor = AppColor.DarkGrayD3
    override val textBgColor = Color.Black

    override val bg = AppColor.base_12
    override val bg_surface = AppColor.base_11
    override val bg_btn_icon = AppColor.primary_500
    override val bg_indicator = AppColor.base_3

    override val text_normal = AppColor.base_3
    override val title = AppColor.base_7
    override val title_chart = AppColor.base_8

    override val line_title = AppColor.base_9
    override val line_chart = AppColor.base_8

    override val green = AppColor.green_500
    override val red = AppColor.red_500













}

