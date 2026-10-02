package es.joshluq.canvaskit.foundations.modifiers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Creates a specular vertical gradient brush for borders ("Atelier Specular Hairline").
 *
 * Implements a directional lighting model:
 * - At the top rim, a subtle highlight catches the light (translucent White in Dark mode,
 *   subtle brand accent in Light mode).
 * - Smoothly transitions into the base subtle border color along the sides and bottom.
 *
 * Calibrated according to Senior UX/UI contrast and visual calm guidelines (max 22% alpha in light mode,
 * 16% in dark mode) to avoid visual fatigue in dense list feeds.
 *
 * @param highlightAlpha Optional override for the specular highlight opacity.
 * @param baseBorderColor Base border color along the perimeter.
 * @param highlightColor Highlight color at the top edge.
 */
@Composable
fun rememberSpecularBorderBrush(
    highlightAlpha: Float = if (CanvasKitTheme.colors.isDark) 0.16f else 0.22f,
    baseBorderColor: Color = CanvasKitTheme.colors.borderSubtle,
    highlightColor: Color = if (CanvasKitTheme.colors.isDark) Color.White else CanvasKitTheme.colors.brandAccent
): Brush {
    val topColor = remember(highlightColor, highlightAlpha) {
        highlightColor.copy(alpha = highlightAlpha)
    }
    return remember(topColor, baseBorderColor) {
        Brush.verticalGradient(
            0.0f to topColor,
            0.25f to baseBorderColor,
            1.0f to baseBorderColor
        )
    }
}

/**
 * Modifier that applies the signature Atelier specular hairline border to any container.
 *
 * @param shape Shape of the container.
 * @param width Border stroke thickness (defaults to 1.dp).
 * @param enabled Whether the specular border is active.
 */
@Composable
fun Modifier.specularBorder(
    shape: Shape,
    width: Dp = 1.dp,
    enabled: Boolean = true
): Modifier {
    if (!enabled) return this
    val brush = rememberSpecularBorderBrush()
    return this.border(BorderStroke(width, brush), shape)
}
