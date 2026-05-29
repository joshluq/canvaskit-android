package es.joshluq.canvaskit.core.tokens

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Motion and animation tokens for CanvasKit.
 */
@Immutable
data class CanvasKitMotion(
    // Durations in milliseconds
    val short1: Int = 100,
    val short2: Int = 200,
    val medium1: Int = 250,
    val medium2: Int = 400,
    val long1: Int = 500,
    val long2: Int = 800,

    // Easings
    val standard: Easing = FastOutSlowInEasing,
    val decelerate: Easing = LinearOutSlowInEasing,
    val accelerate: Easing = FastOutLinearInEasing,

    // Scales
    val pressedScale: Float = 0.97f
)

/**
 * CompositionLocal key for [CanvasKitMotion].
 */
val LocalCanvasKitMotion = staticCompositionLocalOf {
    CanvasKitMotion()
}
