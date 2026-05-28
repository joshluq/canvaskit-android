package es.joshluq.canvaskit.components.feedback

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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitInlineAlert is a compact, contextual alert block designed to be embedded
 * within content columns, form sections, or card bodies. Unlike [CanvasKitBanner]
 * which is a full-width system notification, the InlineAlert communicates localized
 * feedback directly adjacent to the content it relates to.
 *
 * It supports 4 semantic variants via [CanvasKitAlertVariant] and uses
 * `LiveRegionMode.Polite` so screen readers announce content changes without
 * interrupting current focus.
 *
 * @param variant The semantic state of the alert (Info, Success, Warning, Error).
 * @param message Composable content block for the body text.
 * @param modifier Root layout modifier.
 * @param icon Optional leading icon slot. Defaults to a variant-appropriate icon.
 * @param title Optional composable for a bold headline above the message body.
 * @param action Optional trailing composable for an inline action (e.g., a text link).
 */
@Composable
fun CanvasKitInlineAlert(
    variant: CanvasKitAlertVariant,
    message: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    title: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    val contentColor = when (variant) {
        CanvasKitAlertVariant.Info    -> colors.brandAccent
        CanvasKitAlertVariant.Success -> colors.success
        CanvasKitAlertVariant.Warning -> colors.warning
        CanvasKitAlertVariant.Error   -> colors.error
    }
    val containerColor = when (variant) {
        CanvasKitAlertVariant.Info    -> colors.brandAccent.copy(alpha = 0.08f)
        CanvasKitAlertVariant.Success -> colors.successContainer
        CanvasKitAlertVariant.Warning -> colors.warningContainer
        CanvasKitAlertVariant.Error   -> colors.errorContainer
    }
    val borderColor = contentColor.copy(alpha = 0.30f)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shapes.medium)
            .background(containerColor)
            .border(width = 0.5.dp, color = borderColor, shape = shapes.medium)
            .padding(horizontal = spacing.sm, vertical = spacing.sm)
            .semantics { liveRegion = LiveRegionMode.Polite },
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        // Leading icon
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .size(18.dp),
            contentAlignment = Alignment.Center
        ) {
            if (icon != null) {
                icon()
            } else {
                Icon(
                    imageVector = variant.defaultIcon(),
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        // Text content column
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            title?.invoke()
            message()
        }

        // Optional trailing action
        if (action != null) {
            Spacer(modifier = Modifier.width(spacing.xxs))
            Box(contentAlignment = Alignment.CenterStart) {
                action()
            }
        }
    }
}
