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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import es.joshluq.canvaskit.components.feedback.CanvasKitLoadingSpinner
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
 * Size variants for the CanvasKitButton component.
 */
enum class CanvasKitButtonSize {
    Small,
    Medium,
    Large
}

/**
 * Icon positions for the CanvasKitButton component.
 */
enum class CanvasKitButtonIconPosition {
    Leading,
    Trailing
}

/**
 * CanvasKitButton is a highly-polished, customizable, and accessible button component.
 * It strictly adheres to design tokens for colors, typography, shapes, and motion.
 *
 * ### Best Practices:
 * - **Standard Use:** Prefer [text] and [icon] parameters for the majority of use cases. This ensures the button correctly applies system-standard spacing and alignment.
 * - **Custom Content:** Only use the [content] slot for non-standard layouts. If [content] is provided, [text] and [icon] are ignored.
 * - **Sizing:** Use [size] to adjust the button's scale (Small, Medium, Large) which automatically manages paddings and typography scales.
 * - **Feedback:** Includes built-in haptic feedback and scale-based motion for a premium interactive feel.
 *
 * @param onClick Callback to run on click.
 * @param modifier Root layout modifier.
 * @param variant Visual button variant (Primary, Secondary, Ghost).
 * @param size Scale of the button (Small, Medium, Large).
 * @param enabled Controls whether the button is clickable and visual state.
 * @param loading Shows a progress spinner and prevents clicks when true.
 * @param text Optional label text for the button.
 * @param icon Optional leading or trailing icon.
 * @param iconPosition Position of the icon relative to the text.
 * @param interactionSource Custom interaction source to track state.
 * @param content Custom composable slot for the button content. If provided, [text] and [icon] are ignored.
 */
@Composable
fun CanvasKitButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: CanvasKitButtonVariant = CanvasKitButtonVariant.Primary,
    size: CanvasKitButtonSize = CanvasKitButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    text: String? = null,
    icon: ImageVector? = null,
    iconPosition: CanvasKitButtonIconPosition = CanvasKitButtonIconPosition.Leading,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable RowScope.(contentColor: Color) -> Unit)? = null
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val haptic = LocalHapticFeedback.current
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
        CanvasKitButtonVariant.Primary -> if (enabled) colors.onBrandAccent else colors.brandPrimary
        CanvasKitButtonVariant.Secondary -> colors.brandPrimary
        CanvasKitButtonVariant.Ghost -> colors.brandPrimary
    }

    val borderStroke = when (variant) {
        CanvasKitButtonVariant.Secondary -> BorderStroke(
            width = theme.stroke.thin,
            color = colors.borderSubtle
        )
        else -> null
    }

    // Size-based adjustments
    val minHeight = when (size) {
        CanvasKitButtonSize.Small -> 32.dp
        CanvasKitButtonSize.Medium -> 44.dp
        CanvasKitButtonSize.Large -> 56.dp
    }

    val horizontalPadding = when (size) {
        CanvasKitButtonSize.Small -> spacing.sm
        CanvasKitButtonSize.Medium -> spacing.md
        CanvasKitButtonSize.Large -> spacing.lg
    }

    val verticalPadding = when (size) {
        CanvasKitButtonSize.Small -> spacing.xs
        CanvasKitButtonSize.Medium -> spacing.sm
        CanvasKitButtonSize.Large -> spacing.md
    }

    val textStyle = when (size) {
        CanvasKitButtonSize.Small -> theme.typography.labelSmall
        CanvasKitButtonSize.Medium -> theme.typography.labelLarge
        CanvasKitButtonSize.Large -> theme.typography.headingMedium
    }

    val iconSize = when (size) {
        CanvasKitButtonSize.Small -> 16.dp
        CanvasKitButtonSize.Medium -> 20.dp
        CanvasKitButtonSize.Large -> 24.dp
    }

    Box(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .alpha(contentAlpha)
            .defaultMinSize(minWidth = minHeight, minHeight = minHeight)
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
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick()
                }
            )
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            .semantics(mergeDescendants = true) {
                role = Role.Button
                if (loading) {
                    stateDescription = "Loading"
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CanvasKitLoadingSpinner(color = contentColor, modifier = Modifier.size(iconSize))
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    spacing.xs,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (content != null) {
                    content(contentColor)
                } else {
                    if (icon != null && iconPosition == CanvasKitButtonIconPosition.Leading) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(iconSize),
                            tint = contentColor
                        )
                    }

                    if (text != null) {
                        Text(
                            text = text,
                            style = textStyle,
                            color = contentColor
                        )
                    }

                    if (icon != null && iconPosition == CanvasKitButtonIconPosition.Trailing) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(iconSize),
                            tint = contentColor
                        )
                    }
                }
            }
        }
    }
}
