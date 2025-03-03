package com.helloworld.presentation.ui.theme



import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Immutable
data class EquixTypography(
    // Titles
    val largeTitle: TextStyle,
    val mediumTitle: TextStyle,
    val smallTitle: TextStyle,
    val xSmallTitle: TextStyle,

    // Headlines
    val largeHeadline: TextStyle,
    val mediumHeadline: TextStyle,
    val smallHeadline: TextStyle,
    val xSmallHeadline: TextStyle,

    // Body
    val largeBody: TextStyle,
    val mediumBody: TextStyle,
    val smallBody: TextStyle,
    val xSmallBody: TextStyle,

    // Buttons
    val largeButton: TextStyle,
    val smallButton: TextStyle,

    // Captions
    val mediumCaption: TextStyle,
    val smallCaption: TextStyle,
    val boldCaption: TextStyle,

    // Misc
    val label: TextStyle,
    val brand: TextStyle,
)

internal val EquixTabletTypography = com.helloworld.presentation.ui.theme.EquixTypography(
    brand = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixBrandFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 36.sp,
    ),
    largeTitle = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 28.sp,
        lineHeight = 52.sp,
    ),
    mediumTitle = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 24.sp,
        lineHeight = 44.sp,
    ),
    smallTitle = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 38.sp,
    ),
    xSmallTitle = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 16.sp,
        lineHeight = 32.sp,
    ),
    largeHeadline = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 24.sp,
        lineHeight = 44.sp,
    ),
    mediumHeadline = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 20.sp,
        lineHeight = 38.sp,
    ),
    smallHeadline = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 16.sp,
        lineHeight = 32.sp,
    ),
    xSmallHeadline = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    ),
    largeBody = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 20.sp,
        lineHeight = 38.sp,
    ),
    mediumBody = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 16.sp,
        lineHeight = 32.sp,
    ),
    smallBody = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    ),
    xSmallBody = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 12.sp,
        lineHeight = 22.sp,
    ),
    largeButton = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    smallButton = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.SemiBold,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    ),
    mediumCaption = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.SemiBold,
        fontSize = 12.sp,
    ),
    smallCaption = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 10.sp,
    ),
    boldCaption = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 10.sp,
        letterSpacing = 0.5.sp,
    ),
    label = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 10.sp,
    ),
)

internal val EquixMobileTypography = com.helloworld.presentation.ui.theme.EquixTypography(
    brand = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixBrandFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 36.sp,
    ),
    largeTitle = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 24.sp,
    ),
    mediumTitle = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 20.sp,
    ),
    smallTitle = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 16.sp,
    ),
    xSmallTitle = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.ExtraBold,
        fontSize = 14.sp,
    ),
    largeHeadline = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 20.sp,
    ),
    mediumHeadline = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 16.sp,
    ),
    smallHeadline = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 14.sp,
    ),
    xSmallHeadline = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 12.sp,
    ),
    largeBody = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 16.sp,
    ),
    mediumBody = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 14.sp,
    ),
    smallBody = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 12.sp,
    ),
    xSmallBody = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 12.sp,
    ),
    largeButton = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.SemiBold,
        fontSize = 14.sp,
    ),
    smallButton = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.SemiBold,
        fontSize = 12.sp,
    ),
    mediumCaption = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.SemiBold,
        fontSize = 12.sp,
    ),
    smallCaption = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Regular,
        fontSize = 10.sp,
    ),
    boldCaption = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 10.sp,
        letterSpacing = 0.5.sp,
    ),
    label = TextStyle(
        fontFamily = com.helloworld.presentation.ui.theme.EquixFontFamily,
        fontWeight = com.helloworld.presentation.ui.theme.EquixFontWeight.Companion.Bold,
        fontSize = 10.sp,
    ),
)

internal val LocalEquixTypography = staticCompositionLocalOf {
    com.helloworld.presentation.ui.theme.EquixTypography(
        brand = TextStyle.Default,
        largeTitle = TextStyle.Default,
        mediumTitle = TextStyle.Default,
        smallTitle = TextStyle.Default,
        xSmallTitle = TextStyle.Default,
        largeHeadline = TextStyle.Default,
        mediumHeadline = TextStyle.Default,
        smallHeadline = TextStyle.Default,
        xSmallHeadline = TextStyle.Default,
        largeBody = TextStyle.Default,
        mediumBody = TextStyle.Default,
        smallBody = TextStyle.Default,
        xSmallBody = TextStyle.Default,
        largeButton = TextStyle.Default,
        smallButton = TextStyle.Default,
        mediumCaption = TextStyle.Default,
        smallCaption = TextStyle.Default,
        boldCaption = TextStyle.Default,
        label = TextStyle.Default,
    )
}