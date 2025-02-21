package com.helloworld.ui.theme.themeing.fonts

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.helloworld.ui.theme.InterBoldFont
import com.helloworld.ui.theme.InterMediumFont
import com.helloworld.ui.theme.InterRegularFont
import com.helloworld.ui.theme.themeing.abstract_factory.FontFactory

class TypographyFont : FontFactory {
    override var largeTitle = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 36.sp,
    )

    //    heading
    override var heading1Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 56.sp,
        fontWeight = FontWeight.W700
    )
    override var heading1Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 56.sp,
    )
    override var heading2Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 52.sp,
    )
    override var heading2Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 52.sp,
    )
    override var heading3Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 48.sp,
    )
    override var heading3Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 48.sp,
    )
    override var heading4Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 40.sp,
    )
    override var heading4Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 40.sp,
    )
    override var heading5Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 32.sp,
    )
    override var heading5Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 32.sp,
    )

    //    title
    override var title1Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 28.sp,
    )
    override var title1Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 28.sp,
    )
    override var title1Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 28.sp,
    )
    override var title2Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 24.sp,
    )
    override var title2Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 24.sp,
    )
    override var title2Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 24.sp,
    )

    //    Subtitle
    override var subtitle1Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 20.sp,
    )
    override var subtitle1Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 20.sp,
    )
    override var subtitle1Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 20.sp,
    )
    override var subtitle2Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 18.sp,
    )
    override var subtitle2Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 18.sp,
    )
    override var subtitle2Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 18.sp,
    )
    override var subtitle3Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 16.sp,
    )
    override var subtitle3Medium = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 16.sp,
    )
    override var subtitle3Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 16.sp,
    )

    //    body
    override var body1Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 14.sp,
    )
    override var body1Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 14.sp,
    )
    override var body1Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 14.sp,
    )
    override var body2Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 13.sp,
    )
    override var body2Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 13.sp,
    )
    override var body2Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 13.sp,
    )
    override var body3Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 12.sp,
    )
    override var body3Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 12.sp,
    )
    override var body3Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 12.sp,
    )
    override var body4Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 11.sp,
    )
    override var body4Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 11.sp,
    )
    override var body4Bold = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 11.sp,
    )
    override var body5Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 10.sp,
    )
    override var body5Medium = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 10.sp,
    )
    override var body5Bold = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 10.sp,
    )
    override var body6Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 9.sp,
    )
    override var body6Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 9.sp,
    )
    override var body6Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 9.sp,
    )
    override var body7Regular = TextStyle(
        fontFamily = InterRegularFont,
        fontSize = 8.sp,
    )
    override var body7Medium = TextStyle(
        fontFamily = InterMediumFont,
        fontSize = 8.sp,
    )
    override var body7Bold = TextStyle(
        fontFamily = InterBoldFont,
        fontSize = 8.sp,
    )

}