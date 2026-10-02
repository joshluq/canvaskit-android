package es.joshluq.canvaskit.core.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import es.joshluq.canvaskit.R

/**
 * Inter font family definition for CanvasKit.
 */
val InterFontFamily =
    FontFamily(
        Font(R.font.inter_light, FontWeight.Light),
        Font(R.font.inter, FontWeight.Normal),
        Font(R.font.inter_medium, FontWeight.Medium),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
        Font(R.font.inter_bold, FontWeight.Bold),
        Font(R.font.inter_black, FontWeight.Black),
    )

/**
 * Custom typography scale definitions for CanvasKit.
 * Inspired by clean geometric sans-serif type scales.
 */
@Immutable
data class CanvasKitTypography(
    val displayLarge: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Black,
            fontSize = 42.sp,
            lineHeight = 48.sp,
            letterSpacing = (-1.5).sp,
            fontFeatureSettings = "tnum",
        ),
    val displayMedium: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Black,
            fontSize = 34.sp,
            lineHeight = 40.sp,
            letterSpacing = (-1).sp,
            fontFeatureSettings = "tnum",
        ),
    val headingLarge: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.5).sp,
            fontFeatureSettings = "tnum",
        ),
    val headingMedium: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            lineHeight = 30.sp,
            letterSpacing = (-0.25).sp,
            fontFeatureSettings = "tnum",
        ),
    val bodyLarge: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.25.sp,
        ),
    val bodyMedium: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.1.sp,
        ),
    val labelLarge: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val labelSmall: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.1.sp,
        ),
    val overline: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 1.5.sp,
            fontFeatureSettings = "tnum",
        ),
    val tabularNumber: TextStyle =
        TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp,
            fontFeatureSettings = "tnum",
        ),
)

/**
 * CompositionLocal key for [CanvasKitTypography].
 */
val LocalCanvasKitTypography =
    staticCompositionLocalOf {
        CanvasKitTypography()
    }
