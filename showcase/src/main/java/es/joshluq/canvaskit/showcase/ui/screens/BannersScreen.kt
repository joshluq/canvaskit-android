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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.cards.CanvasKitCardVariant
import es.joshluq.canvaskit.components.feedback.CanvasKitAlertVariant
import es.joshluq.canvaskit.components.feedback.CanvasKitBanner
import es.joshluq.canvaskit.components.feedback.CanvasKitInlineAlert
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * BannersScreen showcases the "Artisanal Precision" feedback components:
 * [CanvasKitBanner] (full-width system notification) and [CanvasKitInlineAlert]
 * (compact contextual alert for embedding within content blocks).
 */
@Composable
fun BannersScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    // Playground state: which variant is currently shown
    var selectedVariant by remember { mutableStateOf(CanvasKitAlertVariant.Info) }
    var bannerVisible by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        // Top Bar
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Banners & Alerts",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Feedback components for system messages and contextual alerts.",
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
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {

            // ──────────────────────────────────────────────────────────
            // Section 1: Interactive Banner Playground
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Interactive Banner Playground",
                description = "Select a semantic variant to see the Banner in real-time."
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.sm),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Live Banner Preview
                    CanvasKitBanner(
                        variant = selectedVariant,
                        visible = bannerVisible,
                        title = {
                            Text(
                                text = selectedVariant.label(),
                                style = typography.labelLarge,
                                color = selectedVariant.labelColor(colors)
                            )
                        },
                        message = {
                            Text(
                                text = selectedVariant.sampleMessage(),
                                style = typography.bodyMedium,
                                color = colors.textPrimary
                            )
                        },
                        action = {
                            CanvasKitButton(
                                onClick = {},
                                variant = CanvasKitButtonVariant.Ghost
                            ) {
                                Text(
                                    text = "See details",
                                    style = typography.labelLarge,
                                    color = selectedVariant.labelColor(colors)
                                )
                            }
                        },
                        onDismiss = { bannerVisible = false }
                    )

                    // Restore banner button (when dismissed)
                    if (!bannerVisible) {
                        CanvasKitButton(
                            onClick = { bannerVisible = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Restore Banner",
                                style = typography.labelLarge,
                                color = colors.backgroundPrimary
                            )
                        }
                    }

                    // Variant Selector Grid
                    Text(
                        text = "Active variant",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spacing.xs)
                    ) {
                        CanvasKitAlertVariant.entries.forEach { variant ->
                            CanvasKitCard(
                                variant = CanvasKitCardVariant.Outlined,
                                selected = selectedVariant == variant,
                                onClick = {
                                    selectedVariant = variant
                                    bannerVisible = true
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = variant.name,
                                    style = typography.labelSmall,
                                    color = if (selectedVariant == variant) colors.brandAccent else colors.textSecondary
                                )
                            }
                        }
                    }
                }
            }

            // ──────────────────────────────────────────────────────────
            // Section 2: All Banner Variants Gallery
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Banner Variants",
                description = "The four semantic states of the notification system."
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.sm),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CanvasKitAlertVariant.entries.forEach { variant ->
                        CanvasKitBanner(
                            variant = variant,
                            visible = true,
                            title = {
                                Text(
                                    text = variant.label(),
                                    style = typography.labelLarge,
                                    color = variant.labelColor(colors)
                                )
                            },
                            message = {
                                Text(
                                    text = variant.sampleMessage(),
                                    style = typography.bodyMedium,
                                    color = colors.textPrimary
                                )
                            }
                        )
                    }
                }
            }

            // ──────────────────────────────────────────────────────────
            // Section 3: Inline Alerts Gallery
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Inline Alert Variants",
                description = "Compact alerts for embedding within forms and containers."
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.sm),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CanvasKitAlertVariant.entries.forEach { variant ->
                        CanvasKitInlineAlert(
                            variant = variant,
                            title = {
                                Text(
                                    text = variant.label(),
                                    style = typography.labelLarge,
                                    color = variant.labelColor(colors)
                                )
                            },
                            message = {
                                Text(
                                    text = variant.sampleMessage(),
                                    style = typography.bodyMedium,
                                    color = colors.textPrimary
                                )
                            }
                        )
                    }
                }
            }

            // ──────────────────────────────────────────────────────────
            // Section 4: Real-world Example (Card with embedded alerts)
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Contextual Usage",
                description = "Example of integration within a form card."
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.sm),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CanvasKitCard(variant = CanvasKitCardVariant.Outlined) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(spacing.sm)
                        ) {
                            Text(
                                text = "Payment Form",
                                style = typography.headingMedium,
                                color = colors.textPrimary
                            )
                            Text(
                                text = "Enter your card details to continue.",
                                style = typography.bodyMedium,
                                color = colors.textSecondary
                            )
                            Spacer(modifier = Modifier.height(spacing.xxs))
                            // Inline error inside a form card
                            CanvasKitInlineAlert(
                                variant = CanvasKitAlertVariant.Error,
                                title = {
                                    Text(
                                        text = "Card declined",
                                        style = typography.labelLarge,
                                        color = colors.error
                                    )
                                },
                                message = {
                                    Text(
                                        text = "Insufficient funds. Check your balance or use another payment method.",
                                        style = typography.bodyMedium,
                                        color = colors.textPrimary
                                    )
                                },
                                action = {
                                    CanvasKitButton(
                                        onClick = {},
                                        variant = CanvasKitButtonVariant.Ghost
                                    ) {
                                        Text(
                                            text = "Retry",
                                            style = typography.labelLarge,
                                            color = colors.error
                                        )
                                    }
                                }
                            )
                            // Info hint inside the same form card
                            CanvasKitInlineAlert(
                                variant = CanvasKitAlertVariant.Info,
                                message = {
                                    Text(
                                        text = "Your data is encrypted with TLS 1.3.",
                                        style = typography.bodyMedium,
                                        color = colors.textPrimary
                                    )
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing.lg))
        }
    }
}

// ---------------------------------------------------------------------------
// Screen-local helpers
// ---------------------------------------------------------------------------

private fun CanvasKitAlertVariant.label(): String = when (this) {
    CanvasKitAlertVariant.Info    -> "Information"
    CanvasKitAlertVariant.Success -> "Success"
    CanvasKitAlertVariant.Warning -> "Warning"
    CanvasKitAlertVariant.Error   -> "Error"
}

private fun CanvasKitAlertVariant.sampleMessage(): String = when (this) {
    CanvasKitAlertVariant.Info    -> "New system version available. Update to get the latest improvements."
    CanvasKitAlertVariant.Success -> "Changes were saved successfully and are now active."
    CanvasKitAlertVariant.Warning -> "Some permissions are disabled. Please review the app settings."
    CanvasKitAlertVariant.Error   -> "The operation could not be completed. Check your connection and try again."
}

@Composable
private fun CanvasKitAlertVariant.labelColor(colors: es.joshluq.canvaskit.core.tokens.CanvasKitColors): androidx.compose.ui.graphics.Color =
    when (this) {
        CanvasKitAlertVariant.Info    -> colors.brandAccent
        CanvasKitAlertVariant.Success -> colors.success
        CanvasKitAlertVariant.Warning -> colors.warning
        CanvasKitAlertVariant.Error   -> colors.error
    }
