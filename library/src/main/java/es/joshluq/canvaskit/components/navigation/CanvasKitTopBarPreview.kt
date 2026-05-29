package es.joshluq.canvaskit.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun SampleTopBarTitle(text: String) {
    Text(
        text = text,
        style = CanvasKitTheme.typography.headingMedium,
        color = CanvasKitTheme.colors.textPrimary
    )
}

@Composable
private fun SampleBackIcon() {
    CanvasKitIconButton(
        onClick = {},
        contentDescription = "Back"
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
    }
}

@Composable
private fun TopBarPreviewContainer() {
    val spacing = CanvasKitTheme.spacing
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.md),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Standard start-aligned TopBar
        CanvasKitTopBar(
            title = { SampleTopBarTitle("Settings") },
            navigationIcon = { SampleBackIcon() },
            actions = {
                CanvasKitIconButton(
                    onClick = {},
                    contentDescription = "Search"
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        )

        // Center-aligned TopBar
        CanvasKitTopBar(
            title = { SampleTopBarTitle("Edit Profile") },
            navigationIcon = { SampleBackIcon() },
            centeredTitle = true,
            actions = {
                CanvasKitIconButton(
                    onClick = {},
                    contentDescription = "Save"
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null
                    )
                }
                CanvasKitIconButton(
                    onClick = {},
                    contentDescription = "Settings"
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                }
            }
        )

        // Transparent TopBar with no border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.xs)
        ) {
            CanvasKitTopBar(
                title = { SampleTopBarTitle("Transparent View") },
                transparent = true,
                showBottomBorder = false
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitTopBarLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            TopBarPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitTopBarDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            TopBarPreviewContainer()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
fun CanvasKitTopBarRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.padding(16.dp)) {
                TopBarPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
fun CanvasKitTopBarFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            TopBarPreviewContainer()
        }
    }
}
