package es.joshluq.canvaskit.foundations.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import es.joshluq.canvaskit.core.tokens.CanvasKitColors
import es.joshluq.canvaskit.core.tokens.CanvasKitMotion
import es.joshluq.canvaskit.core.tokens.CanvasKitShapes
import es.joshluq.canvaskit.core.tokens.CanvasKitSpacing
import es.joshluq.canvaskit.core.tokens.CanvasKitTypography
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitColors
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitMotion
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitShapes
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitSpacing
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitTypography
import es.joshluq.canvaskit.core.tokens.darkCanvasKitColors
import es.joshluq.canvaskit.core.tokens.lightCanvasKitColors

/**
 * CanvasKitTheme is the custom CompositionLocalProvider theme entry point.
 */
@Composable
fun CanvasKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: CanvasKitColors = if (darkTheme) darkCanvasKitColors() else lightCanvasKitColors(),
    typography: CanvasKitTypography = CanvasKitTypography(),
    shapes: CanvasKitShapes = CanvasKitShapes(),
    spacing: CanvasKitSpacing = CanvasKitSpacing(),
    motion: CanvasKitMotion = CanvasKitMotion(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCanvasKitColors provides colors,
        LocalCanvasKitTypography provides typography,
        LocalCanvasKitShapes provides shapes,
        LocalCanvasKitSpacing provides spacing,
        LocalCanvasKitMotion provides motion,
        content = content
    )
}

/**
 * Static lookup object for accessing CanvasKit Design System tokens from Composables.
 */
object CanvasKitTheme {
    val colors: CanvasKitColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCanvasKitColors.current

    val typography: CanvasKitTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalCanvasKitTypography.current

    val shapes: CanvasKitShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalCanvasKitShapes.current

    val spacing: CanvasKitSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalCanvasKitSpacing.current

    val motion: CanvasKitMotion
        @Composable
        @ReadOnlyComposable
        get() = LocalCanvasKitMotion.current
}
