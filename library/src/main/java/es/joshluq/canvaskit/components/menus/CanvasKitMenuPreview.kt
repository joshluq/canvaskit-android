package es.joshluq.canvaskit.components.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun MenuPreviewContainer() {
    val spacing = CanvasKitTheme.spacing
    var expanded by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.lg)
    ) {
        Text(
            text = "Expressive Dropdown Menus",
            style = CanvasKitTheme.typography.headingMedium,
            color = CanvasKitTheme.colors.textPrimary
        )

        // Interactive Trigger (might not show popup in all preview renderers)
        Box {
            CanvasKitButton(onClick = { expanded = !expanded }) { contentColor ->
                Text("Toggle Menu", color = contentColor)
                Icon(
                    if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = contentColor
                )
            }

            CanvasKitDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                MenuItemsContent()
            }
        }

        Text(
            text = "Menu Item Styling (Static)",
            style = CanvasKitTheme.typography.labelLarge,
            color = CanvasKitTheme.colors.textSecondary
        )

        // Static visualization for preview purposes
        Column(
            modifier = Modifier
                .width(280.dp)
                .shadow(8.dp, CanvasKitTheme.shapes.large)
                .background(CanvasKitTheme.colors.backgroundPrimary, CanvasKitTheme.shapes.large)
                .border(0.5.dp, CanvasKitTheme.colors.borderSubtle, CanvasKitTheme.shapes.large)
        ) {
            MenuItemsContent()
        }
    }
}

@Composable
private fun MenuItemsContent() {
    CanvasKitDropdownMenuItem(
        text = "Profile",
        onClick = { },
        leadingIcon = {
            Icon(Icons.Default.Person, contentDescription = null)
        }
    )
    CanvasKitDropdownMenuItem(
        text = "Settings",
        onClick = { },
        leadingIcon = {
            Icon(Icons.Default.Settings, contentDescription = null)
        }
    )
    CanvasKitDropdownMenuItem(
        text = "External Link",
        onClick = { },
        leadingIcon = {
            Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null)
        },
        trailingContent = {
            Text(
                text = "PRO",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.brandAccent
            )
        }
    )
    CanvasKitDropdownMenuItem(
        text = "Logout",
        onClick = { },
        leadingIcon = {
            Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
        },
        enabled = false
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
internal fun CanvasKitMenuLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box {
            MenuPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasKitMenuDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box {
            MenuPreviewContainer()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
internal fun CanvasKitMenuRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box {
                MenuPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
internal fun CanvasKitMenuFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            MenuPreviewContainer()
        }
    }
}
