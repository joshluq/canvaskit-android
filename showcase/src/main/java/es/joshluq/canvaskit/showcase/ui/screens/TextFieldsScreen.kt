package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.inputs.CanvasKitTextField
import es.joshluq.canvaskit.components.inputs.CanvasKitTextFieldVariant
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * TextFieldsScreen showcases outlines, filled, error, and password input states
 * for the CanvasKitTextField component.
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
                text = "Text Fields Showcase",
                style = CanvasKitTheme.typography.headingLarge,
                color = CanvasKitTheme.colors.textPrimary
            )
        }

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
    }
}
