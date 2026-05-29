package es.joshluq.canvaskit.core.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Stroke and border width tokens for CanvasKit.
 */
@Immutable
data class CanvasKitStroke(
    val none: Dp = 0.dp,
    val thin: Dp = 1.dp,
    val medium: Dp = 2.dp,
    val thick: Dp = 4.dp
)

/**
 * CompositionLocal key for [CanvasKitStroke].
 */
val LocalCanvasKitStroke = staticCompositionLocalOf {
    CanvasKitStroke()
}
