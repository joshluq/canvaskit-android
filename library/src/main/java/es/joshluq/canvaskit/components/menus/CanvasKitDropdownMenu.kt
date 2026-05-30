package es.joshluq.canvaskit.components.menus

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitDropdownMenu is a highly-polished, expressive menu component.
 * It follows Material 3 Expressive guidelines with generous spacing and rounded corners.
 *
 * @param expanded Whether the menu is currently visible.
 * @param onDismissRequest Callback to fire when the menu should be closed.
 * @param modifier Root layout modifier.
 * @param offset DpOffset to be applied to the menu's position.
 * @param content Composable slot for the menu items.
 */
@Composable
fun CanvasKitDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        offset = offset,
        shape = shapes.large, // Expressive: 16dp
        containerColor = colors.backgroundPrimary,
        border = BorderStroke(0.5.dp, colors.borderSubtle),
        shadowElevation = 8.dp,
        content = content
    )
}

/**
 * CanvasKitDropdownMenuItem is an individual item within a [CanvasKitDropdownMenu].
 *
 * @param text Principal text for the menu item.
 * @param onClick Callback to fire when the item is clicked.
 * @param modifier Root layout modifier.
 * @param leadingIcon Optional icon to be displayed at the start of the item.
 * @param trailingContent Optional slot for secondary text or status icons at the end of the item.
 * @param enabled Whether the item is interactive.
 */
@Composable
fun CanvasKitDropdownMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    enabled: Boolean = true
) {
    val colors = CanvasKitTheme.colors
    val typography = CanvasKitTheme.typography
    val spacing = CanvasKitTheme.spacing

    DropdownMenuItem(
        text = {
            Text(
                text = text,
                style = typography.bodyLarge,
                color = if (enabled) colors.textPrimary else colors.textSecondary.copy(alpha = 0.38f)
            )
        },
        onClick = onClick,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingContent,
        enabled = enabled,
        colors = MenuDefaults.itemColors(
            textColor = colors.textPrimary,
            leadingIconColor = colors.textPrimary,
            trailingIconColor = colors.textSecondary,
            disabledTextColor = colors.textSecondary.copy(alpha = 0.38f),
            disabledLeadingIconColor = colors.textSecondary.copy(alpha = 0.38f),
            disabledTrailingIconColor = colors.textSecondary.copy(alpha = 0.38f),
        ),
        contentPadding = PaddingValues(
            horizontal = spacing.md,
            vertical = spacing.sm
        )
    )
}
