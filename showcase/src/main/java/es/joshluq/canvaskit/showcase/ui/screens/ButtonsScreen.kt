package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * ButtonsScreen showcases primary, secondary, ghost, and icon buttons in a
 * clean and organized specification card layout.
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

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Buttons & Actions",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Fichas técnicas y estados de pulsación interactiva.",
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

        // Section: Primary Buttons Card
        SpecSectionCard(
            title = "Primary Buttons",
            description = "Utilizado para la acción principal en un flujo. Admite estados normal, deshabilitado y de carga asíncrona."
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                verticalArrangement = Arrangement.spacedBy(spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                CanvasKitButton(onClick = onButtonClick) {
                    Text(
                        "Primary Default",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.backgroundPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, enabled = false) {
                    Text(
                        "Disabled",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.backgroundPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, loading = true) {
                    Text(
                        "Loading",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.backgroundPrimary
                    )
                }
            }
        }

        // Section: Secondary Buttons Card
        SpecSectionCard(
            title = "Secondary Buttons",
            description = "Acción secundaria del flujo. Ofrece un diseño limpio con borde sutil."
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                verticalArrangement = Arrangement.spacedBy(spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Secondary) {
                    Text(
                        "Secondary Default",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.brandPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Secondary, enabled = false) {
                    Text(
                        "Disabled",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.brandPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Secondary, loading = true) {
                    Text(
                        "Loading",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.brandPrimary
                    )
                }
            }
        }

        // Section: Ghost Buttons Card
        SpecSectionCard(
            title = "Ghost Buttons",
            description = "Acción de menor prioridad visual o enlaces de texto interactivo."
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                verticalArrangement = Arrangement.spacedBy(spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Ghost) {
                    Text(
                        "Ghost Default",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.brandAccent
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Ghost, enabled = false) {
                    Text(
                        "Disabled",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.brandAccent
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Ghost, loading = true) {
                    Text(
                        "Loading",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.brandAccent
                    )
                }
            }
        }

        // Section: Icon Buttons Card
        SpecSectionCard(
            title = "Icon Buttons",
            description = "Iconos circulares o cuadrangulares interactivos con animaciones de micro-escala."
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CanvasKitIconButton(onClick = onButtonClick) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Añadir",
                        tint = colors.brandPrimary
                    )
                }

                CanvasKitIconButton(
                    onClick = onButtonClick,
                    shape = shapes.medium,
                    backgroundColor = colors.backgroundSecondary,
                    contentColor = colors.brandAccent
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuración",
                        tint = colors.brandAccent
                    )
                }

                CanvasKitIconButton(onClick = onButtonClick, enabled = false) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuración",
                        tint = colors.brandPrimary
                    )
                }

                CanvasKitIconButton(onClick = onButtonClick, loading = true) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuración",
                        tint = colors.brandPrimary
                    )
                }
            }
        }
    }
}
}

/**
 * Reusable section card layout container for the CanvasKit showcase spec sheets.
 */
@Composable
fun SpecSectionCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shapes.medium)
            .background(colors.backgroundSecondary)
            .border(width = 1.dp, color = colors.borderSubtle, shape = shapes.medium)
            .padding(spacing.md)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            Column {
                Text(
                    text = title,
                    style = CanvasKitTheme.typography.headingMedium,
                    color = colors.brandAccent
                )
                Text(
                    text = description,
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textSecondary
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(colors.borderSubtle)
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}
