package es.joshluq.canvaskit.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun ButtonsPreviewContainer() {
    val spacing = CanvasKitTheme.spacing
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.md)
    ) {
        // Standard Buttons
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            Text(
                text = "Standard Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            FlowRow (horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                CanvasKitButton(onClick = {}) {
                    Text(
                        text = "Primary",
                        color = CanvasKitTheme.colors.textPrimary
                    )
                }
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Secondary
                ) {
                    Text(
                        text = "Secondary",
                        color = CanvasKitTheme.colors.textPrimary
                    )
                }
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Ghost
                ) {
                    Text(
                        text = "Ghost",
                        color = CanvasKitTheme.colors.textPrimary
                    )
                }
            }
        }

        // States
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            Text(
                text = "Button States",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                CanvasKitButton(onClick = {}, enabled = false) { Text("Disabled") }
                CanvasKitButton(onClick = {}, loading = true) { Text("Loading") }
            }
        }

        // With Icons
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            Text(
                text = "Buttons with Icons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                CanvasKitButton(onClick = {}) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = CanvasKitTheme.colors.contentColor)
                    Text(text = "Add Item", color = CanvasKitTheme.colors.textPrimary)
                }
                CanvasKitButton(onClick = {}, variant = CanvasKitButtonVariant.Secondary) {
                    Text(text = "Continue", color = CanvasKitTheme.colors.textPrimary)
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = CanvasKitTheme.colors.contentColor)
                }
            }
        }

        // Icon Buttons
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            Text(
                text = "Icon Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                CanvasKitIconButton(
                    onClick = {},
                    contentDescription = "Favorite"
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = null, tint = CanvasKitTheme.colors.contentColor)
                }
                CanvasKitIconButton(
                    onClick = {},
                    loading = true,
                    contentDescription = "Favorite"
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = null, tint = CanvasKitTheme.colors.contentColor)
                }
                CanvasKitIconButton(
                    onClick = {},
                    backgroundColor = CanvasKitTheme.colors.brandAccent,
                    contentColor = CanvasKitTheme.colors.backgroundPrimary,
                    contentDescription = "Add"
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = CanvasKitTheme.colors.contentColor)
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitButtonLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box {
            ButtonsPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitButtonDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box {
            ButtonsPreviewContainer()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
fun CanvasKitButtonRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box {
                ButtonsPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
fun CanvasKitButtonFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            ButtonsPreviewContainer()
        }
    }
}
