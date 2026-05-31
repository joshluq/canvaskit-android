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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.cards.CanvasKitCardVariant
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CardsScreen showcases the "Artisanal Precision" modular structure.
 */
@Composable
fun CardsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    var selectedPlanIndex by remember { mutableIntStateOf(1) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Modular Purity",
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Exclusive container structures and layered hierarchies.",
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

            // Introduction Section (Artisanal Precision)
            Column(modifier = Modifier.padding(horizontal = spacing.xs)) {
                Text(
                    text = "Modular\nExclusivity",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Our 'card-on-card' structure creates a visual signature that is both orderly and premium.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section 1: Visual Variants (The "Container" card)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shapes.container)
                    .background(colors.backgroundPrimary)
                    .padding(spacing.md),
                verticalArrangement = Arrangement.spacedBy(spacing.md)
            ) {
                Text(
                    text = "Visual Hierarchy",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = colors.textPrimary
                )
                
                // Outlined Card (Nested)
                CanvasKitCard(
                    variant = CanvasKitCardVariant.Flat,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Primary Layer",
                            style = CanvasKitTheme.typography.headingMedium,
                            color = colors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(spacing.xxs))
                        Text(
                            text = "Clean, light-weight surfaces that prioritize content over containment.",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textSecondary
                        )
                    }
                }

                // Elevated Card (Nested)
                CanvasKitCard(
                    variant = CanvasKitCardVariant.Elevated,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Refined Elevation",
                            style = CanvasKitTheme.typography.headingMedium,
                            color = colors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(spacing.xxs))
                        Text(
                            text = "Subtle shadows that suggest depth without adding visual noise.",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textSecondary
                        )
                    }
                }
            }

            // Section 2: Interactive Selection
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shapes.container)
                    .background(colors.backgroundPrimary)
                    .padding(spacing.md),
                verticalArrangement = Arrangement.spacedBy(spacing.md)
            ) {
                Text(
                    text = "Selection Precision",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = colors.textPrimary
                )

                val plans = listOf(
                    Triple("Starter Kit", "Free", "Essential modular components."),
                    Triple("Professional", "$24/mo", "Full artisanal precision library."),
                    Triple("Enterprise", "Custom", "Tailored tokens and components.")
                )

                plans.forEachIndexed { index, (title, price, desc) ->
                    CanvasKitCard(
                        variant = CanvasKitCardVariant.Outlined,
                        selected = selectedPlanIndex == index,
                        onClick = { selectedPlanIndex = index },
                        modifier = Modifier.fillMaxWidth(),
                        shape = shapes.extraLarge
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = title,
                                    style = CanvasKitTheme.typography.headingMedium,
                                    color = colors.textPrimary
                                )
                                Text(
                                    text = desc,
                                    style = CanvasKitTheme.typography.bodyMedium,
                                    color = colors.textSecondary
                                )
                            }
                            Spacer(modifier = Modifier.width(spacing.md))
                            Text(
                                text = price,
                                style = CanvasKitTheme.typography.labelLarge,
                                color = if (selectedPlanIndex == index) colors.brandPrimary else colors.textPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}
