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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.chips.CanvasKitChip
import es.joshluq.canvaskit.components.chips.CanvasKitChipVariant
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * ChipsScreen showcases the "Artisanal Precision" Chip component.
 */
@Composable
fun ChipsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    // Playground States
    var selectedFilter by remember { mutableStateOf<String?>(null) }

    var showDismissible by remember { mutableStateOf(true) }

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
                        text = "Chips",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Compact components for actions, filters, or selection.",
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
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            
            // ──────────────────────────────────────────────────────────
            // Section 1: Filter Chips
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Filter Chips",
                description = "Outlined variant for single-selection filters."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.xs)
                ) {
                    val englishFilters = listOf("All", "Recent", "Popular", "Favorites")
                    englishFilters.take(3).forEach { filter ->
                        CanvasKitChip(
                            onClick = { 
                                selectedFilter = if (selectedFilter == filter) null else filter 
                            },
                            variant = CanvasKitChipVariant.Outlined,
                            selected = selectedFilter == filter,
                            leadingIcon = if (selectedFilter == filter) {
                                { Icon(Icons.Default.Check, contentDescription = null, tint = colors.brandAccent) }
                            } else null,
                            label = {
                                Text(
                                    text = filter,
                                    style = typography.labelLarge,
                                    color = if (selectedFilter == filter) colors.brandAccent else colors.textPrimary
                                )
                            }
                        )
                    }
                }
            }

            // ──────────────────────────────────────────────────────────
            // Section 2: Action Chips
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Action Chips",
                description = "Ghost variant for quick actions."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.xs)
                ) {
                    CanvasKitChip(
                        onClick = {},
                        variant = CanvasKitChipVariant.Ghost,
                        label = {
                            Text(text = "Share", style = typography.labelLarge, color = colors.textSecondary)
                        }
                    )
                    CanvasKitChip(
                        onClick = {},
                        variant = CanvasKitChipVariant.Ghost,
                        label = {
                            Text(text = "Download", style = typography.labelLarge, color = colors.textSecondary)
                        }
                    )
                }
            }

            // ──────────────────────────────────────────────────────────
            // Section 3: Input / Dismissible Chips
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Input Chips",
                description = "Chips with a dismiss button (Primary)."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.xs)
                ) {
                    if (showDismissible) {
                        CanvasKitChip(
                            onClick = {},
                            variant = CanvasKitChipVariant.Primary,
                            label = {
                                Text(text = "Filter Applied", style = typography.labelLarge, color = colors.textPrimary)
                            },
                            trailingIcon = {
                                Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = colors.textPrimary)
                            }
                        )
                    } else {
                        CanvasKitChip(
                            onClick = { showDismissible = true },
                            variant = CanvasKitChipVariant.Outlined,
                            label = {
                                Text("Add filter", style = typography.labelLarge, color = colors.brandAccent)
                            }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(spacing.lg))
        }
    }
}
