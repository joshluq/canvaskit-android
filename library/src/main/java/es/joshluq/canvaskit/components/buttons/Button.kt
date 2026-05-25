package es.joshluq.canvaskit.components.buttons

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Variants for the CanvasKitButton component.
 */
enum class CanvasKitButtonVariant {
    Primary,
    Secondary,
    Ghost
}

/**
 * CanvasKitButton is a highly-polished, customizable, and accessible button component.
 * It strictly adheres to design tokens for colors, typography, shapes, and motion.
 *
 * @param onClick Callback to run on click.
 * @param modifier Root layout modifier.
 * @param variant Visual button variant (Primary, Secondary, Ghost).
 * @param enabled Controls whether the button is clickable and visual state.
 * @param loading Shows a progress spinner and prevents clicks when true.
 * @param interactionSource Custom interaction source to track state.
 * @param content Composable slot for the button content.
 */
@Composable
fun CanvasKitButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: CanvasKitButtonVariant = CanvasKitButtonVariant.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()

    // 1. Press micro-animation (Scale down on press)
    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled && !loading) 0.97f else 1.0f,
        animationSpec = tween(durationMillis = CanvasKitTheme.motion.short1, easing = CanvasKitTheme.motion.standard),
        label = "ButtonScale"
    )

    // 2. Disabled/Loading alpha
    val contentAlpha = if (enabled) 1.0f else 0.38f

    // 3. Dynamic styling based on variant & theme tokens
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    val backgroundColor = when (variant) {
        CanvasKitButtonVariant.Primary -> if (enabled) colors.brandAccent else colors.borderSubtle
        CanvasKitButtonVariant.Secondary -> Color.Transparent
        CanvasKitButtonVariant.Ghost -> Color.Transparent
    }

    val contentColor = when (variant) {
        CanvasKitButtonVariant.Primary -> colors.backgroundPrimary
        CanvasKitButtonVariant.Secondary -> colors.brandPrimary
        CanvasKitButtonVariant.Ghost -> colors.brandAccent
    }

    val borderStroke = when (variant) {
        CanvasKitButtonVariant.Secondary -> BorderStroke(
            width = 1.dp,
            color = if (enabled) colors.borderSubtle else colors.borderSubtle.copy(alpha = 0.5f)
        )
        else -> null
    }

    Box(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .alpha(contentAlpha)
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp) // Minimum A11y touch target
            .clip(shapes.medium)
            .then(
                if (borderStroke != null) Modifier.border(borderStroke, shapes.medium) else Modifier
            )
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Disable default ripple to showcase clean scale animation
                enabled = enabled && !loading,
                role = Role.Button,
                onClick = onClick
            )
            .padding(horizontal = spacing.md, vertical = spacing.sm)
            .semantics(mergeDescendants = true) {
                role = Role.Button
                if (loading) {
                    stateDescription = "Cargando" // Screen reader loading status
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CanvasKitLoadingSpinner(color = contentColor)
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.xs, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        }
    }
}

/**
 * Custom canvas-based loading spinner. Avoids direct dependencies on Material3 components.
 */
@Composable
internal fun CanvasKitLoadingSpinner(
    color: Color,
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "SpinnerTransition")
    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "SpinnerAngle"
    )

    Canvas(modifier = modifier.size(20.dp)) {
        drawArc(
            color = color,
            startAngle = angle,
            sweepAngle = 270f,
            useCenter = false,
            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}
