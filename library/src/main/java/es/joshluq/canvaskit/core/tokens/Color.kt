package es.joshluq.canvaskit.core.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// --- Raw Palette ---
internal val DeepNavy = Color(0xFF001E50)
internal val ElectricBlue = Color(0xFF00B0F0)
internal val ElectricBluePressed = Color(0xFF0082C8)
internal val Slate950 = Color(0xFF080C14)
internal val Slate900 = Color(0xFF0F172A)
internal val Slate800 = Color(0xFF1E293B)
internal val Slate400 = Color(0xFF94A3B8)
internal val Gray100 = Color(0xFFF3F5F7)
internal val Gray200 = Color(0xFFE2E8F0)
internal val Gray600 = Color(0xFF5A6578)
internal val White = Color(0xFFFFFFFF)

// Status colors
internal val RedError = Color(0xFFD32F2F)
internal val RedErrorBg = Color(0xFFFFEBEE)
internal val GreenSuccess = Color(0xFF2E7D32)
internal val GreenSuccessBg = Color(0xFFE8F5E9)
internal val Amber500 = Color(0xFFB45309)
internal val AmberBg = Color(0xFFFFFBEB)
internal val AmberDark = Color(0xFFFBBF24)
internal val AmberDarkBg = Color(0xFF1C1506)

/**
 * Semantic color scheme for the CanvasKit Design System.
 */
@Immutable
data class CanvasKitColors(
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val brandPrimary: Color,
    val brandAccent: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val borderSubtle: Color,
    val error: Color,
    val errorContainer: Color,
    val success: Color,
    val successContainer: Color,
    val warning: Color,
    val warningContainer: Color,
    val isDark: Boolean
)

/**
 * Creates a Light Mode instance of [CanvasKitColors].
 */
fun lightCanvasKitColors(
    backgroundPrimary: Color = White,
    backgroundSecondary: Color = Gray100,
    brandPrimary: Color = DeepNavy,
    brandAccent: Color = ElectricBlue,
    textPrimary: Color = DeepNavy,
    textSecondary: Color = Gray600,
    borderSubtle: Color = Gray200,
    error: Color = RedError,
    errorContainer: Color = RedErrorBg,
    success: Color = GreenSuccess,
    successContainer: Color = GreenSuccessBg,
    warning: Color = Amber500,
    warningContainer: Color = AmberBg
): CanvasKitColors = CanvasKitColors(
    backgroundPrimary = backgroundPrimary,
    backgroundSecondary = backgroundSecondary,
    brandPrimary = brandPrimary,
    brandAccent = brandAccent,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    borderSubtle = borderSubtle,
    error = error,
    errorContainer = errorContainer,
    success = success,
    successContainer = successContainer,
    warning = warning,
    warningContainer = warningContainer,
    isDark = false
)

/**
 * Creates a Dark Mode instance of [CanvasKitColors].
 */
fun darkCanvasKitColors(
    backgroundPrimary: Color = Slate950,
    backgroundSecondary: Color = Slate900,
    brandPrimary: Color = White,
    brandAccent: Color = ElectricBlue,
    textPrimary: Color = White,
    textSecondary: Color = Slate400,
    borderSubtle: Color = Slate800,
    error: Color = RedError,
    errorContainer: Color = Slate900,
    success: Color = GreenSuccess,
    successContainer: Color = Slate900,
    warning: Color = AmberDark,
    warningContainer: Color = AmberDarkBg
): CanvasKitColors = CanvasKitColors(
    backgroundPrimary = backgroundPrimary,
    backgroundSecondary = backgroundSecondary,
    brandPrimary = brandPrimary,
    brandAccent = brandAccent,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    borderSubtle = borderSubtle,
    error = error,
    errorContainer = errorContainer,
    success = success,
    successContainer = successContainer,
    warning = warning,
    warningContainer = warningContainer,
    isDark = true
)

/**
 * CompositionLocal key for [CanvasKitColors].
 */
val LocalCanvasKitColors = staticCompositionLocalOf {
    lightCanvasKitColors()
}

/**
 * CompositionLocal key for the preferred content color (e.g., text or icons).
 */
val LocalCanvasKitContentColor = staticCompositionLocalOf {
    DeepNavy
}
