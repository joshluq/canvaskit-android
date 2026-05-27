package es.joshluq.canvaskit.components.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitRadioButton is a premium declarative radio button selection option.
 *
 * @param selected Radio selected status.
 * @param onClick Callback triggered when option is tapped. If null, it is static.
 * @param modifier Root layout modifier.
 * @param enabled When false, reduces opacity and disables interactions.
 */
@Composable
fun CanvasKitRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = CanvasKitTheme.colors
    val motion = CanvasKitTheme.motion

    // Color transition of the outer border
    val ringColor by animateColorAsState(
        targetValue = if (selected) colors.brandAccent else colors.borderSubtle,
        animationSpec = tween(
            durationMillis = motion.short2
        ),
        label = "radioButtonRingColor"
    )

    // Inner dot pop scale & opacity transitions
    val dotScale by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(
            durationMillis = motion.short2,
            easing = motion.standard
        ),
        label = "radioButtonDotScale"
    )

    val dotAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(
            durationMillis = motion.short2,
            easing = motion.standard
        ),
        label = "radioButtonDotAlpha"
    )

    val selectableModifier = if (onClick != null) {
        Modifier.selectable(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            role = Role.RadioButton
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .then(selectableModifier)
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp) // Touch target compliance
            .graphicsLayer {
                alpha = if (enabled) 1.0f else 0.4f
            },
        contentAlignment = Alignment.Center
    ) {
        // Outer border circle
        Box(
            modifier = Modifier
                .size(24.dp)
                .border(width = 2.dp, color = ringColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Inner dot selection indicator
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .graphicsLayer {
                        scaleX = dotScale
                        scaleY = dotScale
                        alpha = dotAlpha
                    }
                    .background(color = colors.brandAccent, shape = CircleShape)
            )
        }
    }
}
