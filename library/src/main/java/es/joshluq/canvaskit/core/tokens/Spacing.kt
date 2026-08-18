package es.joshluq.canvaskit.core.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing grid scale definitions for CanvasKit (multiples of 4dp/8dp).
 */
@Immutable
data class CanvasKitSpacing(
    val none: Dp = 0.dp,
    val xxxs: Dp = 2.dp,
    val xxs: Dp = 4.dp,
    val xs: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 24.dp,
    val xl: Dp = 32.dp,
    val xxl: Dp = 48.dp,
    val xxxl: Dp = 64.dp,

    // Semantic Screen Margins
    val screenHorizontal: Dp = 24.dp,
    val screenVertical: Dp = 16.dp
)

/**
 * CompositionLocal key for [CanvasKitSpacing].
 */
val LocalCanvasKitSpacing = staticCompositionLocalOf {
    CanvasKitSpacing()
}
