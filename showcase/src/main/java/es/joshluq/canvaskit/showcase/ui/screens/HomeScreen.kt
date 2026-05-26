package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * HomeScreen is the catalog landing screen. It displays all available and planned
 * Design System components categorized in structured layout cards.
 */
@Composable
fun HomeScreen(
    onNavigateToButtons: () -> Unit,
    onNavigateToTextFields: () -> Unit,
    onNavigateToDialogs: () -> Unit,
    onNavigateToPopups: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(CanvasKitTheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg)
    ) {
        // Header Area
        Column {
            Text(
                text = "CanvasKit Showcase",
                style = CanvasKitTheme.typography.displayMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(CanvasKitTheme.spacing.xxs))
            Text(
                text = "Explora y prueba los componentes del Atelier Design System.",
                style = CanvasKitTheme.typography.bodyLarge,
                color = CanvasKitTheme.colors.textSecondary
            )
        }

        // Section: Active Components
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Componentes Disponibles",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )

            ComponentItem(
                name = "Buttons",
                description = "Botones Primary, Secondary, Ghost e Icon con micro-animaciones de pulsado.",
                onClick = onNavigateToButtons
            )

            ComponentItem(
                name = "Text Fields",
                description = "Campos de texto Outlined y Filled con estados de validación, errores y contraseñas.",
                onClick = onNavigateToTextFields
            )

            ComponentItem(
                name = "Dialogs",
                description = "Ventanas modales accesibles y plantillas de contenido predefinido.",
                onClick = onNavigateToDialogs
            )

            ComponentItem(
                name = "Popups & Menus",
                description = "Desplegables y bocadillos de información anclados con transiciones animadas.",
                onClick = onNavigateToPopups
            )
        }

        // Section: Planned Components
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Futuros Componentes (Atelier Road)",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )

            ComponentItem(
                name = "Cards & Containers",
                description = "Contenedores estructurados, tarjetas de selección y layouts adaptables.",
                enabled = false
            )

            ComponentItem(
                name = "Switches & Selection Controls",
                description = "Interruptores, casillas de verificación (checkboxes) y botones de selección única.",
                enabled = false
            )

            ComponentItem(
                name = "Banners & Feedback",
                description = "Banners de aviso, tarjetas de estado y notificaciones flotantes (snackbars).",
                enabled = false
            )

            ComponentItem(
                name = "Skeletons",
                description = "Estructuras de carga de contenido dinámico con efectos de brillo (shimmer).",
                enabled = false
            )
        }
    }
}

@Composable
private fun ComponentItem(
    name: String,
    description: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    val borderStrokeColor = if (enabled) colors.borderSubtle else colors.borderSubtle.copy(alpha = 0.5f)
    val containerAlpha = if (enabled) 1.0f else 0.5f

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shapes.medium)
            .background(colors.backgroundSecondary)
            .border(width = 1.dp, color = borderStrokeColor, shape = shapes.medium)
            .then(
                if (enabled && onClick != null) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                }
            )
            .padding(spacing.md)
            .alpha(containerAlpha)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.xs)
                ) {
                    Text(
                        text = name,
                        style = CanvasKitTheme.typography.headingMedium,
                        color = colors.textPrimary
                    )
                    if (!enabled) {
                        Box(
                            modifier = Modifier
                                .clip(shapes.pill)
                                .background(colors.borderSubtle)
                                .padding(horizontal = spacing.xs, vertical = spacing.xxxs)
                        ) {
                            Text(
                                text = "Próximamente",
                                style = CanvasKitTheme.typography.labelSmall,
                                color = colors.textSecondary
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(spacing.xxs))
                Text(
                    text = description,
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textSecondary
                )
            }
            if (enabled) {
                Text(
                    text = "→",
                    style = CanvasKitTheme.typography.headingLarge,
                    color = colors.brandAccent
                )
            }
        }
    }
}
