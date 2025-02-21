package com.helloworld.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.helloworld.R


internal val EquixFontFamily = FontFamily(
    Font(R.font.nunitosans_regular, EquixFontWeight.Regular),
    Font(R.font.nunitosans_semibold, EquixFontWeight.SemiBold),
    Font(R.font.nunitosans_bold, EquixFontWeight.Bold),
    Font(R.font.nunitosans_extrabold, EquixFontWeight.ExtraBold),
)

/**
 * Font family for brand in the design system
 */
internal val EquixBrandFontFamily = FontFamily(Font(R.font.pacifico_regular))


internal val AppFontFamily = FontFamily(
    Font(R.font.inter_regular),
    Font(R.font.inter_bold),
    Font(R.font.inter_medium),

)

internal val InterRegularFont = FontFamily(Font(R.font.inter_regular))
internal val InterBoldFont = FontFamily(Font(R.font.inter_bold))
internal val InterMediumFont = FontFamily(Font(R.font.inter_medium))