package es.joshluq.canvaskit.components.inputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeDown
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun SliderPreviewContainer() {
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography
    val colors = CanvasKitTheme.colors

    var value1 by remember { mutableFloatStateOf(0.3f) }
    var value2 by remember { mutableFloatStateOf(50f) }
    var value3 by remember { mutableFloatStateOf(0.7f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.xl)
    ) {
        // Continuous Slider
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            Text(
                text = "Continuous Slider (Value: ${(value1 * 100).toInt()}%)",
                style = typography.labelSmall,
                color = colors.textSecondary
            )
            CanvasKitSlider(
                value = value1,
                onValueChange = { value1 = it }
            )
        }

        // Discrete Slider with Icons
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            Text(
                text = "Discrete Slider with Icons",
                style = typography.labelSmall,
                color = colors.textSecondary
            )
            CanvasKitSlider(
                value = value2,
                onValueChange = { value2 = it },
                valueRange = 0f..100f,
                steps = 4,
                startIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeDown,
                        contentDescription = null,
                        tint = colors.textSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                },
                endIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = null,
                        tint = colors.textSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }

        // Disabled State
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            Text(
                text = "Disabled Slider",
                style = typography.labelSmall,
                color = colors.textSecondary
            )
            CanvasKitSlider(
                value = value3,
                onValueChange = { value3 = it },
                enabled = false
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitSliderLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        SliderPreviewContainer()
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitSliderDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        SliderPreviewContainer()
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
fun CanvasKitSliderRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            SliderPreviewContainer()
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
fun CanvasKitSliderFontScalePreview() {
    CanvasKitTheme {
        SliderPreviewContainer()
    }
}
