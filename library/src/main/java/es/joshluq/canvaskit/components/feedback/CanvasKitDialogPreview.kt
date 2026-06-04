package es.joshluq.canvaskit.components.feedback

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun SampleDialogContent() {
    CanvasKitDialogContent(
        title = {
            Text(
                text = "Eliminar archivo permanentemente",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
        },
        content = {
            Text(
                text = "¿Estás seguro de que deseas eliminar este archivo? Esta acción no se puede deshacer y perderás el progreso no guardado.",
                style = CanvasKitTheme.typography.bodyMedium,
                color = CanvasKitTheme.colors.textSecondary
            )
        },
        buttons = {
            CanvasKitButton(
                onClick = {},
                variant = CanvasKitButtonVariant.Ghost
            ) {
                Text(
                    text = "Cancelar",
                    style = CanvasKitTheme.typography.labelLarge,
                    color = CanvasKitTheme.colors.brandAccent
                )
            }
            CanvasKitButton(
                onClick = {}
            ) {
                Text(
                    text = "Eliminar",
                    style = CanvasKitTheme.typography.labelLarge,
                    color = CanvasKitTheme.colors.backgroundPrimary
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
internal fun CanvasKitDialogContentLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            SampleDialogContent()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasKitDialogContentDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            SampleDialogContent()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
internal fun CanvasKitDialogContentRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.padding(16.dp)) {
                SampleDialogContent()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
internal fun CanvasKitDialogContentFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            SampleDialogContent()
        }
    }
}
