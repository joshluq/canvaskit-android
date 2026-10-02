package es.joshluq.canvaskit.components.feedback

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Height / thickness sizing variants for linear progress bars.
 */
@Immutable
enum class CanvasKitProgressBarSize(val height: Dp) {
    Small(4.dp),
    Medium(8.dp),
    Large(12.dp)
}

/**
 * CanvasKitLinearProgressBar is a refined horizontal progress track adhering to Atelier Design System principles.
 * Supports both determinate percentages (0.0f to 1.0f) and indeterminate continuous loading.
 *
 * @param progress Progress fraction between 0.0f and 1.0f. When null, renders indeterminate animation.
 * @param modifier Root layout modifier.
 * @param size Height sizing ([CanvasKitProgressBarSize.Small], [CanvasKitProgressBarSize.Medium], or [CanvasKitProgressBarSize.Large]).
 * @param color Progress indicator color. Defaults to [CanvasKitColors.brandAccent].
 * @param trackColor Inactive track background color. Defaults to [CanvasKitColors.backgroundSecondary].
 */
@Composable
fun CanvasKitLinearProgressBar(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    size: CanvasKitProgressBarSize = CanvasKitProgressBarSize.Medium,
    color: Color = CanvasKitTheme.colors.brandAccent,
    trackColor: Color = CanvasKitTheme.colors.backgroundSecondary
) {
    val shapes = CanvasKitTheme.shapes
    val isIndeterminate = progress == null

    // Spring-based progress animation for determinate mode
    val animatedProgress by animateFloatAsState(
        targetValue = progress?.coerceIn(0f, 1f) ?: 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "LinearProgressBarProgress"
    )

    // Infinite sweep transition for indeterminate mode
    val infiniteTransition = rememberInfiniteTransition(label = "LinearIndeterminateTransition")
    val indeterminateTranslate by infiniteTransition.animateFloat(
        initialValue = -0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "LinearIndeterminateSweep"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(size.height)
            .clip(shapes.pill)
            .background(trackColor)
            .semantics {
                if (progress != null) {
                    progressBarRangeInfo = ProgressBarRangeInfo(
                        current = progress.coerceIn(0f, 1f),
                        range = 0f..1f
                    )
                } else {
                    progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate
                }
            }
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val width = this.size.width
            val height = this.size.height

            if (isIndeterminate) {
                val segmentWidth = width * 0.4f
                val startX = (indeterminateTranslate * width) - (segmentWidth / 2f)
                drawRoundRect(
                    color = color,
                    topLeft = Offset(x = startX.coerceIn(-segmentWidth, width), y = 0f),
                    size = Size(width = segmentWidth, height = height),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(height / 2f, height / 2f)
                )
            } else {
                val fillWidth = width * animatedProgress
                if (fillWidth > 0f) {
                    drawRoundRect(
                        color = color,
                        topLeft = Offset.Zero,
                        size = Size(width = fillWidth, height = height),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(height / 2f, height / 2f)
                    )
                }
            }
        }
    }
}

/**
 * CanvasKitCircularProgressBar renders a circular progress ring with rounded stroke caps.
 * Supports both determinate fractions (0.0f to 1.0f) and indeterminate continuous spinning.
 *
 * @param progress Progress fraction between 0.0f and 1.0f. When null, renders indeterminate animation.
 * @param modifier Root layout modifier.
 * @param size Overall diameter of the circle. Defaults to 40.dp.
 * @param strokeWidth Thickness of the ring. Defaults to 4.dp.
 * @param color Progress indicator color. Defaults to [CanvasKitColors.brandAccent].
 * @param trackColor Inactive track background color. Defaults to [CanvasKitColors.backgroundSecondary].
 */
@Composable
fun CanvasKitCircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    size: Dp = 40.dp,
    strokeWidth: Dp = 4.dp,
    color: Color = CanvasKitTheme.colors.brandAccent,
    trackColor: Color = CanvasKitTheme.colors.backgroundSecondary
) {
    val isIndeterminate = progress == null

    val animatedProgress by animateFloatAsState(
        targetValue = progress?.coerceIn(0f, 1f) ?: 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "CircularProgressBarProgress"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "CircularIndeterminateTransition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1100, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "CircularRotation"
    )

    Canvas(
        modifier = modifier
            .size(size)
            .defaultMinSize(minWidth = size, minHeight = size)
            .semantics {
                if (progress != null) {
                    progressBarRangeInfo = ProgressBarRangeInfo(
                        current = progress.coerceIn(0f, 1f),
                        range = 0f..1f
                    )
                } else {
                    progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate
                }
            }
    ) {
        val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        val diameter = this.size.minDimension
        val radius = (diameter - stroke.width) / 2f
        val centerOffset = Offset(this.size.width / 2f, this.size.height / 2f)

        // Background track circle
        drawCircle(
            color = trackColor,
            radius = radius,
            center = centerOffset,
            style = stroke
        )

        if (isIndeterminate) {
            // Rotating arc with rounded ends
            drawArc(
                color = color,
                startAngle = rotation,
                sweepAngle = 270f,
                useCenter = false,
                topLeft = Offset(centerOffset.x - radius, centerOffset.y - radius),
                size = Size(radius * 2f, radius * 2f),
                style = stroke
            )
        } else {
            // Determinate arc starting from the top (-90 degrees)
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * animatedProgress,
                useCenter = false,
                topLeft = Offset(centerOffset.x - radius, centerOffset.y - radius),
                size = Size(radius * 2f, radius * 2f),
                style = stroke
            )
        }
    }
}
