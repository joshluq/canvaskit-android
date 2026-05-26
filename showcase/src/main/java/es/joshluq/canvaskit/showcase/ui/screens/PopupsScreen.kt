package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.feedback.CanvasKitPopup
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * PopupsScreen showcases anchored context popups and informational tooltips.
 */
@Composable
fun PopupsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showPopup1 by remember { mutableStateOf(false) }
    var showPopup2 by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(CanvasKitTheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg)
    ) {
        // Navigation Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)
        ) {
            CanvasKitIconButton(onClick = onBack) {
                Text(
                    text = "←",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = CanvasKitTheme.colors.brandPrimary
                )
            }
            Text(
                text = "Popups Showcase",
                style = CanvasKitTheme.typography.headingLarge,
                color = CanvasKitTheme.colors.textPrimary
            )
        }

        // Section: Popups Trigger
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Popups & Anchored Menus",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Anchor Box for Popup 1 (Menu)
                Box {
                    CanvasKitButton(
                        onClick = { showPopup1 = !showPopup1 }
                    ) {
                        Text(
                            "Acciones Rápidas",
                            style = CanvasKitTheme.typography.labelLarge,
                            color = CanvasKitTheme.colors.backgroundPrimary
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
                                color = CanvasKitTheme.colors.textSecondary,
                                modifier = Modifier.padding(bottom = CanvasKitTheme.spacing.xs)
                            )
                            Text(
                                text = "Compartir enlace",
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = CanvasKitTheme.colors.textPrimary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        showPopup1 = false
                                        Toast.makeText(context, "Enlace copiado", Toast.LENGTH_SHORT).show()
                                    }
                                    .padding(vertical = CanvasKitTheme.spacing.xxs)
                            )
                            Text(
                                text = "Configuración",
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = CanvasKitTheme.colors.textPrimary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        showPopup1 = false
                                        Toast.makeText(context, "Configuración abierta", Toast.LENGTH_SHORT).show()
                                    }
                                    .padding(vertical = CanvasKitTheme.spacing.xxs)
                            )
                        }
                    }
                }

                // Anchor Box for Popup 2 (Tooltip Style)
                Box {
                    CanvasKitButton(
                        onClick = { showPopup2 = !showPopup2 },
                        variant = CanvasKitButtonVariant.Secondary
                    ) {
                        Text(
                            "Ver Tooltip Info",
                            style = CanvasKitTheme.typography.labelLarge,
                            color = CanvasKitTheme.colors.brandPrimary
                        )
                    }

                    CanvasKitPopup(
                        expanded = showPopup2,
                        onDismissRequest = { showPopup2 = false },
                        alignment = Alignment.TopCenter,
                        offset = DpOffset(0.dp, 52.dp),
                        shape = CanvasKitTheme.shapes.small
                    ) {
                        Text(
                            text = "Este es un popover de información útil sobre el componente.",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = CanvasKitTheme.colors.textPrimary,
                            modifier = Modifier.width(220.dp)
                        )
                    }
                }
            }
        }
    }
}
