package es.joshluq.canvaskit.components.lists

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitListItem is a premium molecular row component for lists.
 * It follows Material 3 Expressive guidelines with high-density slots and artisanal interaction.
 *
 * @param headline The principal text of the item.
 * @param modifier Root layout modifier.
 * @param supportingText Optional secondary text below the headline.
 * @param leadingContent Optional leading slot for icons, avatars, or checkboxes.
 * @param trailingContent Optional trailing slot for switches, badges, or metadata.
 * @param onClick Optional callback for click events. If null, the item is not clickable.
 * @param enabled Whether the item is interactive.
 * @param selected Whether the item is in a selected state.
 */
@Composable
fun CanvasKitListItem(
    headline: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    supportingText: (@Composable () -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    selected: Boolean = false
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val motion = CanvasKitTheme.motion

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Pure Motion: Background color animation instead of ripple
    val backgroundColor by animateColorAsState(
        targetValue = when {
            selected -> colors.brandAccent.copy(alpha = 0.12f)
            isPressed -> colors.textPrimary.copy(alpha = 0.05f)
            else -> Color.Transparent
        },
        animationSpec = tween(durationMillis = motion.short2),
        label = "ListItemBackgroundColor"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .background(backgroundColor)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        enabled = enabled,
                        role = Role.Button,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            )
            .semantics(mergeDescendants = true) { }
            .padding(horizontal = spacing.md, vertical = spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        // Leading Slot
        if (leadingContent != null) {
            Box(
                modifier = Modifier.defaultMinSize(minWidth = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                leadingContent()
            }
        }

        // Text Content Block
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            headline()
            if (supportingText != null) {
                supportingText()
            }
        }

        // Trailing Slot
        if (trailingContent != null) {
            Box(
                modifier = Modifier.defaultMinSize(minWidth = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                trailingContent()
            }
        }
    }
}
