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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.inputs.CanvasKitSwitch
import es.joshluq.canvaskit.components.layout.CanvasKitAccordion
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * AccordionsScreen showcases the expressive collapsible sections.
 */
@Composable
fun AccordionsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    var expandedSection by remember { mutableStateOf<Int?>(0) }
    var notificationState by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Collapsible Groups",
                        style = typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Organize high-density content with artisanal motion.",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(onClick = onBack, contentDescription = "Back") { contentColor ->
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = contentColor)
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
                    text = "Tactile\nArchitecture",
                    style = typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Accordions allow users to navigate complex hierarchies without losing focus on the current task.",
                    style = typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Interactive Accordions
            SpecSectionCard(
                title = "Configuration Groups",
                description = "Combining ListItems and Accordions for complex flows."
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    // Group 1: Notifications
                    CanvasKitAccordion(
                        expanded = expandedSection == 0,
                        onExpandedChange = { expandedSection = if (it) 0 else null },
                        headline = { Text("System Notifications", style = typography.bodyLarge) },
                        supportingText = { Text("Push, Email and SMS settings", style = typography.bodyMedium) },
                        leadingContent = { Icon(Icons.Default.Notifications, contentDescription = null, tint = colors.brandPrimary) }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Enable Global Alerts",
                                style = typography.bodyMedium,
                                color = colors.textPrimary
                            )
                            CanvasKitSwitch(
                                checked = notificationState,
                                onCheckedChange = { notificationState = it }
                            )
                        }
                    }

                    // Group 2: Visual Style
                    CanvasKitAccordion(
                        expanded = expandedSection == 1,
                        onExpandedChange = { expandedSection = if (it) 1 else null },
                        headline = { Text("Interface Themes", style = typography.bodyLarge) },
                        leadingContent = { Icon(Icons.Default.Palette, contentDescription = null, tint = colors.brandPrimary) },
                        trailingContent = {
                            Text(
                                text = "Pro",
                                style = typography.labelSmall,
                                color = colors.brandAccent
                            )
                        }
                    ) {
                        Text(
                            text = "Choose between Atelier Light, Obsidian Dark, and High Contrast accessibility themes.",
                            style = typography.bodyMedium,
                            color = colors.textSecondary
                        )
                    }

                    // Group 3: Privacy
                    CanvasKitAccordion(
                        expanded = expandedSection == 2,
                        onExpandedChange = { expandedSection = if (it) 2 else null },
                        headline = { Text("Advanced Security", style = typography.bodyLarge) },
                        leadingContent = { Icon(Icons.Default.Build, contentDescription = null, tint = colors.brandPrimary) }
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                            Text(
                                text = "Manage your biometric keys and cryptographic session tokens.",
                                style = typography.bodyMedium,
                                color = colors.textSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}
