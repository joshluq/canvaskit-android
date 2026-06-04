package es.joshluq.canvaskit.components.inputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun TogglesPreviewContainer() {
    val spacing = CanvasKitTheme.spacing
    val colors = CanvasKitTheme.colors
    val typography = CanvasKitTheme.typography

    var switchChecked1 by remember { mutableStateOf(true) }
    var switchChecked2 by remember { mutableStateOf(false) }

    var checkboxChecked1 by remember { mutableStateOf(true) }
    var checkboxChecked2 by remember { mutableStateOf(false) }

    var radioSelected1 by remember { mutableStateOf(true) }
    var radioSelected2 by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Switch section
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            Text(
                text = "Switches (Active, Inactive, Disabled States)",
                style = typography.labelLarge,
                color = colors.textSecondary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.md),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CanvasKitSwitch(checked = switchChecked1, onCheckedChange = { switchChecked1 = it })
                CanvasKitSwitch(checked = switchChecked2, onCheckedChange = { switchChecked2 = it })
                CanvasKitSwitch(checked = true, onCheckedChange = null, enabled = false)
                CanvasKitSwitch(checked = false, onCheckedChange = null, enabled = false)
            }
        }

        // Checkbox section
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            Text(
                text = "Checkboxes (Active, Inactive, Disabled States)",
                style = typography.labelLarge,
                color = colors.textSecondary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.md),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CanvasKitCheckbox(checked = checkboxChecked1, onCheckedChange = { checkboxChecked1 = it })
                CanvasKitCheckbox(checked = checkboxChecked2, onCheckedChange = { checkboxChecked2 = it })
                CanvasKitCheckbox(checked = true, onCheckedChange = null, enabled = false)
                CanvasKitCheckbox(checked = false, onCheckedChange = null, enabled = false)
            }
        }

        // RadioButton section
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            Text(
                text = "Radio Buttons (Active, Inactive, Disabled States)",
                style = typography.labelLarge,
                color = colors.textSecondary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.md),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CanvasKitRadioButton(selected = radioSelected1, onClick = {
                    radioSelected1 = true
                    radioSelected2 = false
                })
                CanvasKitRadioButton(selected = radioSelected2, onClick = {
                    radioSelected2 = true
                    radioSelected1 = false
                })
                CanvasKitRadioButton(selected = true, onClick = null, enabled = false)
                CanvasKitRadioButton(selected = false, onClick = null, enabled = false)
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
internal fun CanvasKitTogglesLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            TogglesPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasKitTogglesDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            TogglesPreviewContainer()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
internal fun CanvasKitTogglesRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.padding(16.dp)) {
                TogglesPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
internal fun CanvasKitTogglesFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            TogglesPreviewContainer()
        }
    }
}
