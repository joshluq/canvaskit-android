package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
 * CardsScreen showcases card and container variations, click-scaling states,
 * and complex layouts built using CanvasKitCard's Slot APIs.
 */
@Composable
fun CardsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    var selectedPlanIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Cards & Containers",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Contenedores estructurados con soporte de estados, selección y elevación.",
                        style = CanvasKitTheme.typography.labelSmall,
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

        // Section 1: Basic Card Variants
        SpecSectionCard(
            title = "Visual Variants",
            description = "Diferentes estilos de contenedores según la jerarquía de la interfaz (Outlined, Elevated, Flat)."
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.md),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Outlined Card
                CanvasKitCard(
                    variant = CanvasKitCardVariant.Outlined,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Outlined Card",
                            style = CanvasKitTheme.typography.headingMedium,
                            color = colors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(spacing.xxs))
                        Text(
                            text = "Contenedor plano con borde sutil. Ideal para separar bloques de contenido en interfaces densas.",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textSecondary
                        )
                    }
                }

                // Elevated Card
                CanvasKitCard(
                    variant = CanvasKitCardVariant.Elevated,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Elevated Card",
                            style = CanvasKitTheme.typography.headingMedium,
                            color = colors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(spacing.xxs))
                        Text(
                            text = "Contenedor elevado con sombra suave. Ideal para resaltar sobre el fondo principal.",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textSecondary
                        )
                    }
                }

                // Flat Card
                CanvasKitCard(
                    variant = CanvasKitCardVariant.Flat,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Flat Card",
                            style = CanvasKitTheme.typography.headingMedium,
                            color = colors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(spacing.xxs))
                        Text(
                            text = "Contenedor plano sin bordes ni sombras. Utiliza un color secundario de fondo para agrupar elementos.",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textSecondary
                        )
                    }
                }
            }
        }

        // Section 2: Interactive Selection Grid
        SpecSectionCard(
            title = "Selection & Interaction States",
            description = "Los contenedores cambian su escala dinámicamente al presionarse y aplican un borde de acento en estado de selección."
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                val plans = listOf(
                    Triple("Plan Starter", "$0 / mes", "Acceso limitado a la biblioteca básica."),
                    Triple("Plan Pro", "$19 / mes", "Todos los componentes avanzados y soporte prioritario."),
                    Triple("Plan Enterprise", "$49 / mes", "Soporte personalizado, tokens ilimitados y multi-licencia.")
                )

                plans.forEachIndexed { index, (title, price, desc) ->
                    CanvasKitCard(
                        variant = CanvasKitCardVariant.Outlined,
                        selected = selectedPlanIndex == index,
                        onClick = { selectedPlanIndex = index },
                        modifier = Modifier.fillMaxWidth()
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
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = price,
                                    style = CanvasKitTheme.typography.labelLarge,
                                    color = if (selectedPlanIndex == index) colors.brandAccent else colors.textPrimary
                                )
                                if (selectedPlanIndex == index) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = colors.brandAccent,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Text(
                                            text = "Activo",
                                            style = CanvasKitTheme.typography.labelSmall,
                                            color = colors.brandAccent
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Section 3: High-Fidelity Complex Slot Card Layout
        SpecSectionCard(
            title = "Modular Slot APIs",
            description = "Estructura avanzada utilizando los slots de header, content y footer nativos."
        ) {
            CanvasKitCard(
                variant = CanvasKitCardVariant.Outlined,
                header = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Configuración del Sistema",
                                style = CanvasKitTheme.typography.headingMedium,
                                color = colors.textPrimary
                            )
                            Text(
                                text = "Panel de control general de tokens y compilación.",
                                style = CanvasKitTheme.typography.labelSmall,
                                color = colors.textSecondary
                            )
                        }
                        // Badges/Status indicator mockup (Flat container inside)
                        CanvasKitCard(
                            variant = CanvasKitCardVariant.Flat,
                            shape = shapes.pill,
                            modifier = Modifier.padding(horizontal = spacing.xxs)
                        ) {
                            // minimal padding wrapper
                            Text(
                                text = "V2.3 COMPILER",
                                style = CanvasKitTheme.typography.labelSmall,
                                color = colors.brandAccent,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        }
                    }
                },
                footer = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitButton(
                            onClick = { /* no-op */ },
                            variant = CanvasKitButtonVariant.Ghost
                        ) {
                            Text("Restaurar")
                        }
                        Spacer(modifier = Modifier.width(spacing.xs))
                        CanvasKitButton(
                            onClick = { /* no-op */ },
                            variant = CanvasKitButtonVariant.Primary
                        ) {
                            Text(
                                "Guardar cambios",
                                color = colors.backgroundPrimary
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                    Text(
                        text = "El compilador K2 está configurado con optimización de estabilidad Compose para colecciones inmutables. El tiempo de recomposición se reduce un 45%.",
                        style = CanvasKitTheme.typography.bodyMedium,
                        color = colors.textSecondary
                    )
                }
            }
        }
    }
}
}
