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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitInlineAlert is a compact, contextual alert block designed to be embedded
 * within content columns.
 *
 * @param variant The semantic state of the alert (Info, Success, Warning, Error).
 * @param message Composable content block for the body text.
 * @param modifier Root layout modifier.
 * @param icon Optional leading icon slot.
 * @param title Optional composable for a bold headline.
 * @param action Optional trailing composable for an inline action.
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

    val (contentColor, containerColor) = variant.resolveColors(colors)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = shapes.large)
            .clip(shapes.large)
            .background(containerColor)
            .border(width = 0.5.dp, color = colors.borderSubtle, shape = shapes.large)
            .padding(spacing.md)
            .semantics { liveRegion = LiveRegionMode.Polite }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            // Leading icon in a circular container
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(shapes.pill)
                    .background(contentColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                if (icon != null) {
                    icon()
                } else {
                    Icon(
                        imageVector = variant.defaultIcon(),
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(16.dp)
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
}
