package es.joshluq.canvaskit.core.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Opacity and alpha tokens for CanvasKit.
 */
@Immutable
data class CanvasKitOpacity(
    val none: Float = 0f,
    val disabled: Float = 0.38f,
    val subtle: Float = 0.5f,
    val medium: Float = 0.7f,
    val full: Float = 1.0f
)

/**
 * CompositionLocal key for [CanvasKitOpacity].
 */
val LocalCanvasKitOpacity = staticCompositionLocalOf {
    CanvasKitOpacity()
}
