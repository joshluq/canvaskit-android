package es.joshluq.canvaskit.showcase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.inputs.CanvasKitTextField
import es.joshluq.canvaskit.components.inputs.CanvasKitTextFieldVariant
import es.joshluq.canvaskit.components.feedback.CanvasKitDialog
import es.joshluq.canvaskit.components.feedback.CanvasKitDialogContent
import es.joshluq.canvaskit.components.feedback.CanvasKitPopup
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import es.joshluq.canvaskit.showcase.ui.theme.ShowcaseTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowcaseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = CanvasKitTheme.colors.backgroundPrimary
                ) { innerPadding ->
                    ShowcaseScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowcaseScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val onButtonClick = {
        Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(CanvasKitTheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg)
    ) {
        // Header
        Column {
            Text(
                text = "CanvasKit Showcase",
                style = CanvasKitTheme.typography.displayMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(CanvasKitTheme.spacing.xxs))
            Text(
                text = "Atelier Clean Design System Components",
                style = CanvasKitTheme.typography.bodyLarge,
                color = CanvasKitTheme.colors.textSecondary
            )
        }

        // Section: Primary Buttons
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Primary Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                CanvasKitButton(onClick = onButtonClick) {
                    Text(
                        "Primary Default",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.backgroundPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, enabled = false) {
                    Text(
                        "Disabled",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.backgroundPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, loading = true) {
                    Text(
                        "Loading",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.backgroundPrimary
                    )
                }
            }
        }

        // Section: Secondary Buttons
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Secondary Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Secondary) {
                    Text(
                        "Secondary Default",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Secondary, enabled = false) {
                    Text(
                        "Disabled",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Secondary, loading = true) {
                    Text(
                        "Loading",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }
            }
        }

        // Section: Ghost Buttons
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Ghost Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Ghost) {
                    Text(
                        "Ghost Default",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandAccent
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Ghost, enabled = false) {
                    Text(
                        "Disabled",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandAccent
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Ghost, loading = true) {
                    Text(
                        "Loading",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandAccent
                    )
                }
            }
        }

        // Section: Icon Buttons
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Icon Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Circle Default
                CanvasKitIconButton(onClick = onButtonClick) {
                    Text(
                        "＋",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }

                // Rounded Container Background
                CanvasKitIconButton(
                    onClick = onButtonClick,
                    shape = CanvasKitTheme.shapes.medium,
                    backgroundColor = CanvasKitTheme.colors.backgroundSecondary,
                    contentColor = CanvasKitTheme.colors.brandAccent
                ) {
                    Text(
                        "⚙",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = CanvasKitTheme.colors.brandAccent
                    )
                }

                // Disabled
                CanvasKitIconButton(onClick = onButtonClick, enabled = false) {
                    Text(
                        "⚙",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }

                // Loading
                CanvasKitIconButton(onClick = onButtonClick, loading = true) {
                    Text(
                        "⚙",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }
            }
        }

        // Section: Text Fields
        var outlinedText by remember { mutableStateOf("") }
        var filledText by remember { mutableStateOf("") }
        var errorTextVal by remember { mutableStateOf("Texto no válido") }
        var passwordText by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Text Inputs (Material 3 Inspired)",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )

            // Outlined TextField
            CanvasKitTextField(
                value = outlinedText,
                onValueChange = { outlinedText = it },
                label = "Outlined Label",
                helperText = "Helper text showing requirements",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(CanvasKitTheme.spacing.xs))

            // Filled TextField
            CanvasKitTextField(
                value = filledText,
                onValueChange = { filledText = it },
                label = "Filled Label",
                variant = CanvasKitTextFieldVariant.Filled,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(CanvasKitTheme.spacing.xs))

            // Error Outlined TextField
            CanvasKitTextField(
                value = errorTextVal,
                onValueChange = { errorTextVal = it },
                label = "Validation Field",
                isError = true,
                errorText = "Este campo contiene un error de validación.",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(CanvasKitTheme.spacing.xs))

            // Password with visibility toggling
            CanvasKitTextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                label = "Password Field",
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    CanvasKitIconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Text(
                            text = if (passwordVisible) "👁" else "🙈",
                            style = CanvasKitTheme.typography.bodyLarge,
                            color = CanvasKitTheme.colors.textSecondary
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Section: Dialogs
        var showConfirmDialog by remember { mutableStateOf(false) }
        var showCustomDialog by remember { mutableStateOf(false) }
        var customDialogInput by remember { mutableStateOf("") }

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

        // Section: Popups
        var showPopup1 by remember { mutableStateOf(false) }
        var showPopup2 by remember { mutableStateOf(false) }

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
                // Anchor Box for Popup 1
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

@Preview(showBackground = true)
@Composable
fun ShowcasePreview() {
    ShowcaseTheme {
        ShowcaseScreen()
    }
}


