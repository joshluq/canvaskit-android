package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.navigation.CanvasKitBadge
import es.joshluq.canvaskit.components.navigation.CanvasKitBottomBar
import es.joshluq.canvaskit.components.navigation.CanvasKitBottomBarItem
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * BottomBarScreen showcases the new Expressive and Floating navigation systems.
 */
@Composable
fun BottomBarScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    var selectedIndex1 by remember { mutableIntStateOf(0) }
    var selectedIndex2 by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Expressive Navigation",
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Refined Docked and Floating navigation bars.",
                        style = CanvasKitTheme.typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(
                    onClick = onBack,
                    contentDescription = "Back"
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                        tint = colors.brandPrimary
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {
            
            // Introduction Section
            Column(modifier = Modifier.padding(horizontal = spacing.xs)) {
                Text(
                    text = "Navigation\nPrecision",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Combining circular focal points with spring-based motion for a high-end ergonomic experience.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Docked Variant
            SpecSectionCard(
                title = "Docked Precision",
                description = "Standard navigation anchored to the screen bottom."
            ) {
                CanvasKitBottomBar(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SampleBottomBarItems(selectedIndex1) { selectedIndex1 = it }
                }
            }

            // Section: Floating Variant
            SpecSectionCard(
                title = "Floating Dock",
                description = "Detached navigation island for immersive interfaces."
            ) {
                CanvasKitBottomBar(
                    isFloating = true,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SampleBottomBarItems(selectedIndex2) { selectedIndex2 = it }
                }
            }
        }
    }
}

@Composable
private fun RowScope.SampleBottomBarItems(
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    CanvasKitBottomBarItem(
        selected = selectedIndex == 0,
        onClick = { onSelect(0) },
        icon = { tint -> Icon(Icons.Default.Home, contentDescription = null, tint = tint) },
        label = { tint -> Text("Home", color = tint, style = CanvasKitTheme.typography.labelSmall) }
    )
    CanvasKitBottomBarItem(
        selected = selectedIndex == 1,
        onClick = { onSelect(1) },
        icon = { tint -> Icon(Icons.Default.Search, contentDescription = null, tint = tint) },
        label = { tint -> Text("Search", color = tint, style = CanvasKitTheme.typography.labelSmall) }
    )
    CanvasKitBottomBarItem(
        selected = selectedIndex == 2,
        onClick = { onSelect(2) },
        icon = { tint -> Icon(Icons.Default.Notifications, contentDescription = null, tint = tint) },
        label = { tint -> Text("Alerts", color = tint, style = CanvasKitTheme.typography.labelSmall) },
        badge = {
            CanvasKitBadge {
                Text(
                    text = "5",
                    style = CanvasKitTheme.typography.labelSmall,
                    color = CanvasKitTheme.colors.backgroundPrimary
                )
            }
        }
    )
    CanvasKitBottomBarItem(
        selected = selectedIndex == 3,
        onClick = { onSelect(3) },
        icon = { tint -> Icon(Icons.Default.Settings, contentDescription = null, tint = tint) },
        label = { tint -> Text("Settings", color = tint, style = CanvasKitTheme.typography.labelSmall) }
    )
}
