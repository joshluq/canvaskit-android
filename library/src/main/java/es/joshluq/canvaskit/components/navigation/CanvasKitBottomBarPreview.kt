package es.joshluq.canvaskit.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun BottomBarPreviewContainer() {
    val spacing = CanvasKitTheme.spacing
    var selectedIndex1 by remember { mutableIntStateOf(0) }
    var selectedIndex2 by remember { mutableIntStateOf(0) }

    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Expressive Bottom Bar (Docked)",
            style = CanvasKitTheme.typography.labelLarge,
            color = CanvasKitTheme.colors.textSecondary
        )

        CanvasKitBottomBar {
            BottomBarItems(selectedIndex1) { selectedIndex1 = it }
        }

        Text(
            text = "Expressive Bottom Bar (Floating)",
            style = CanvasKitTheme.typography.labelLarge,
            color = CanvasKitTheme.colors.textSecondary
        )

        CanvasKitBottomBar(isFloating = true) {
            BottomBarItems(selectedIndex2) { selectedIndex2 = it }
        }
    }
}

@Composable
private fun RowScope.BottomBarItems(selectedIndex: Int, onSelect: (Int) -> Unit) {
    CanvasKitBottomBarItem(
        selected = selectedIndex == 0,
        onClick = { onSelect(0) },
        icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = tint) },
        label = { tint -> Text("Home", style = CanvasKitTheme.typography.labelSmall, color = tint) }
    )
    CanvasKitBottomBarItem(
        selected = selectedIndex == 1,
        onClick = { onSelect(1) },
        icon = { tint -> Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = tint) },
        label = { tint -> Text("Search", style = CanvasKitTheme.typography.labelSmall, color = tint) }
    )
    CanvasKitBottomBarItem(
        selected = selectedIndex == 2,
        onClick = { onSelect(2) },
        icon = { tint ->
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Alerts",
                tint = tint
            )
        },
        label = { tint -> Text("Alerts", style = CanvasKitTheme.typography.labelSmall, color = tint) },
        badge = {
            CanvasKitBadge {
                Text(
                    text = "3",
                    style = CanvasKitTheme.typography.labelSmall,
                    color = CanvasKitTheme.colors.backgroundPrimary
                )
            }
        }
    )
    CanvasKitBottomBarItem(
        selected = selectedIndex == 3,
        onClick = { onSelect(3) },
        icon = { tint ->
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Ajustes",
                tint = tint
            )
        },
        label = { tint -> Text("Settings", style = CanvasKitTheme.typography.labelSmall, color = tint) }
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
internal fun CanvasKitBottomBarLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            BottomBarPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasKitBottomBarDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            BottomBarPreviewContainer()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
internal fun CanvasKitBottomBarRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.padding(vertical = 16.dp)) {
                BottomBarPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
internal fun CanvasKitBottomBarFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            BottomBarPreviewContainer()
        }
    }
}
