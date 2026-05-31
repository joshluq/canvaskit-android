package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.feedback.CanvasKitDialog
import es.joshluq.canvaskit.components.feedback.CanvasKitDialogContent
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * DialogsScreen showcases the "Artisanal Precision" modal experiences.
 */
@Composable
fun DialogsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showExclusiveDialog by remember { mutableStateOf(false) }

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
                        text = "Modal Experiences",
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Premium transitions and exclusive container hierarchies.",
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
                        imageVector = Icons.Default.ArrowBack,
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
                    text = "Refined\nOverlays",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Dialogs that command attention without disrupting the visual harmony. Precision in every shadow and corner.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Alert Dialog
            SpecSectionCard(
                title = "Alert Confirmation",
                description = "Focused modal for critical system decisions."
            ) {
                CanvasKitButton(onClick = { showConfirmDialog = true }) {
                    Text(
                        "Trigger Alert",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.backgroundPrimary
                    )
                }
            }

            // Section: Exclusive Content
            SpecSectionCard(
                title = "Exclusive Content",
                description = "The pinnacle of our layered artisanal modularity."
            ) {
                CanvasKitButton(
                    onClick = { showExclusiveDialog = true },
                    variant = CanvasKitButtonVariant.Secondary
                ) {
                    Text(
                        "Trigger Exclusive Overlay",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.brandPrimary
                    )
                }
            }
        }

        // Render Dialogs
        if (showConfirmDialog) {
            CanvasKitDialog(
                onDismissRequest = { showConfirmDialog = false }
            ) {
                CanvasKitDialogContent(
                    title = {
                        Text(
                            text = "Confirm Action",
                            style = CanvasKitTheme.typography.headingLarge,
                            color = colors.textPrimary
                        )
                    },
                    content = {
                        Text(
                            text = "This action will apply global precision tokens to all components. Are you ready to proceed with the refinement?",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textSecondary
                        )
                    },
                    buttons = {
                        CanvasKitButton(
                            onClick = { showConfirmDialog = false },
                            variant = CanvasKitButtonVariant.Ghost
                        ) {
                            Text(
                                "Cancel",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = colors.brandPrimary
                            )
                        }
                        CanvasKitButton(
                            onClick = {
                                showConfirmDialog = false
                                Toast.makeText(context, "Precision applied.", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Text(
                                "Proceed",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = colors.backgroundPrimary
                            )
                        }
                    }
                )
            }
        }

        if (showExclusiveDialog) {
            CanvasKitDialog(
                onDismissRequest = { showExclusiveDialog = false }
            ) {
                CanvasKitDialogContent(
                    title = {
                        Text(
                            text = "Artisanal Precision",
                            style = CanvasKitTheme.typography.headingLarge,
                            color = colors.textPrimary
                        )
                    },
                    content = {
                        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                            Text(
                                text = "Experience the full depth of the Atelier Design System. Modular, exclusive, and precise.",
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = colors.textSecondary
                            )
                            // Nested Card example inside dialog
                            SpecSectionCard(
                                title = "Layered Architecture",
                                description = "Card-on-card precision even in overlays."
                            ) {
                                Text(
                                    text = "This is a nested module.",
                                    style = CanvasKitTheme.typography.labelSmall,
                                    color = colors.brandPrimary
                                )
                            }
                        }
                    },
                    buttons = {
                        CanvasKitButton(
                            onClick = { showExclusiveDialog = false }
                        ) {
                            Text(
                                "Excellent",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = colors.backgroundPrimary
                            )
                        }
                    }
                )
            }
        }
    }
}
