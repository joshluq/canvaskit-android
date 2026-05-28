package es.joshluq.canvaskit.components.feedback

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
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

/**
 * CanvasKitBanner is a full-width, prominent notification ribbon following the
 * "Artisanal Precision" design language. It supports an animated enter/exit transition,
 * a colored left accent stripe that communicates the semantic variant, an optional
 * dismiss action, and a trailing action slot for contextual CTAs.
 *
 * It uses `LiveRegionMode.Polite` so screen readers announce banner content changes
 * immediately without interrupting the current focus.
 *
 * @param variant The semantic state of the banner (Info, Success, Warning, Error).
 * @param message Composable content block for the body text of the banner.
 * @param modifier Root layout modifier.
 * @param visible Whether the banner should be visible. Drives the animated entry/exit.
 * @param title Optional composable for a bold banner headline above the message.
 * @param icon Optional leading icon slot. Defaults to a variant-appropriate icon.
 * @param action Optional trailing composable for a CTA (e.g., a text button "Retry").
 * @param onDismiss When non-null, renders a close button that triggers this callback.
 */
@Composable
fun CanvasKitBanner(
    variant: CanvasKitAlertVariant,
    message: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    title: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing
    val motion = CanvasKitTheme.motion

    val (contentColor, containerColor) = variant.resolveColors(colors)
    val accentColor = contentColor

    val animSpec = tween<Float>(durationMillis = motion.medium1, easing = motion.standard)

    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(
            animationSpec = tween(durationMillis = motion.medium1, easing = motion.standard)
        ) + fadeIn(animationSpec = animSpec),
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = motion.short2, easing = motion.standard)
        ) + fadeOut(animationSpec = tween(durationMillis = motion.short2, easing = motion.standard)),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shapes.medium)
                .background(containerColor)
                // Left accent stripe drawn via Canvas for pixel-perfect precision
                .drawBehind {
                    drawRect(
                        color = accentColor,
                        topLeft = Offset.Zero,
                        size = androidx.compose.ui.geometry.Size(4.dp.toPx(), size.height)
                    )
                }
                .semantics {
                    liveRegion = LiveRegionMode.Polite
                }
                .padding(start = spacing.md + 4.dp, end = spacing.sm, top = spacing.sm, bottom = spacing.sm)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                // Leading icon
                Box(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .size(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (icon != null) {
                        icon()
                    } else {
                        Icon(
                            imageVector = variant.defaultIcon(),
                            contentDescription = null,
                            tint = contentColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(spacing.sm))

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
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar notificación",
                            tint = contentColor.copy(alpha = 0.7f),
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
    CanvasKitAlertVariant.Info -> AlertColors(colors.brandAccent, colors.brandAccent.copy(alpha = 0.10f))
    CanvasKitAlertVariant.Success -> AlertColors(colors.success, colors.successContainer)
    CanvasKitAlertVariant.Warning -> AlertColors(colors.warning, colors.warningContainer)
    CanvasKitAlertVariant.Error -> AlertColors(colors.error, colors.errorContainer)
}

internal fun CanvasKitAlertVariant.defaultIcon() = when (this) {
    CanvasKitAlertVariant.Info -> Icons.Default.Info
    CanvasKitAlertVariant.Success -> Icons.Default.CheckCircle
    CanvasKitAlertVariant.Warning -> Icons.Default.Warning
    CanvasKitAlertVariant.Error -> Icons.Default.Cancel
}
