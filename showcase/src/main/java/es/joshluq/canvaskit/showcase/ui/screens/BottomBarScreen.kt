package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.navigation.CanvasKitBottomBar
import es.joshluq.canvaskit.components.navigation.CanvasKitBottomBarItem
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * BottomBarScreen showcases the "Artisanal Precision" app navigation.
 */
@Composable
fun BottomBarScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "App Navigation",
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Accessible and elegant bottom navigation bars.",
                        style = CanvasKitTheme.typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Atrás",
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
                    text = "Refined\nNavigation",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Bottom bars that prioritize ergonomics and visual purity. Built with high-contrast active states for maximum clarity.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Bottom Bar Variations
            SpecSectionCard(
                title = "Standard Navigation",
                description = "Primary navigation with clear icons and labels."
            ) {
                CanvasKitBottomBar(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CanvasKitBottomBarItem(
                        selected = selectedIndex == 0,
                        onClick = { selectedIndex = 0 },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("Home") }
                    )
                    CanvasKitBottomBarItem(
                        selected = selectedIndex == 1,
                        onClick = { selectedIndex = 1 },
                        icon = { Icon(Icons.Default.Search, contentDescription = null) },
                        label = { Text("Search") }
                    )
                    CanvasKitBottomBarItem(
                        selected = selectedIndex == 2,
                        onClick = { selectedIndex = 2 },
                        icon = { Icon(Icons.Default.Person, contentDescription = null) },
                        label = { Text("Account") }
                    )
                    CanvasKitBottomBarItem(
                        selected = selectedIndex == 3,
                        onClick = { selectedIndex = 3 },
                        icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                        label = { Text("Settings") }
                    )
                }
            }
        }
    }
}
