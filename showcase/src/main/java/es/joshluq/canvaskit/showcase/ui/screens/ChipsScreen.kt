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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    val filters = listOf("Todo", "Recientes", "Populares", "Favoritos")
    
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
                        text = "Componentes compactos para acciones, filtros o selección.",
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
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            
            // ──────────────────────────────────────────────────────────
            // Section 1: Filter Chips
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Filter Chips",
                description = "Uso de la variante Outlined para filtros de selección simple."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.xs)
                ) {
                    filters.take(3).forEach { filter ->
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
                description = "Variante Ghost para acciones rápidas."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.xs)
                ) {
                    CanvasKitChip(
                        onClick = {},
                        variant = CanvasKitChipVariant.Ghost,
                        label = {
                            Text(text = "Compartir", style = typography.labelLarge, color = colors.textSecondary)
                        }
                    )
                    CanvasKitChip(
                        onClick = {},
                        variant = CanvasKitChipVariant.Ghost,
                        label = {
                            Text(text = "Descargar", style = typography.labelLarge, color = colors.textSecondary)
                        }
                    )
                }
            }

            // ──────────────────────────────────────────────────────────
            // Section 3: Input / Dismissible Chips
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Input Chips",
                description = "Chips con botón de cierre (Primary)."
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
                                Text(text = "Filtro Aplicado", style = typography.labelLarge, color = colors.textPrimary)
                            },
                            trailingIcon = {
                                CanvasKitIconButton(
                                    onClick = { showDismissible = false }
                                ) {
                                    Icon(Icons.Default.Close, contentDescription = "Eliminar", tint = colors.textSecondary)
                                }
                            }
                        )
                    } else {
                        CanvasKitChip(
                            onClick = { showDismissible = true },
                            variant = CanvasKitChipVariant.Outlined,
                            label = {
                                Text("Añadir filtro", style = typography.labelLarge, color = colors.brandAccent)
                            }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(spacing.lg))
        }
    }
}
