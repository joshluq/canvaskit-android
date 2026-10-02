package es.joshluq.canvaskit.components.feedback

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Semantic variants for [CanvasKitStatusDot].
 */
enum class CanvasKitStatusDotVariant {
    Success,
    Error,
    Warning,
    Brand,
    Neutral
}

/**
 * CanvasKitStatusDot is an artisanal precision status indicator ("Tactical Radar LED").
 *
 * Implements a dual-ring optical presentation:
 * - A solid inner status core.
 * - An ambient perimeter halo ring that can optionally emit a subtle radar pulse wave.
 *
 * Designed in compliance with Senior UX/UI visual fatigue principles:
 * - Pulses are smooth, unobtrusive ([FastOutSlowInEasing]), and default to static mode ([animatePulse = false]).
 * - Includes localized accessibility descriptions for screen readers.
 *
 * @param modifier Root layout modifier.
 * @param variant Semantic color variant (Success, Error, Warning, Brand, Neutral).
 * @param customColor Optional color override replacing the semantic variant.
 * @param size Diameter of the core inner dot (defaults to 8.dp).
 * @param animatePulse Whether to emit a continuous subtle concentric radar pulse wave.
 * @param contentDescription Localized accessibility description for screen readers (e.g., "Active", "Offline").
 * @param label Optional trailing label slot next to the status dot.
 */
@Composable
fun CanvasKitStatusDot(
    modifier: Modifier = Modifier,
    variant: CanvasKitStatusDotVariant = CanvasKitStatusDotVariant.Success,
    customColor: Color? = null,
    size: Dp = 8.dp,
    animatePulse: Boolean = false,
    contentDescription: String? = null,
    label: (@Composable () -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    val dotColor = customColor ?: when (variant) {
        CanvasKitStatusDotVariant.Success -> colors.success
        CanvasKitStatusDotVariant.Error -> colors.error
        CanvasKitStatusDotVariant.Warning -> colors.warning
        CanvasKitStatusDotVariant.Brand -> colors.brandAccent
        CanvasKitStatusDotVariant.Neutral -> colors.textSecondary
    }

    val accessibilityModifier = if (contentDescription != null) {
        Modifier.semantics { this.contentDescription = contentDescription }
    } else {
        Modifier
    }

    Row(
        modifier = modifier.then(accessibilityModifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // Dot Container
        Box(
            modifier = Modifier.size(size * 2.5f),
            contentAlignment = Alignment.Center
        ) {
            if (animatePulse) {
                val infiniteTransition = rememberInfiniteTransition(label = "RadarPulseTransition")
                val pulseScale by infiniteTransition.animateFloat(
                    initialValue = 1.0f,
                    targetValue = 2.4f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1600, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Restart
                    ),
                    label = "PulseScale"
                )
                val pulseAlpha by infiniteTransition.animateFloat(
                    initialValue = 0.55f,
                    targetValue = 0.0f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1600, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Restart
                    ),
                    label = "PulseAlpha"
                )

                // Concentric expanding pulse wave
                Box(
                    modifier = Modifier
                        .size(size)
                        .graphicsLayer {
                            scaleX = pulseScale
                            scaleY = pulseScale
                            alpha = pulseAlpha
                        }
                        .clip(CircleShape)
                        .background(dotColor)
                )
            } else {
                // Static ambient halo
                Box(
                    modifier = Modifier
                        .size(size + 6.dp)
                        .clip(CircleShape)
                        .background(dotColor.copy(alpha = 0.20f))
                )
            }

            // Solid Core
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(CircleShape)
                    .background(dotColor)
            )
        }

        if (label != null) {
            Spacer(modifier = Modifier.width(spacing.xs))
            label()
        }
    }
}
