package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.inputs.CanvasKitTextField
import es.joshluq.canvaskit.components.inputs.CanvasKitTextFieldVariant
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * TextFieldsScreen showcases the "Artisanal Precision" input hierarchy.
 */
@Composable
fun TextFieldsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var outlinedText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Input Precision",
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Refined text fields and semantic validation states.",
                        style = CanvasKitTheme.typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(
                    onClick = onBack,
                    contentDescription = "Atrás"
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {

            // Introduction Section
            Column(modifier = Modifier.padding(horizontal = spacing.xs)) {
                Text(
                    text = "Refined\nInputs",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Text fields designed for professional clarity. Every pixel is calculated for the ultimate input experience.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Outlined
            SpecSectionCard(
                title = "Standard Precision",
                description = "Our signature outlined input for maximum clarity."
            ) {
                CanvasKitTextField(
                    value = outlinedText,
                    onValueChange = { outlinedText = it },
                    label = "Full Name",
                    helperText = "Enter your legal name as it appears on ID.",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Section: Password
            SpecSectionCard(
                title = "Secure Input",
                description = "Tactile password toggling with semantic iconography."
            ) {
                CanvasKitTextField(
                    value = passwordText,
                    onValueChange = { passwordText = it },
                    label = "Secure Password",
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        CanvasKitIconButton(
                            onClick = { passwordVisible = !passwordVisible },
                            contentDescription = if (passwordVisible) "Ocultar" else "Mostrar"
                        ) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Check else Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Section: Error State
            SpecSectionCard(
                title = "Validation Precision",
                description = "Immediate semantic feedback for erroneous inputs."
            ) {
                CanvasKitTextField(
                    value = "invalid-email",
                    onValueChange = { },
                    label = "Email Address",
                    isError = true,
                    errorText = "Please enter a valid artisanal email address.",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
