package es.joshluq.canvaskit.components.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.inputs.CanvasKitSwitch
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun ListItemPreviewContainer() {
    val colors = CanvasKitTheme.colors
    val typography = CanvasKitTheme.typography

    var switchState by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Settings Example",
            style = typography.headingMedium,
            color = colors.textPrimary,
            modifier = Modifier.padding(16.dp)
        )

        CanvasKitListItem(
            headline = {
                Text(text = "Profile", style = typography.bodyLarge, color = colors.textPrimary)
            },
            supportingText = {
                Text(
                    text = "Manage your account and preferences",
                    style = typography.bodyMedium,
                    color = colors.textSecondary
                )
            },
            leadingContent = {
                Icon(Icons.Default.Person, contentDescription = null, tint = colors.brandPrimary)
            },
            onClick = { }
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 0.5.dp,
            color = colors.borderSubtle
        )

        CanvasKitListItem(
            headline = {
                Text(text = "Notifications", style = typography.bodyLarge, color = colors.textPrimary)
            },
            leadingContent = {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = colors.brandPrimary)
            },
            trailingContent = {
                CanvasKitSwitch(checked = switchState, onCheckedChange = { switchState = it })
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 0.5.dp,
            color = colors.borderSubtle
        )

        CanvasKitListItem(
            headline = {
                Text(text = "Dark Mode", style = typography.bodyLarge, color = colors.textPrimary)
            },
            leadingContent = {
                Icon(Icons.Default.Settings, contentDescription = null, tint = colors.brandPrimary)
            },
            selected = true,
            onClick = { }
        )

        CanvasKitListItem(
            headline = {
                Text(text = "Disabled Item", style = typography.bodyLarge, color = colors.textPrimary)
            },
            enabled = false,
            onClick = { }
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
internal fun CanvasKitListItemLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        ListItemPreviewContainer()
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasKitListItemDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        ListItemPreviewContainer()
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
internal fun CanvasKitListItemRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            ListItemPreviewContainer()
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
internal fun CanvasKitListItemFontScalePreview() {
    CanvasKitTheme {
        ListItemPreviewContainer()
    }
}
