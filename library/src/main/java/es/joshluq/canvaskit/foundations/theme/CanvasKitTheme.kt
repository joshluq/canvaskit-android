package es.joshluq.canvaskit.foundations.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import es.joshluq.canvaskit.core.tokens.CanvasKitColors
import es.joshluq.canvaskit.core.tokens.CanvasKitMotion
import es.joshluq.canvaskit.core.tokens.CanvasKitOpacity
import es.joshluq.canvaskit.core.tokens.CanvasKitShapes
import es.joshluq.canvaskit.core.tokens.CanvasKitSpacing
import es.joshluq.canvaskit.core.tokens.CanvasKitStroke
import es.joshluq.canvaskit.core.tokens.CanvasKitTypography
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitColors
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitMotion
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitOpacity
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitShapes
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitSpacing
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitStroke
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitTypography
import es.joshluq.canvaskit.core.tokens.palettes.CanvasPalette
import es.joshluq.canvaskit.core.tokens.palettes.LocalCanvasPalette

/**
 * CanvasKitTheme is the custom CompositionLocalProvider theme entry point.
 */
@Composable
fun CanvasKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    palette: CanvasPalette = CanvasPalette.Navy,
    colors: CanvasKitColors = palette.colors(darkTheme),
    typography: CanvasKitTypography = CanvasKitTypography(),
    shapes: CanvasKitShapes = CanvasKitShapes(),
    spacing: CanvasKitSpacing = CanvasKitSpacing(),
    motion: CanvasKitMotion = CanvasKitMotion(),
    opacity: CanvasKitOpacity = CanvasKitOpacity(),
    stroke: CanvasKitStroke = CanvasKitStroke(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCanvasPalette provides palette,
        LocalCanvasKitColors provides colors,
        LocalCanvasKitTypography provides typography,
        LocalCanvasKitShapes provides shapes,
        LocalCanvasKitSpacing provides spacing,
        LocalCanvasKitMotion provides motion,
        LocalCanvasKitOpacity provides opacity,
        LocalCanvasKitStroke provides stroke,
        content = content
    )
}

/**
 * Static lookup object for accessing CanvasKit Design System tokens from Composables.
 */
object CanvasKitTheme {
    val palette: CanvasPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalCanvasPalette.current

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

    val opacity: CanvasKitOpacity
        @Composable
        @ReadOnlyComposable
        get() = LocalCanvasKitOpacity.current

    val stroke: CanvasKitStroke
        @Composable
        @ReadOnlyComposable
        get() = LocalCanvasKitStroke.current
}
