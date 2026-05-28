package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Reusable section card layout container for the CanvasKit showcase spec sheets.
 * Optimized for the "Artisanal Precision" aesthetic with 24dp rounding and card-on-card structure.
 */
@Composable
fun SpecSectionCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shapes.container)
            .background(colors.backgroundPrimary)
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        Column {
            Text(
                text = title,
                style = CanvasKitTheme.typography.headingMedium,
                color = colors.textPrimary
            )
            Text(
                text = description,
                style = CanvasKitTheme.typography.bodyMedium,
                color = colors.textSecondary
            )
        }
        
        Box(modifier = Modifier.fillMaxWidth()) {
            content()
        }
    }
}
