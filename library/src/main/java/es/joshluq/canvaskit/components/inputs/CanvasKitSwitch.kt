package es.joshluq.canvaskit.components.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitSwitch is a premium declarative toggle switch.
 *
 * @param checked Toggle checked state.
 * @param onCheckedChange Callback triggered when state switches. If null, component is static.
 * @param modifier Root layout modifier.
 * @param enabled When false, reduces opacity and disables interactions.
 */
@Composable
fun CanvasKitSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val motion = CanvasKitTheme.motion

    // Track color animations
    val trackColor by animateColorAsState(
        targetValue = if (checked) colors.brandAccent else colors.backgroundSecondary,
        animationSpec = tween(
            durationMillis = motion.short2,
            easing = motion.standard
        ),
        label = "switchTrackColor"
    )

    val borderColor by animateColorAsState(
        targetValue = if (checked) Color.Transparent else colors.borderSubtle,
        animationSpec = tween(
            durationMillis = motion.short2,
            easing = motion.standard
        ),
        label = "switchBorderColor"
    )

    // Thumb position & color animations
    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 24.dp else 4.dp,
        animationSpec = tween(
            durationMillis = motion.short2,
            easing = motion.standard
        ),
        label = "switchThumbOffset"
    )

    val thumbColor by animateColorAsState(
        targetValue = if (checked) colors.backgroundPrimary else colors.textSecondary,
        animationSpec = tween(
            durationMillis = motion.short2,
            easing = motion.standard
        ),
        label = "switchThumbColor"
    )

    val toggleableModifier = if (onCheckedChange != null) {
        Modifier.toggleable(
            value = checked,
            onValueChange = onCheckedChange,
            enabled = enabled,
            role = Role.Switch
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .then(toggleableModifier)
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp) // Accessibility min touch targets
            .graphicsLayer {
                alpha = if (enabled) 1.0f else 0.4f
            },
        contentAlignment = Alignment.Center
    ) {
        // Track
        Box(
            modifier = Modifier
                .size(width = 52.dp, height = 32.dp)
                .background(color = trackColor, shape = shapes.pill)
                .border(width = 2.dp, color = borderColor, shape = shapes.pill)
        ) {
            // Thumb
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = thumbOffset)
                    .size(24.dp)
                    .background(color = thumbColor, shape = shapes.pill)
            )
        }
    }
}
