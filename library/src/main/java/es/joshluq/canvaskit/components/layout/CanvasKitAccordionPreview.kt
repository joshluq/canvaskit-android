package es.joshluq.canvaskit.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun AccordionPreviewContent() {
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(true) }
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography
    val colors = CanvasKitTheme.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        Text(
            text = "Accordion Variations",
            style = typography.headingMedium,
            color = colors.textPrimary,
            modifier = Modifier.padding(bottom = spacing.xs)
        )

        // FAQ Style
        CanvasKitAccordion(
            expanded = expanded1,
            onExpandedChange = { expanded1 = it },
            headline = { Text("What is Atelier design philosophy?", style = typography.bodyLarge) },
            leadingContent = { Icon(Icons.Default.Info, contentDescription = null, tint = colors.brandPrimary) }
        ) {
            Text(
                text = "Atelier is our proprietary design system focused on extreme aesthetic refinement, technical rigor, and native accessibility. It treats UI as a craft.",
                style = typography.bodyMedium,
                color = colors.textSecondary
            )
        }

        // Settings Style
        CanvasKitAccordion(
            expanded = expanded2,
            onExpandedChange = { expanded2 = it },
            headline = { Text("Privacy & Security", style = typography.bodyLarge) },
            supportingText = { Text("Manage authentication and data", style = typography.bodyMedium) },
            leadingContent = { Icon(Icons.Default.Lock, contentDescription = null, tint = colors.brandPrimary) },
            trailingContent = {
                Text(
                    text = "High",
                    style = typography.labelSmall,
                    color = colors.success
                )
            }
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                Text(
                    text = "Configure your encryption keys, biometric access, and data sharing preferences here.",
                    style = typography.bodyMedium,
                    color = colors.textSecondary
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitAccordionLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        AccordionPreviewContent()
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitAccordionDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        AccordionPreviewContent()
    }
}
