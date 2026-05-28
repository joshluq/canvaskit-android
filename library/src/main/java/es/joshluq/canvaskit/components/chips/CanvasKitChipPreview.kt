package es.joshluq.canvaskit.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun SampleChips() {
    val colors = CanvasKitTheme.colors
    val typography = CanvasKitTheme.typography

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Primary
        Text("Primary Variant", style = typography.labelLarge, color = colors.textSecondary)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CanvasKitChip(onClick = {}, variant = CanvasKitChipVariant.Primary, selected = false, label = {
                Text("Default", style = typography.labelLarge, color = colors.textPrimary)
            })
            CanvasKitChip(onClick = {}, variant = CanvasKitChipVariant.Primary, selected = true, label = {
                Text("Selected", style = typography.labelLarge, color = colors.backgroundPrimary)
            })
            CanvasKitChip(onClick = {}, variant = CanvasKitChipVariant.Primary, enabled = false, label = {
                Text("Disabled", style = typography.labelLarge, color = colors.textPrimary)
            })
        }

        // Outlined
        Text("Outlined Variant", style = typography.labelLarge, color = colors.textSecondary)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CanvasKitChip(onClick = {}, variant = CanvasKitChipVariant.Outlined, selected = false, label = {
                Text("Default", style = typography.labelLarge, color = colors.textPrimary)
            })
            CanvasKitChip(onClick = {}, variant = CanvasKitChipVariant.Outlined, selected = true, label = {
                Text("Selected", style = typography.labelLarge, color = colors.brandAccent)
            })
            CanvasKitChip(onClick = {}, variant = CanvasKitChipVariant.Outlined, enabled = false, label = {
                Text("Disabled", style = typography.labelLarge, color = colors.textPrimary)
            })
        }

        // Ghost
        Text("Ghost Variant", style = typography.labelLarge, color = colors.textSecondary)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CanvasKitChip(onClick = {}, variant = CanvasKitChipVariant.Ghost, selected = false, label = {
                Text("Default", style = typography.labelLarge, color = colors.textSecondary)
            })
            CanvasKitChip(onClick = {}, variant = CanvasKitChipVariant.Ghost, selected = true, label = {
                Text("Selected", style = typography.labelLarge, color = colors.brandAccent)
            })
            CanvasKitChip(onClick = {}, variant = CanvasKitChipVariant.Ghost, enabled = false, label = {
                Text("Disabled", style = typography.labelLarge, color = colors.textSecondary)
            })
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Icons
        Text("With Icons", style = typography.labelLarge, color = colors.textSecondary)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CanvasKitChip(
                onClick = {},
                variant = CanvasKitChipVariant.Primary,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = colors.textPrimary)
                },
                label = {
                    Text("Filter", style = typography.labelLarge, color = colors.textPrimary)
                }
            )

            CanvasKitChip(
                onClick = {},
                variant = CanvasKitChipVariant.Outlined,
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = colors.textPrimary)
                },
                label = {
                    Text("Dismiss", style = typography.labelLarge, color = colors.textPrimary)
                }
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitChipLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
            SampleChips()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitChipDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
            SampleChips()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
fun CanvasKitChipRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
                SampleChips()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
fun CanvasKitChipFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
            SampleChips()
        }
    }
}
