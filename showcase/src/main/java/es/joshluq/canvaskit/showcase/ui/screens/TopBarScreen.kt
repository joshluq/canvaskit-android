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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * TopBarScreen showcases the "Artisanal Precision" header navigation.
 */
@Composable
fun TopBarScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Header Navigation",
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Refined app bars with exclusive typography.",
                        style = CanvasKitTheme.typography.labelSmall,
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
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
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
                    text = "Refined\nHeaders",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Top bars that define the start of every premium experience. High-contrast typography meets functional precision.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Standard Bar
            SpecSectionCard(
                title = "Standard Header",
                description = "Clean, focused navigation for most application screens."
            ) {
                CanvasKitTopBar(
                    title = {
                        Text(
                            text = "Dashboard",
                            style = CanvasKitTheme.typography.headingMedium,
                            color = colors.textPrimary
                        )
                    },
                    navigationIcon = {
                        CanvasKitIconButton(
                            onClick = { },
                            contentDescription = "Back"
                        ) { contentColor ->
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null,
                                tint = contentColor
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Section: Action Bar
            SpecSectionCard(
                title = "Action-Rich Header",
                description = "Navigation headers with support for multiple contextual actions."
            ) {
                CanvasKitTopBar(
                    title = {
                        Column {
                            Text(
                                text = "Project Settings",
                                style = CanvasKitTheme.typography.headingMedium,
                                color = colors.textPrimary
                            )
                            Text(
                                text = "Last updated 2m ago",
                                style = CanvasKitTheme.typography.labelSmall,
                                color = colors.textSecondary
                            )
                        }
                    },
                    actions = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CanvasKitIconButton(
                                onClick = { },
                                contentDescription = "Search"
                            ) { contentColor ->
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = contentColor
                                )
                            }
                            CanvasKitIconButton(
                                onClick = { },
                                contentDescription = "Notifications"
                            ) { contentColor ->
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = null,
                                    tint = contentColor
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
