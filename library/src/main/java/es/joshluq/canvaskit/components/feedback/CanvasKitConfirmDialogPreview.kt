package es.joshluq.canvaskit.components.feedback

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun CanvasKitConfirmDialogPreview() {
    CanvasKitTheme {
        CanvasKitConfirmDialog(
            title = "Delete Record",
            message = "Are you sure you want to permanently delete this vehicle? This action cannot be undone.",
            confirmText = "Delete",
            onConfirm = {},
            onDismissRequest = {},
            icon = Icons.Default.Delete,
            isDestructive = true
        )
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun CanvasKitConfirmDialogDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        CanvasKitConfirmDialog(
            title = "Discard Changes",
            message = "You have unsaved changes. Do you want to discard them before leaving?",
            confirmText = "Discard",
            onConfirm = {},
            onDismissRequest = {},
            icon = Icons.Default.Warning
        )
    }
}
