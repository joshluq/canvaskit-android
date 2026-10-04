package es.joshluq.canvaskit.components.feedback

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Semantic variants for [CanvasKitBanner] and [CanvasKitInlineAlert].
 */
enum class CanvasKitAlertVariant {
    /** Informational context — uses the brand accent color. */
    Info,

    /** Positive outcome or confirmation. */
    Success,

    /** Caution or potential issue. */
    Warning,

    /** Destructive state, error, or failure. */
    Error
}

internal fun CanvasKitAlertVariant.toStatusDotVariant(): CanvasKitStatusDotVariant = when (this) {
    CanvasKitAlertVariant.Info -> CanvasKitStatusDotVariant.Brand
    CanvasKitAlertVariant.Success -> CanvasKitStatusDotVariant.Success
    CanvasKitAlertVariant.Warning -> CanvasKitStatusDotVariant.Warning
    CanvasKitAlertVariant.Error -> CanvasKitStatusDotVariant.Error
}

/**
 * CanvasKitBanner is a premium, floating notification module following the
 * Material 3 Expressive "Toast" style. It features a detached silhouette with
 * 24dp rounding, directional specular border, and live radar status LED.
 *
 * @param variant The semantic state of the banner (Info, Success, Warning, Error).
 * @param message Composable content block for the body text of the banner.
 * @param modifier Root layout modifier.
 * @param visible Whether the banner should be visible. Drives the animated entry/exit.
 * @param animatePulse Whether the status indicator should emit concentric radar pulses.
 * @param title Optional composable for a bold banner headline above the message.
 * @param icon Optional leading icon slot. Defaults to a variant-appropriate [CanvasKitStatusDot].
 * @param action Optional trailing composable for a CTA (e.g., a text button "Retry").
 * @param onDismiss When non-null, renders a close button that triggers this callback.
 */
@Composable
fun CanvasKitBanner(
    variant: CanvasKitAlertVariant,
    message: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    animatePulse: Boolean = true,
    title: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    val (contentColor, containerColor) = variant.resolveColors(colors)

    val specularBorderBrush = remember(contentColor, colors.isDark) {
        Brush.verticalGradient(
            0.0f to contentColor.copy(alpha = if (colors.isDark) 0.35f else 0.40f),
            0.35f to contentColor.copy(alpha = 0.15f),
            1.0f to contentColor.copy(alpha = 0.05f)
        )
    }

    // Correctly-typed spring specs per animation target type
    val floatSpring = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMediumLow
    )
    val intOffsetSpring = spring<androidx.compose.ui.unit.IntOffset>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMediumLow
    )

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(animationSpec = intOffsetSpring, initialOffsetY = { -it }) +
            fadeIn(animationSpec = floatSpring) +
            scaleIn(initialScale = 0.9f, animationSpec = floatSpring),
        exit = slideOutVertically(animationSpec = intOffsetSpring, targetOffsetY = { -it }) +
            fadeOut(animationSpec = floatSpring) +
            scaleOut(targetScale = 0.9f, animationSpec = floatSpring),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.md, vertical = spacing.sm)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .shadow(elevation = 8.dp, shape = shapes.container)
                .clip(shapes.container)
                .background(containerColor)
                .border(BorderStroke(1.dp, specularBorderBrush), shapes.container)
                .semantics {
                    liveRegion = LiveRegionMode.Polite
                }
                .padding(spacing.md)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Leading indicator (Icon or Tactical Radar Status Dot)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shapes.pill)
                        .background(contentColor.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    if (icon != null) {
                        icon()
                    } else {
                        CanvasKitStatusDot(
                            variant = variant.toStatusDotVariant(),
                            animatePulse = animatePulse,
                            size = 8.dp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(spacing.md))

                // Text content column
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    title?.invoke()
                    message()
                    if (action != null) {
                        Box(modifier = Modifier.padding(top = spacing.xxs)) {
                            action()
                        }
                    }
                }

                // Dismiss button
                if (onDismiss != null) {
                    CanvasKitIconButton(
                        onClick = onDismiss,
                        contentDescription = "Close notification"
                        // No size modifier here — CanvasKitIconButton's own defaultMinSize(48.dp) governs the touch target
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = contentColor.copy(alpha = 0.5f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Internal helpers — shared with CanvasKitInlineAlert via CanvasKitAlertDefaults.kt
// ---------------------------------------------------------------------------

@Immutable
internal data class AlertColors(val contentColor: Color, val containerColor: Color)

@Composable
internal fun CanvasKitAlertVariant.resolveColors(
    colors: es.joshluq.canvaskit.core.tokens.CanvasKitColors
): AlertColors = when (this) {
    CanvasKitAlertVariant.Info -> AlertColors(colors.brandAccent, colors.backgroundPrimary)
    CanvasKitAlertVariant.Success -> AlertColors(colors.success, colors.backgroundPrimary)
    CanvasKitAlertVariant.Warning -> AlertColors(colors.warning, colors.backgroundPrimary)
    CanvasKitAlertVariant.Error -> AlertColors(colors.error, colors.backgroundPrimary)
}

internal fun CanvasKitAlertVariant.defaultIcon() = when (this) {
    CanvasKitAlertVariant.Info -> Icons.Default.Info
    CanvasKitAlertVariant.Success -> Icons.Default.CheckCircle
    CanvasKitAlertVariant.Warning -> Icons.Default.Warning
    CanvasKitAlertVariant.Error -> Icons.Default.Cancel
}
