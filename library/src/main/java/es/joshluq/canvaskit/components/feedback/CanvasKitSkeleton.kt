package es.joshluq.canvaskit.components.feedback

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitSkeleton is a loading placeholder component with a premium shimmer effect.
 * It is built as a single flexible component that inherits its shape and size directly from the given Modifier.
 *
 * @param modifier Root layout modifier. Defines the size, shape (clip), and padding of the skeleton.
 */
@Composable
fun CanvasKitSkeleton(
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors

    Spacer(
        modifier = modifier
            .background(color = colors.borderSubtle.copy(alpha = 0.5f))
            .shimmerEffect(
                shimmerColor = colors.backgroundSecondary.copy(alpha = 0.6f)
            )
            .semantics(mergeDescendants = true) {
                stateDescription = "Cargando"
            }
    )
}

/**
 * Applies an infinite shimmer animation to the component's background.
 */
internal fun Modifier.shimmerEffect(shimmerColor: Color): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val translateAnim by transition.animateFloat(
        initialValue = -1000f,
        targetValue = 2000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "ShimmerTranslate"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            Color.Transparent,
            shimmerColor,
            Color.Transparent
        ),
        start = Offset(translateAnim - 500f, translateAnim - 500f),
        end = Offset(translateAnim, translateAnim)
    )

    this.then(Modifier.background(brush))
}
