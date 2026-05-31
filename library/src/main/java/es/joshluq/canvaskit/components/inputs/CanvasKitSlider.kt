package es.joshluq.canvaskit.components.inputs

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitSlider is a premium range selector component.
 * It follows Material 3 Expressive aesthetics with organic rounding and "Pure Motion" interaction.
 *
 * @param value Current value of the slider.
 * @param onValueChange Callback triggered when value changes.
 * @param modifier Root layout modifier.
 * @param enabled Whether the slider is interactive.
 * @param valueRange Range of values allowed.
 * @param steps Number of discrete steps. 0 for continuous.
 * @param startIcon Optional icon slot for the minimum value side.
 * @param endIcon Optional icon slot for the maximum value side.
 * @param interactionSource Tracking source for interactions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CanvasKitSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    startIcon: (@Composable () -> Unit)? = null,
    endIcon: (@Composable () -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    val isPressed by interactionSource.collectIsPressedAsState()

    // Pure Motion: Thumb scales up when pressed
    val thumbScale by animateFloatAsState(
        targetValue = if (isPressed && enabled) 1.25f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "SliderThumbScale"
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (startIcon != null) {
            startIcon()
            Spacer(modifier = Modifier.width(spacing.md))
        }

        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            enabled = enabled,
            valueRange = valueRange,
            steps = steps,
            interactionSource = interactionSource,
            colors = SliderDefaults.colors(
                thumbColor = colors.brandAccent,
                activeTrackColor = colors.brandAccent,
                inactiveTrackColor = colors.borderSubtle,
                disabledThumbColor = colors.textSecondary.copy(alpha = 0.38f),
                disabledActiveTrackColor = colors.brandAccent.copy(alpha = 0.38f),
                disabledInactiveTrackColor = colors.borderSubtle.copy(alpha = 0.38f)
            ),
            thumb = {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    thumbSize = DpSize(24.dp.times(thumbScale), 24.dp.times(thumbScale)),
                    modifier = Modifier.graphicsLayer {
                        // The scale is already handled via thumbSize for better layout stability
                    }
                )
            },
            track = { sliderState ->
                SliderDefaults.Track(
                    sliderState = sliderState,
                    modifier = Modifier.height(10.dp),
                    thumbTrackGapSize = 0.dp,
                    trackInsideCornerSize = 5.dp
                )
            }
        )

        if (endIcon != null) {
            Spacer(modifier = Modifier.width(spacing.md))
            endIcon()
        }
    }
}
