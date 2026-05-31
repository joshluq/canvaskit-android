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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.inputs.CanvasKitSwitch
import es.joshluq.canvaskit.components.lists.CanvasKitListItem
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * ListsScreen showcases the "Atelier Precision" list items.
 */
@Composable
fun ListsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val shapes = CanvasKitTheme.shapes
    val typography = CanvasKitTheme.typography

    var notificationsEnabled by remember { mutableStateOf(true) }
    var privacyMode by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "List Modules",
                        style = typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "High-density molecular rows for complex layouts.",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(
                    onClick = onBack,
                    contentDescription = "Back"
                ) { contentColor ->
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = contentColor
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
                    text = "Density &\nPrecision",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Molecular rows designed to handle complex information while maintaining the ecosystem's visual purity.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Settings Simulation
            SpecSectionCard(
                title = "Information Architecture",
                description = "Common use cases for settings and profiles."
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shapes.container)
                        .background(colors.backgroundPrimary)
                ) {
                    CanvasKitListItem(
                        headline = { Text("Profile Information", style = typography.bodyLarge, color = colors.textPrimary) },
                        supportingText = { Text("joshluq@atelier.design", style = typography.bodyMedium, color = colors.textSecondary) },
                        leadingContent = { Icon(Icons.Default.Person, contentDescription = null, tint = colors.brandPrimary) },
                        trailingContent = { Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = colors.textSecondary.copy(alpha = 0.5f)) },
                        onClick = { }
                    )
                    
                    HorizontalDivider(modifier = Modifier.padding(horizontal = spacing.md), thickness = 0.5.dp, color = colors.borderSubtle)
                    
                    CanvasKitListItem(
                        headline = { Text("Push Notifications", style = typography.bodyLarge, color = colors.textPrimary) },
                        leadingContent = { Icon(Icons.Default.Notifications, contentDescription = null, tint = colors.brandPrimary) },
                        trailingContent = {
                            CanvasKitSwitch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(horizontal = spacing.md), thickness = 0.5.dp, color = colors.borderSubtle)

                    CanvasKitListItem(
                        headline = { Text("Privacy Mode", style = typography.bodyLarge, color = colors.textPrimary) },
                        leadingContent = { Icon(Icons.Default.Lock, contentDescription = null, tint = colors.brandPrimary) },
                        trailingContent = {
                            CanvasKitSwitch(checked = privacyMode, onCheckedChange = { privacyMode = it })
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(horizontal = spacing.md), thickness = 0.5.dp, color = colors.borderSubtle)

                    CanvasKitListItem(
                        headline = { Text("Theme Customization", style = typography.bodyLarge, color = colors.textPrimary) },
                        leadingContent = { Icon(Icons.Default.Palette, contentDescription = null, tint = colors.brandPrimary) },
                        trailingContent = { Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = colors.textSecondary.copy(alpha = 0.5f)) },
                        onClick = { }
                    )
                }
            }
        }
    }
}
