package es.joshluq.canvaskit.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Standard Bottom Bar (With Badges & Labels Always Visible)",
            style = CanvasKitTheme.typography.labelLarge,
            color = CanvasKitTheme.colors.textSecondary
        )

        CanvasKitBottomBar {
            CanvasKitBottomBarItem(
                selected = selectedIndex1 == 0,
                onClick = { selectedIndex1 = 0 },
                icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "Inicio", tint = tint) },
                label = { tint -> Text("Inicio", style = CanvasKitTheme.typography.labelSmall, color = tint) }
            )
            CanvasKitBottomBarItem(
                selected = selectedIndex1 == 1,
                onClick = { selectedIndex1 = 1 },
                icon = { tint -> Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar", tint = tint) },
                label = { tint -> Text("Buscar", style = CanvasKitTheme.typography.labelSmall, color = tint) }
            )
            CanvasKitBottomBarItem(
                selected = selectedIndex1 == 2,
                onClick = { selectedIndex1 = 2 },
                icon = { tint ->
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Avisos",
                        tint = tint
                    )
                },
                label = { tint -> Text("Avisos", style = CanvasKitTheme.typography.labelSmall, color = tint) },
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
                selected = selectedIndex1 == 3,
                onClick = { selectedIndex1 = 3 },
                icon = { tint ->
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Ajustes",
                        tint = tint
                    )
                },
                label = { tint -> Text("Ajustes", style = CanvasKitTheme.typography.labelSmall, color = tint) }
            )
        }

        Text(
            text = "Bottom Bar (Hide Label When Unselected & Dot Status Badge)",
            style = CanvasKitTheme.typography.labelLarge,
            color = CanvasKitTheme.colors.textSecondary
        )

        CanvasKitBottomBar {
            CanvasKitBottomBarItem(
                selected = selectedIndex2 == 0,
                onClick = { selectedIndex2 = 0 },
                icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "Inicio", tint = tint) },
                label = { tint -> Text("Inicio", style = CanvasKitTheme.typography.labelSmall, color = tint) },
                alwaysShowLabel = false
            )
            CanvasKitBottomBarItem(
                selected = selectedIndex2 == 1,
                onClick = { selectedIndex2 = 1 },
                icon = { tint -> Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar", tint = tint) },
                label = { tint -> Text("Buscar", style = CanvasKitTheme.typography.labelSmall, color = tint) },
                alwaysShowLabel = false
            )
            CanvasKitBottomBarItem(
                selected = selectedIndex2 == 2,
                onClick = { selectedIndex2 = 2 },
                icon = { tint ->
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Avisos",
                        tint = tint
                    )
                },
                label = { tint -> Text("Avisos", style = CanvasKitTheme.typography.labelSmall, color = tint) },
                badge = { CanvasKitBadge() }, // Empty dot status badge
                alwaysShowLabel = false
            )
            CanvasKitBottomBarItem(
                selected = selectedIndex2 == 3,
                onClick = { selectedIndex2 = 3 },
                icon = { tint ->
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Ajustes",
                        tint = tint
                    )
                },
                label = { tint -> Text("Ajustes", style = CanvasKitTheme.typography.labelSmall, color = tint) },
                alwaysShowLabel = false
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitBottomBarLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            BottomBarPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitBottomBarDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            BottomBarPreviewContainer()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
fun CanvasKitBottomBarRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.padding(16.dp)) {
                BottomBarPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
fun CanvasKitBottomBarFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            BottomBarPreviewContainer()
        }
    }
}
