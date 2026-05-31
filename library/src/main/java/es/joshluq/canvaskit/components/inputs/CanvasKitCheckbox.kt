package es.joshluq.canvaskit.components.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitCheckbox is a premium decorative checkbox.
 *
 * @param checked Checked status of the checkbox.
 * @param onCheckedChange Callback fired when checked state toggles. If null, it is static.
 * @param modifier Root layout modifier.
 * @param enabled When false, reduces opacity and disables interactions.
 */
@Composable
fun CanvasKitCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val motion = CanvasKitTheme.motion

    val interactionSource = remember { MutableInteractionSource() }

    // Color animations
    val backgroundColor by animateColorAsState(
        targetValue = if (checked) colors.brandAccent else Color.Transparent,
        animationSpec = tween(
            durationMillis = motion.short2
        ),
        label = "checkboxBgColor"
    )

    val borderColor by animateColorAsState(
        targetValue = if (checked) colors.brandAccent else colors.borderSubtle,
        animationSpec = tween(
            durationMillis = motion.short2
        ),
        label = "checkboxBorderColor"
    )

    // Checkmark animations
    val checkScale by animateFloatAsState(
        targetValue = if (checked) 1f else 0.7f,
        animationSpec = tween(
            durationMillis = motion.short2,
            easing = motion.standard
        ),
        label = "checkboxCheckScale"
    )

    val checkAlpha by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(
            durationMillis = motion.short2,
            easing = motion.standard
        ),
        label = "checkboxCheckAlpha"
    )

    val toggleableModifier = if (onCheckedChange != null) {
        Modifier.toggleable(
            value = checked,
            onValueChange = onCheckedChange,
            enabled = enabled,
            role = Role.Checkbox,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .then(toggleableModifier)
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp) // Touch target size compliance
            .graphicsLayer {
                alpha = if (enabled) 1.0f else 0.4f
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(color = backgroundColor, shape = shapes.small)
                .border(width = 2.dp, color = borderColor, shape = shapes.small),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null, // Visual indicator, semantics are merged with the toggleable container
                tint = colors.backgroundPrimary,
                modifier = Modifier
                    .size(16.dp)
                    .graphicsLayer {
                        scaleX = checkScale
                        scaleY = checkScale
                        alpha = checkAlpha
                    }
            )
        }
    }
}
