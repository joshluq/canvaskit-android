package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.feedback.CanvasKitDialog
import es.joshluq.canvaskit.components.feedback.CanvasKitDialogContent
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.components.inputs.CanvasKitTextField
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * DialogsScreen showcases alert/confirmation dialogs and custom input dialogs
 * in a premium spec card layout.
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
                        text = "Dialogs & Overlays",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Fichas técnicas y estados de diálogos modales emergentes.",
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

        // Section: Confirmation Dialog Card
        SpecSectionCard(
            title = "Alert & Confirmation Dialog",
            description = "Ventana modal bloqueante para confirmar acciones destructivas o críticas."
        ) {
            CanvasKitButton(onClick = { showConfirmDialog = true }) {
                Text(
                    "Mostrar Confirmación",
                    style = CanvasKitTheme.typography.labelLarge,
                    color = colors.backgroundPrimary
                )
            }
        }

        // Section: Custom Dialog Card
        SpecSectionCard(
            title = "Custom Content Dialog",
            description = "Ventana modal con layouts personalizados dinámicos (inputs, descripciones complejas)."
        ) {
            CanvasKitButton(
                onClick = { showCustomDialog = true },
                variant = CanvasKitButtonVariant.Secondary
            ) {
                Text(
                    "Mostrar Personalizado",
                    style = CanvasKitTheme.typography.labelLarge,
                    color = colors.brandPrimary
                )
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
                            color = colors.textPrimary
                        )
                    },
                    content = {
                        Text(
                            text = "¿Estás seguro de que deseas eliminar este elemento permanentemente? Esta acción no se puede deshacer.",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textSecondary
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
                                color = colors.brandAccent
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
                                color = colors.backgroundPrimary
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
                            color = colors.textPrimary
                        )
                    },
                    content = {
                        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                            Text(
                                text = "Ingresa el nombre del nuevo workspace para comenzar a organizar tus proyectos.",
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = colors.textSecondary
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
                                color = colors.brandAccent
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
                                color = colors.backgroundPrimary
                            )
                        }
                    }
                )
            }
        }
    }
}
}
