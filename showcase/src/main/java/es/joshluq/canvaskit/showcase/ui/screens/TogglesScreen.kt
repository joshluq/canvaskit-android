package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.cards.CanvasKitCardVariant
import es.joshluq.canvaskit.components.inputs.CanvasKitCheckbox
import es.joshluq.canvaskit.components.inputs.CanvasKitRadioButton
import es.joshluq.canvaskit.components.inputs.CanvasKitSwitch
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * TogglesScreen showcases Switches, Checkboxes, and RadioButtons.
 * It provides interactive list checklists, payment option radio groups, and
 * a control panel to toggle the active/disabled states in compliance with design system tokens.
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
    var switch2Checked by remember { mutableStateOf(false) }

    var checkbox1Checked by remember { mutableStateOf(true) }
    var checkbox2Checked by remember { mutableStateOf(true) }
    var checkbox3Checked by remember { mutableStateOf(false) }

    var selectedPaymentIndex by remember { mutableIntStateOf(0) }
    val paymentOptions = listOf("Tarjeta de Crédito", "PayPal", "Google Pay")

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Pinned Header
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Switches & Toggles",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Inputs binarios de control de estados: switch, casilla y botón de radio.",
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

        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            // Global controller card
            SpecSectionCard(
                title = "Global Settings",
                description = "Configuración general para evaluar todos los controles."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Habilitar Interacción (Enabled)",
                        style = typography.bodyMedium,
                        color = colors.textPrimary
                    )
                    CanvasKitSwitch(
                        checked = globalEnabled,
                        onCheckedChange = { globalEnabled = it }
                    )
                }
            }

            // Section 1: Switch Playground
            SpecSectionCard(
                title = "Toggle Switches",
                description = "Ideales para cambiar preferencias directas y configuraciones instantáneas."
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Configuración de Notificaciones",
                                style = typography.labelLarge,
                                color = colors.textPrimary
                            )
                            Text(
                                text = if (switch1Checked) "Estado: Activado" else "Estado: Desactivado",
                                style = typography.labelSmall,
                                color = if (switch1Checked) colors.brandAccent else colors.textSecondary
                            )
                        }
                        CanvasKitSwitch(
                            checked = switch1Checked,
                            onCheckedChange = { switch1Checked = it },
                            enabled = globalEnabled
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Modo Desarrollador",
                                style = typography.labelLarge,
                                color = colors.textPrimary
                            )
                            Text(
                                text = if (switch2Checked) "Estado: Activado" else "Estado: Desactivado",
                                style = typography.labelSmall,
                                color = if (switch2Checked) colors.brandAccent else colors.textSecondary
                            )
                        }
                        CanvasKitSwitch(
                            checked = switch2Checked,
                            onCheckedChange = { switch2Checked = it },
                            enabled = globalEnabled
                        )
                    }
                }
            }

            // Section 2: Checkboxes List (Multi-selection)
            SpecSectionCard(
                title = "Checkboxes Checklist",
                description = "Casillas de selección múltiple. Permiten elegir varios elementos de una lista."
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    // Task 1
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
                                text = "Compilar código fuente",
                                style = typography.bodyMedium,
                                color = colors.textPrimary
                            )
                        }
                    }

                    // Task 2
                    InteractiveRowWrapper(
                        checked = checkbox2Checked,
                        onClick = { checkbox2Checked = !checkbox2Checked },
                        enabled = globalEnabled
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                        ) {
                            CanvasKitCheckbox(
                                checked = checkbox2Checked,
                                onCheckedChange = { checkbox2Checked = it },
                                enabled = globalEnabled
                            )
                            Text(
                                text = "Ejecutar pruebas instrumentadas",
                                style = typography.bodyMedium,
                                color = colors.textPrimary
                            )
                        }
                    }

                    // Task 3
                    InteractiveRowWrapper(
                        checked = checkbox3Checked,
                        onClick = { checkbox3Checked = !checkbox3Checked },
                        enabled = globalEnabled
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                        ) {
                            CanvasKitCheckbox(
                                checked = checkbox3Checked,
                                onCheckedChange = { checkbox3Checked = it },
                                enabled = globalEnabled
                            )
                            Text(
                                text = "Verificar contraste WCAG AA",
                                style = typography.bodyMedium,
                                color = colors.textPrimary
                            )
                        }
                    }
                }
            }

            // Section 3: Radio Buttons Group (Single-selection)
            SpecSectionCard(
                title = "Radio Button Groups",
                description = "Selección única mutuamente excluyente entre múltiples opciones."
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

            // Section 4: Design tokens reference
            SpecSectionCard(
                title = "Design Tokens Reference",
                description = "Especificaciones de diseño para Switches y Toggles."
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.xs),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TokenRow(name = "Switch Size", value = "52.dp x 32.dp")
                    TokenRow(name = "Switch Thumb Diameter", value = "24.dp")
                    TokenRow(name = "Checkbox Size", value = "24.dp x 24.dp")
                    TokenRow(name = "RadioButton Size", value = "24.dp x 24.dp")
                    TokenRow(name = "RadioButton Dot Diameter", value = "12.dp")
                    TokenRow(name = "Interactive Touch Area", value = ">= 48.dp x 48.dp")
                    TokenRow(name = "Accent Fill Color", value = "colors.brandAccent")
                    TokenRow(name = "Border Color", value = "colors.borderSubtle")
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
        modifier = modifier.fillMaxWidth()
    ) {
        content()
    }
}

@Composable
private fun TokenRow(name: String, value: String) {
    val colors = CanvasKitTheme.colors

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = CanvasKitTheme.typography.bodyMedium,
            color = colors.textSecondary
        )
        Text(
            text = value,
            style = CanvasKitTheme.typography.labelLarge,
            color = colors.textPrimary
        )
    }
}
