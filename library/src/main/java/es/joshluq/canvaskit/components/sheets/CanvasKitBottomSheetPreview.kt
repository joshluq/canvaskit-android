package es.joshluq.canvaskit.components.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetPreviewContent() {
    val spacing = CanvasKitTheme.spacing
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.lg),
        verticalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        Text(
            text = "Expressive Bottom Sheet",
            style = CanvasKitTheme.typography.headingMedium,
            color = CanvasKitTheme.colors.textPrimary
        )
        
        Text(
            text = "This component follows the Atelier design philosophy with organic rounding and refined interactions.",
            style = CanvasKitTheme.typography.bodyLarge,
            color = CanvasKitTheme.colors.textSecondary
        )
        
        Spacer(modifier = Modifier.height(spacing.md))
        
        CanvasKitButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) { contentColor ->
            Text("Primary Action", color = contentColor)
        }
        
        Spacer(modifier = Modifier.height(spacing.xl))
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitBottomSheetLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        // We simulate the content since ModalBottomSheet requires a real context to render properly in Previews
        Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
            Column {
                CanvasKitDragHandle()
                BottomSheetPreviewContent()
            }
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitBottomSheetDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
            Column {
                CanvasKitDragHandle()
                BottomSheetPreviewContent()
            }
        }
    }
}
