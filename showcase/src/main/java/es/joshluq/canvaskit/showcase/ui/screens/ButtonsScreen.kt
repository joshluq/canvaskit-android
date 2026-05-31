package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.layout.CanvasKitLoadingScaffold
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * ButtonsScreen showcases the "Artisanal Precision" action hierarchy.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ButtonsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val onButtonClick = {
        Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
    }

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    CanvasKitLoadingScaffold(
        isLoading = false,
        modifier = modifier.fillMaxSize(),
        topBar = {
            CanvasKitTopBar(
                title = {
                    Column {
                        Text(
                            text = "Action Hierarchy",
                            style = CanvasKitTheme.typography.headingLarge,
                            color = colors.textPrimary
                        )
                        Text(
                            text = "Refined buttons and tactile interactive states.",
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
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.backgroundSecondary)
                    .verticalScroll(rememberScrollState())
                    .padding(spacing.md),
                verticalArrangement = Arrangement.spacedBy(spacing.lg)
            ) {
                // Introduction Section
                Column(modifier = Modifier.padding(horizontal = spacing.xs)) {
                    Text(
                        text = "Tactile\nPrecision",
                        style = CanvasKitTheme.typography.displayMedium,
                        color = colors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(spacing.sm))
                    Text(
                        text = "Our buttons are crafted for clarity and responsiveness, using high-contrast weights for premium legibility.",
                        style = CanvasKitTheme.typography.bodyLarge,
                        color = colors.textSecondary
                    )
                }

                // Section: Primary Buttons Card
                SpecSectionCard(
                    title = "Primary Actions",
                    description = "High-contrast actions for main user flows."
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                        verticalArrangement = Arrangement.spacedBy(spacing.sm),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CanvasKitButton(onClick = onButtonClick) { contentColor ->
                            Text(
                                "Default",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = contentColor
                            )
                        }
                        CanvasKitButton(onClick = onButtonClick, enabled = false) {
                            Text(
                                "Disabled",
                                style = CanvasKitTheme.typography.labelLarge
                            )
                        }
                        CanvasKitButton(onClick = onButtonClick, loading = true) {
                            Text(
                                "Loading",
                                style = CanvasKitTheme.typography.labelLarge
                            )
                        }
                    }
                }

                // Section: Secondary & Ghost
                SpecSectionCard(
                    title = "Supporting Actions",
                    description = "Secondary and ghost variants for cleaner hierarchies."
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                            verticalArrangement = Arrangement.spacedBy(spacing.sm),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CanvasKitButton(
                                onClick = onButtonClick,
                                variant = CanvasKitButtonVariant.Secondary
                            ) {
                                Text(
                                    "Secondary",
                                    style = CanvasKitTheme.typography.labelLarge
                                )
                            }
                            CanvasKitButton(
                                onClick = onButtonClick,
                                variant = CanvasKitButtonVariant.Ghost
                            ) {
                                Text(
                                    "Ghost Action",
                                    style = CanvasKitTheme.typography.labelLarge
                                )
                            }
                        }
                    }
                }

                // Section: Icon Buttons
                SpecSectionCard(
                    title = "Tactile Icons",
                    description = "Precision icon actions with micro-scale animations."
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.md),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitIconButton(
                            onClick = onButtonClick,
                            contentDescription = "Add"
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }

                        CanvasKitIconButton(
                            onClick = onButtonClick,
                            shape = shapes.extraLarge,
                            backgroundColor = colors.backgroundSecondary,
                            contentColor = colors.brandAccent,
                            contentDescription = "Settings"
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null
                            )
                        }

                        CanvasKitIconButton(
                            onClick = onButtonClick,
                            loading = true,
                            contentDescription = "Settings"
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null
                            )
                        }
                    }

                // Section: Buttons with Icons
                SpecSectionCard(
                    title = "Hybrid Actions",
                    description = "Combining iconography with text for maximum clarity."
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CanvasKitButton(onClick = onButtonClick) { contentColor ->
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = contentColor,
                                modifier = Modifier.padding(end = spacing.xs)
                            )
                            Text(
                                "Create New",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = contentColor
                            )
                        }
                    }
                }
                }
            }
        }
    }
}
