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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.cards.CanvasKitCardVariant
import es.joshluq.canvaskit.components.inputs.CanvasKitCheckbox
import es.joshluq.canvaskit.components.inputs.CanvasKitRadioButton
import es.joshluq.canvaskit.components.inputs.CanvasKitSwitch
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * TogglesScreen showcases the "Artisanal Precision" tactile controls.
 */
@Composable
fun TogglesScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    // Global Playground States
    var globalEnabled by remember { mutableStateOf(true) }

    // Component States
    var switch1Checked by remember { mutableStateOf(true) }
    var checkbox1Checked by remember { mutableStateOf(true) }
    var selectedPaymentIndex by remember { mutableIntStateOf(1) }
    val paymentOptions = listOf("Standard", "Premium Plus", "Enterprise")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Tactile Controls",
                        style = typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Precision switches and interactive binary inputs.",
                        style = typography.labelSmall,
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
                    text = "Binary\nRefinement",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Controls designed for absolute clarity. Each interaction is a statement of artisanal quality.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Global settings
            SpecSectionCard(
                title = "System States",
                description = "Toggle the global interaction state of all components."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Global Interactive State",
                        style = typography.bodyMedium,
                        color = colors.textPrimary
                    )
                    CanvasKitSwitch(
                        checked = globalEnabled,
                        onCheckedChange = { globalEnabled = it }
                    )
                }
            }

            // Switches
            SpecSectionCard(
                title = "Switches",
                description = "Tactile toggles for immediate state changes."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Notification Service",
                            style = typography.headingMedium,
                            color = colors.textPrimary
                        )
                        Text(
                            text = if (switch1Checked) "Status: Active" else "Status: Inactive",
                            style = typography.labelSmall,
                            color = if (switch1Checked) colors.brandPrimary else colors.textSecondary
                        )
                    }
                    CanvasKitSwitch(
                        checked = switch1Checked,
                        onCheckedChange = { switch1Checked = it },
                        enabled = globalEnabled
                    )
                }
            }

            // Checkboxes
            SpecSectionCard(
                title = "Checkboxes",
                description = "Multi-selection controls for refined workflows."
            ) {
                InteractiveRowWrapper(
                    checked = checkbox1Checked,
                    onClick = { checkbox1Checked = !checkbox1Checked },
                    enabled = globalEnabled
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                    ) {
                        CanvasKitCheckbox(
                            checked = checkbox1Checked,
                            onCheckedChange = { checkbox1Checked = it },
                            enabled = globalEnabled
                        )
                        Text(
                            text = "Enable K2 Compiler Optimizations",
                            style = typography.bodyMedium,
                            color = colors.textPrimary
                        )
                    }
                }
            }

            // Radio Buttons
            SpecSectionCard(
                title = "Radio Groups",
                description = "Exclusive selection for hierarchical choices."
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    paymentOptions.forEachIndexed { index, option ->
                        val isSelected = selectedPaymentIndex == index
                        InteractiveRowWrapper(
                            checked = isSelected,
                            onClick = { selectedPaymentIndex = index },
                            enabled = globalEnabled
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                            ) {
                                CanvasKitRadioButton(
                                    selected = isSelected,
                                    onClick = { selectedPaymentIndex = index },
                                    enabled = globalEnabled
                                )
                                Text(
                                    text = option,
                                    style = typography.bodyMedium,
                                    color = colors.textPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InteractiveRowWrapper(
    checked: Boolean,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CanvasKitCard(
        variant = CanvasKitCardVariant.Outlined,
        selected = checked && enabled,
        onClick = if (enabled) onClick else null,
        modifier = modifier.fillMaxWidth(),
        shape = CanvasKitTheme.shapes.extraLarge
    ) {
        content()
    }
}
