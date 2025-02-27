package com.helloworld.presentation.ui.theme.themeing.abstract_factory

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


interface FontFactory {

    val largeTitle: TextStyle

//    heading
    val heading1Regular: TextStyle
    val heading1Bold: TextStyle
    val heading2Regular: TextStyle
    val heading2Bold: TextStyle
    val heading3Regular: TextStyle
    val heading3Bold: TextStyle
    val heading4Regular: TextStyle
    val heading4Bold: TextStyle
    val heading5Regular: TextStyle
    val heading5Bold: TextStyle

//    title
    val title1Regular: TextStyle
    val title1Medium: TextStyle
    val title1Bold: TextStyle
    val title2Regular: TextStyle
    val title2Medium: TextStyle
    val title2Bold: TextStyle

//    Subtitle
    val subtitle1Regular: TextStyle
    val subtitle1Medium: TextStyle
    val subtitle1Bold: TextStyle
    val subtitle2Regular: TextStyle
    val subtitle2Medium: TextStyle
    val subtitle2Bold: TextStyle
    val subtitle3Regular: TextStyle
    val subtitle3Medium: TextStyle
    val subtitle3Bold: TextStyle

//    body
    val body1Regular: TextStyle
    val body1Medium: TextStyle
    val body1Bold: TextStyle
    val body2Regular: TextStyle
    val body2Medium: TextStyle
    val body2Bold: TextStyle
    val body3Regular: TextStyle
    val body3Medium: TextStyle
    val body3Bold: TextStyle
    val body4Regular: TextStyle
    val body4Medium: TextStyle
    val body4Bold: TextStyle
    val body5Regular: TextStyle
    val body5Medium: TextStyle
    val body5Bold: TextStyle
    val body6Regular: TextStyle
    val body6Medium: TextStyle
    val body6Bold: TextStyle
    val body7Regular: TextStyle
    val body7Medium: TextStyle
    val body7Bold: TextStyle



}
