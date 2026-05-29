package es.joshluq.canvaskit.components.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.feedback.CanvasKitLoadingSpinner
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import es.joshluq.canvaskit.core.tokens.White

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
    val theme = CanvasKitTheme

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled && !loading) theme.motion.pressedScale else 1.0f,
        animationSpec = tween(durationMillis = theme.motion.short1, easing = theme.motion.standard),
        label = "ButtonScale"
    )

    val contentAlpha = if (enabled) theme.opacity.full else theme.opacity.disabled

    val colors = theme.colors
    val shapes = theme.shapes
    val spacing = theme.spacing

    val backgroundColor = when (variant) {
        CanvasKitButtonVariant.Primary -> if (enabled) colors.brandAccent else colors.borderSubtle
        CanvasKitButtonVariant.Secondary -> Color.Transparent
        CanvasKitButtonVariant.Ghost -> Color.Transparent
    }

    val contentColor = when (variant) {
        CanvasKitButtonVariant.Primary -> White
        CanvasKitButtonVariant.Secondary -> colors.brandPrimary
        CanvasKitButtonVariant.Ghost -> colors.brandAccent
    }

    val borderStroke = when (variant) {
        CanvasKitButtonVariant.Secondary -> BorderStroke(
            width = theme.stroke.thin,
            color = if (enabled) colors.borderSubtle else colors.borderSubtle.copy(alpha = theme.opacity.subtle)
        )
        else -> null
    }

    Box(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .alpha(contentAlpha)
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            .clip(shapes.pill)
            .then(
                if (borderStroke != null) Modifier.border(borderStroke, shapes.pill) else Modifier
            )
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled && !loading,
                role = Role.Button,
                onClick = onClick
            )
            .padding(horizontal = spacing.md, vertical = spacing.sm)
            .semantics(mergeDescendants = true) {
                role = Role.Button
                if (loading) {
                    stateDescription = "Loading"
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CanvasKitLoadingSpinner(color = contentColor)
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    spacing.xs,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        }
    }
}
