package es.joshluq.canvaskit.components.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun SamplePopupContent() {
    Column(modifier = Modifier.width(180.dp)) {
        Text(
            text = "Acciones rápidas",
            style = CanvasKitTheme.typography.labelSmall,
            color = CanvasKitTheme.colors.textSecondary,
            modifier = Modifier.padding(bottom = CanvasKitTheme.spacing.xs)
        )
        Text(
            text = "Editar perfil",
            style = CanvasKitTheme.typography.bodyMedium,
            color = CanvasKitTheme.colors.textPrimary,
            modifier = Modifier.padding(vertical = CanvasKitTheme.spacing.xxs)
        )
        Text(
            text = "Configuración",
            style = CanvasKitTheme.typography.bodyMedium,
            color = CanvasKitTheme.colors.textPrimary,
            modifier = Modifier.padding(vertical = CanvasKitTheme.spacing.xxs)
        )
        Text(
            text = "Cerrar sesión",
            style = CanvasKitTheme.typography.bodyMedium,
            color = CanvasKitTheme.colors.error,
            modifier = Modifier.padding(vertical = CanvasKitTheme.spacing.xxs)
        )
    }
}

@Composable
private fun PopupPreviewContainer() {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    Box(
        modifier = Modifier
            .clip(shapes.medium)
            .background(colors.backgroundSecondary)
            .border(width = 1.dp, color = colors.borderSubtle, shape = shapes.medium)
            .padding(spacing.sm)
    ) {
        SamplePopupContent()
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitPopupLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            PopupPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitPopupDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            PopupPreviewContainer()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
fun CanvasKitPopupRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.padding(16.dp)) {
                PopupPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
fun CanvasKitPopupFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            PopupPreviewContainer()
        }
    }
}
