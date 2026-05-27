package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.feedback.CanvasKitPopup
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * PopupsScreen showcases anchored context popups and informational tooltips
 * in a premium spec card layout.
 */
@Composable
fun PopupsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showPopup1 by remember { mutableStateOf(false) }
    var showPopup2 by remember { mutableStateOf(false) }

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
                        text = "Popups & Menus",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Fichas técnicas y estados de ventanas flotantes ancladas.",
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

        // Section: Anchored Dropdown Menu Card
        SpecSectionCard(
            title = "Dropdown / Context Menu Popup",
            description = "Menú flotante anclado a un botón para mostrar una lista de acciones rápidas."
        ) {
            Box {
                CanvasKitButton(
                    onClick = { showPopup1 = !showPopup1 }
                ) {
                    Text(
                        "Acciones Rápidas",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.backgroundPrimary
                    )
                }

                CanvasKitPopup(
                    expanded = showPopup1,
                    onDismissRequest = { showPopup1 = false },
                    alignment = Alignment.TopStart,
                    offset = DpOffset(0.dp, 52.dp)
                ) {
                    Column(modifier = Modifier.width(180.dp)) {
                        Text(
                            text = "Menú de Opciones",
                            style = CanvasKitTheme.typography.labelSmall,
                            color = colors.textSecondary,
                            modifier = Modifier.padding(bottom = spacing.xs)
                        )
                        Text(
                            text = "Compartir enlace",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textPrimary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    showPopup1 = false
                                    Toast.makeText(context, "Enlace copiado", Toast.LENGTH_SHORT).show()
                                }
                                .padding(vertical = spacing.xxs)
                        )
                        Text(
                            text = "Configuración",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textPrimary,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        showPopup1 = false
                                        Toast.makeText(context, "Configuración abierta", Toast.LENGTH_SHORT).show()
                                    }
                                    .padding(vertical = spacing.xxs)
                        )
                    }
                }
            }
        }

        // Section: Tooltip Info Popover Card
        SpecSectionCard(
            title = "Tooltip Info Popover",
            description = "Bocadillo flotante con esquinas suavizadas para mostrar información contextual corta."
        ) {
            Box {
                CanvasKitButton(
                    onClick = { showPopup2 = !showPopup2 },
                    variant = CanvasKitButtonVariant.Secondary
                ) {
                    Text(
                        "Ver Tooltip Info",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.brandPrimary
                    )
                }

                CanvasKitPopup(
                    expanded = showPopup2,
                    onDismissRequest = { showPopup2 = false },
                    alignment = Alignment.TopCenter,
                    offset = DpOffset(0.dp, 52.dp),
                    shape = shapes.small
                ) {
                    Text(
                        text = "Este es un popover de información útil sobre el componente.",
                        style = CanvasKitTheme.typography.bodyMedium,
                        color = colors.textPrimary,
                        modifier = Modifier.width(220.dp)
                    )
                }
            }
        }
    }
}
}
