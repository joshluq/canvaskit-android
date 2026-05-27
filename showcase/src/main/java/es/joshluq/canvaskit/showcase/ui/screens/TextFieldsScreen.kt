package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.inputs.CanvasKitTextField
import es.joshluq.canvaskit.components.inputs.CanvasKitTextFieldVariant
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * TextFieldsScreen showcases outlines, filled, error, and password input states
 * in a premium spec card layout.
 */
@Composable
fun TextFieldsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var outlinedText by remember { mutableStateOf("") }
    var filledText by remember { mutableStateOf("") }
    var errorTextVal by remember { mutableStateOf("Texto no válido") }
    var passwordText by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

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
                        text = "Text Fields & Inputs",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Fichas técnicas y estados de campos de texto interactivos.",
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

        // Section: Outlined Card
        SpecSectionCard(
            title = "Outlined Text Fields",
            description = "Estilo clásico con borde. Ideal para la mayoría de los formularios por su clara delimitación visual."
        ) {
            CanvasKitTextField(
                value = outlinedText,
                onValueChange = { outlinedText = it },
                label = "Outlined Label",
                helperText = "Helper text showing requirements",
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Section: Filled Card
        SpecSectionCard(
            title = "Filled Text Fields",
            description = "Estilo con fondo relleno y línea inferior. Útil para una rápida identificación en pantallas densas."
        ) {
            CanvasKitTextField(
                value = filledText,
                onValueChange = { filledText = it },
                label = "Filled Label",
                variant = CanvasKitTextFieldVariant.Filled,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Section: Validation Card
        SpecSectionCard(
            title = "Error & Validation",
            description = "Manejo automático del estado de error visual con descripción semántica de apoyo."
        ) {
            CanvasKitTextField(
                value = errorTextVal,
                onValueChange = { errorTextVal = it },
                label = "Validation Field",
                isError = true,
                errorText = "Este campo contiene un error de validación.",
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Section: Password Card
        SpecSectionCard(
            title = "Password Visibility Toggling",
            description = "Muestra la integración de iconos interactivos (trailingIcon) para conmutar la visibilidad de la contraseña."
        ) {
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
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Check else Icons.Default.Close,
                            contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                            tint = colors.textSecondary
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
}
