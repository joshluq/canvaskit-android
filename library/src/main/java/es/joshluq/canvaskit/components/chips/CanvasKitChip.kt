package es.joshluq.canvaskit.components.chips

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Variants for the CanvasKitChip component.
 */
enum class CanvasKitChipVariant {
    Primary,
    Outlined,
    Ghost
}

/**
 * CanvasKitChip is a compact interactive element used for actions, filtering, or selection.
 * It adheres to the "Artisanal Precision" design language and A11y standards.
 *
 * @param onClick Callback to run on click.
 * @param modifier Root layout modifier.
 * @param variant Visual chip variant (Primary, Outlined, Ghost).
 * @param selected Visual state indicating selection (applies mainly to Outlined and Ghost to distinguish them, or Primary to change intensity).
 * @param enabled Controls whether the chip is clickable and visual state.
 * @param leadingIcon Optional leading icon.
 * @param trailingIcon Optional trailing icon (e.g., for dismiss/close action).
 * @param interactionSource Custom interaction source to track state.
 * @param label Composable slot for the chip's text.
 */
@Composable
fun CanvasKitChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: CanvasKitChipVariant = CanvasKitChipVariant.Primary,
    selected: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    label: @Composable () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()

    // 1. Press micro-animation (Scale down on press)
    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) 0.96f else 1.0f,
        animationSpec = tween(durationMillis = CanvasKitTheme.motion.short1, easing = CanvasKitTheme.motion.standard),
        label = "ChipScale"
    )

    // 2. Disabled alpha
    val contentAlpha = if (enabled) 1.0f else 0.38f

    // 3. Dynamic styling based on variant & theme tokens
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    val backgroundColor = when (variant) {
        CanvasKitChipVariant.Primary -> if (selected) colors.brandAccent else colors.backgroundSecondary
        CanvasKitChipVariant.Outlined -> if (selected) colors.brandAccent.copy(alpha = 0.1f) else Color.Transparent
        CanvasKitChipVariant.Ghost -> if (selected) colors.backgroundSecondary else Color.Transparent
    }

    val contentColor = when (variant) {
        CanvasKitChipVariant.Primary -> if (selected) colors.backgroundPrimary else colors.textPrimary
        CanvasKitChipVariant.Outlined -> if (selected) colors.brandAccent else colors.textPrimary
        CanvasKitChipVariant.Ghost -> if (selected) colors.brandAccent else colors.textSecondary
    }

    val borderStroke = when (variant) {
        CanvasKitChipVariant.Outlined -> BorderStroke(
            width = 1.dp,
            color = if (selected) colors.brandAccent else colors.borderSubtle
        )
        CanvasKitChipVariant.Primary, CanvasKitChipVariant.Ghost -> null
    }

    Box(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .alpha(contentAlpha)
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp) // A11y minimum touch target
            .clip(shapes.medium)
            .then(
                if (borderStroke != null) Modifier.border(borderStroke, shapes.medium) else Modifier
            )
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Disable ripple for custom scale animation
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
            .padding(horizontal = spacing.sm, vertical = spacing.xs)
            .semantics(mergeDescendants = true) {
                role = Role.Button
                this.selected = selected
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Box(modifier = Modifier.size(18.dp), contentAlignment = Alignment.Center) {
                    leadingIcon()
                }
                Spacer(modifier = Modifier.width(spacing.xs))
            }

            Box(
                modifier = Modifier.alpha(if (selected) 1f else 0.85f)
            ) {
                // We use a CompositionLocalProvider in a real component to pass contentColor,
                // but since we only provide slots here, consumers should style text.
                // To help consumers, they should use the colors provided or we could wrap the label.
                label()
            }

            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(spacing.xs))
                Box(modifier = Modifier.size(18.dp), contentAlignment = Alignment.Center) {
                    trailingIcon()
                }
            }
        }
    }
}
