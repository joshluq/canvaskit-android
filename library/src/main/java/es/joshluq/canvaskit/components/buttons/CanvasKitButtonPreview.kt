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
            FlowRow(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                CanvasKitButton(onClick = {}) { contentColor ->
                    Text(
                        text = "Primary",
                        color = contentColor
                    )
                }
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Secondary
                ) { contentColor ->
                    Text(
                        text = "Secondary",
                        color = contentColor
                    )
                }
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Ghost
                ) { contentColor ->
                    Text(
                        text = "Ghost",
                        color = contentColor
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
            FlowRow(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Primary,
                    enabled = false
                ) { contentColor ->
                    Text(
                        text = "Disabled",
                        color = contentColor
                    )
                }
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Secondary,
                    enabled = false
                ) { contentColor ->
                    Text(
                        text = "Disabled",
                        color = contentColor
                    )
                }
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Ghost,
                    enabled = false
                ) { contentColor ->
                    Text(
                        text = "Disabled",
                        color = contentColor
                    )
                }
            }
            FlowRow(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Primary,
                    loading = true
                ) { contentColor ->
                    Text(
                        text = "Loading",
                        color = contentColor
                    )
                }
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Secondary,
                    loading = true
                ) { contentColor ->
                    Text(
                        text = "Loading",
                        color = contentColor
                    )
                }
                CanvasKitButton(
                    onClick = {},
                    variant = CanvasKitButtonVariant.Ghost,
                    loading = true
                ) { contentColor ->
                    Text(
                        text = "Loading",
                        color = contentColor
                    )
                }
            }
        }

        // With Icons
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            Text(
                text = "Buttons with Icons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            FlowRow(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                CanvasKitButton(onClick = {}) { contentColor ->
                    Icon(Icons.Default.Add, contentDescription = null, tint = contentColor)
                    Text(
                        text = "Add Item",
                        color = contentColor
                    )
                }
                CanvasKitButton(onClick = {}, variant = CanvasKitButtonVariant.Secondary) { contentColor ->
                    Text(text = "Continue", color = contentColor)
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = contentColor)
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
                ) { contentColor ->
                    Icon(Icons.Default.Favorite, contentDescription = null, tint = contentColor)
                }
                CanvasKitIconButton(
                    onClick = {},
                    loading = true,
                    contentDescription = "Favorite"
                ) { contentColor ->
                    Icon(Icons.Default.Favorite, contentDescription = null, tint = contentColor)
                }
                CanvasKitIconButton(
                    onClick = {},
                    backgroundColor = CanvasKitTheme.colors.brandAccent,
                    contentColor = CanvasKitTheme.colors.onBrandAccent,
                    contentDescription = "Add"
                ) { contentColor ->
                    Icon(Icons.Default.Add, contentDescription = null, tint = contentColor)
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
internal fun CanvasKitButtonLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box {
            ButtonsPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasKitButtonDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box {
            ButtonsPreviewContainer()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
internal fun CanvasKitButtonRtlPreview() {
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
internal fun CanvasKitButtonFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            ButtonsPreviewContainer()
        }
    }
}
