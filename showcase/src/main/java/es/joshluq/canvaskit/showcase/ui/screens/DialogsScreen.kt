package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.feedback.CanvasKitDialog
import es.joshluq.canvaskit.components.feedback.CanvasKitDialogContent
import es.joshluq.canvaskit.components.inputs.CanvasKitTextField
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * DialogsScreen showcases alert/confirmation dialogs and custom input dialogs.
 */
@Composable
fun DialogsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showCustomDialog by remember { mutableStateOf(false) }
    var customDialogInput by remember { mutableStateOf("") }

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
                text = "Dialogs Showcase",
                style = CanvasKitTheme.typography.headingLarge,
                color = CanvasKitTheme.colors.textPrimary
            )
        }

        // Section: Dialog Triggers
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Dialogs (Modal Overlays)",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CanvasKitButton(onClick = { showConfirmDialog = true }) {
                    Text(
                        "Alerta/Confirmación",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.backgroundPrimary
                    )
                }

                CanvasKitButton(
                    onClick = { showCustomDialog = true },
                    variant = CanvasKitButtonVariant.Secondary
                ) {
                    Text(
                        "Contenido Personalizado",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }
            }
        }

        // Render Dialogs if active
        if (showConfirmDialog) {
            CanvasKitDialog(
                onDismissRequest = { showConfirmDialog = false }
            ) {
                CanvasKitDialogContent(
                    title = {
                        Text(
                            text = "Eliminar elemento",
                            style = CanvasKitTheme.typography.headingLarge,
                            color = CanvasKitTheme.colors.textPrimary
                        )
                    },
                    content = {
                        Text(
                            text = "¿Estás seguro de que deseas eliminar este elemento permanentemente? Esta acción no se puede deshacer.",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = CanvasKitTheme.colors.textSecondary
                        )
                    },
                    buttons = {
                        CanvasKitButton(
                            onClick = { showConfirmDialog = false },
                            variant = CanvasKitButtonVariant.Ghost
                        ) {
                            Text(
                                "Cancelar",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = CanvasKitTheme.colors.brandAccent
                            )
                        }
                        CanvasKitButton(
                            onClick = {
                                showConfirmDialog = false
                                Toast.makeText(context, "Elemento eliminado", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Text(
                                "Eliminar",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = CanvasKitTheme.colors.backgroundPrimary
                            )
                        }
                    }
                )
            }
        }

        if (showCustomDialog) {
            CanvasKitDialog(
                onDismissRequest = { showCustomDialog = false }
            ) {
                CanvasKitDialogContent(
                    title = {
                        Text(
                            text = "Nuevo Workspace",
                            style = CanvasKitTheme.typography.headingLarge,
                            color = CanvasKitTheme.colors.textPrimary
                        )
                    },
                    content = {
                        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.xs)) {
                            Text(
                                text = "Ingresa el nombre del nuevo workspace para comenzar a organizar tus proyectos.",
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = CanvasKitTheme.colors.textSecondary
                            )
                            CanvasKitTextField(
                                value = customDialogInput,
                                onValueChange = { customDialogInput = it },
                                label = "Nombre del workspace",
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    buttons = {
                        CanvasKitButton(
                            onClick = { showCustomDialog = false },
                            variant = CanvasKitButtonVariant.Ghost
                        ) {
                            Text(
                                "Cancelar",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = CanvasKitTheme.colors.brandAccent
                            )
                        }
                        CanvasKitButton(
                            onClick = {
                                if (customDialogInput.isNotBlank()) {
                                    showCustomDialog = false
                                    Toast.makeText(context, "Creado: $customDialogInput", Toast.LENGTH_SHORT).show()
                                    customDialogInput = ""
                                } else {
                                    Toast.makeText(context, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
                                }
                            }
                        ) {
                            Text(
                                "Crear",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = CanvasKitTheme.colors.backgroundPrimary
                            )
                        }
                    }
                )
            }
        }
    }
}
