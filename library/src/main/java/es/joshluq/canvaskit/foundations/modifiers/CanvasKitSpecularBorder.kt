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
 * - At the top rim, a crisp highlight catches the light using the active theme's [brandAccent]
 *   (providing distinct chromatic identity in both Dark and Light modes).
 * - Smoothly transitions into the base subtle border color along the sides and bottom.
 *
 * Calibrated according to Senior UX/UI contrast and visual calm guidelines (22% alpha in light mode,
 * 28% in dark mode) to preserve elegance and avoid visual fatigue in dense list feeds.
 *
 * @param highlightAlpha Optional override for the specular highlight opacity.
 * @param baseBorderColor Base border color along the perimeter.
 * @param highlightColor Highlight color at the top edge (defaults to [brandAccent]).
 */
@Composable
fun rememberSpecularBorderBrush(
    highlightAlpha: Float = if (CanvasKitTheme.colors.isDark) 0.28f else 0.22f,
    baseBorderColor: Color = CanvasKitTheme.colors.borderSubtle,
    highlightColor: Color = CanvasKitTheme.colors.brandAccent,
): Brush {
    val topColor =
        remember(highlightColor, highlightAlpha) {
            highlightColor.copy(alpha = highlightAlpha)
        }
    return remember(topColor, baseBorderColor) {
        Brush.verticalGradient(
            0.0f to topColor,
            0.25f to baseBorderColor,
            1.0f to baseBorderColor,
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
    enabled: Boolean = true,
): Modifier {
    if (!enabled) return this
    val brush = rememberSpecularBorderBrush()
    return this.border(BorderStroke(width, brush), shape)
}

/**
 * Creates an atmospheric halo vertical gradient brush for floating/elevated surfaces ("Atelier Atmospheric Halo").
 *
 * Implements a directional lighting model calibrated specifically for elevated containers:
 * - At the top rim, a crisp specular highlight catches directional ambient key light using the active
 *   theme's [brandAccent] (distinct chromatic glow in Dark mode, rich brand accent in Light mode).
 * - Along lateral borders, it gently softens into a semi-translucent boundary (40% opacity of borderSubtle).
 * - At the bottom edge, it softly dissolves into an ultra-subtle fade (10% opacity), allowing the elevated surface
 *   to blend harmoniously into the ambient shadow underneath without a harsh or rigid bottom line.
 *
 * @param highlightAlpha Optional override for the specular highlight opacity at the top edge.
 * @param baseBorderColor Base border color along lateral edges.
 * @param highlightColor Highlight color at the top edge (defaults to [brandAccent]).
 */
@Composable
fun rememberAtmosphericHaloBrush(
    highlightAlpha: Float = if (CanvasKitTheme.colors.isDark) 0.32f else 0.28f,
    baseBorderColor: Color = CanvasKitTheme.colors.borderSubtle,
    highlightColor: Color = CanvasKitTheme.colors.brandAccent,
): Brush {
    val topColor =
        remember(highlightColor, highlightAlpha) {
            highlightColor.copy(alpha = highlightAlpha)
        }
    val lateralColor =
        remember(baseBorderColor) {
            baseBorderColor.copy(alpha = 0.40f)
        }
    val bottomColor =
        remember(baseBorderColor) {
            baseBorderColor.copy(alpha = 0.10f)
        }
    return remember(topColor, lateralColor, bottomColor) {
        Brush.verticalGradient(
            0.0f to topColor,
            0.30f to lateralColor,
            1.0f to bottomColor,
        )
    }
}

/**
 * Modifier that applies the signature Atelier Atmospheric Halo border to floating/elevated containers.
 *
 * @param shape Shape of the container.
 * @param width Border stroke thickness (defaults to 1.dp).
 * @param enabled Whether the atmospheric halo border is active.
 */
@Composable
fun Modifier.atmosphericHaloBorder(
    shape: Shape,
    width: Dp = 1.dp,
    enabled: Boolean = true,
): Modifier {
    if (!enabled) return this
    val brush = rememberAtmosphericHaloBrush()
    return this.border(BorderStroke(width, brush), shape)
}
