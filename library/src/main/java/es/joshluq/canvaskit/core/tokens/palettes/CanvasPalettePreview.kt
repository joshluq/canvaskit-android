package es.joshluq.canvaskit.core.tokens.palettes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Preview parameter provider supplying all [CanvasPalette] options.
 */
internal class CanvasPaletteParameterProvider : PreviewParameterProvider<CanvasPalette> {
    override val values: Sequence<CanvasPalette> = CanvasPalette.values.asSequence()
}

@Composable
private fun PaletteCard(
    palette: CanvasPalette,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shapes.container)
            .background(colors.backgroundPrimary)
            .border(1.dp, colors.borderSubtle, shapes.container)
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = palette.name,
                style = typography.headingMedium,
                color = colors.textPrimary
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(colors.brandAccent)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "Accent",
                    style = typography.labelSmall,
                    color = colors.onBrandAccent
                )
            }
        }

        Text(
            text = "Primary & Accent brand tokens alongside semantic status feedback.",
            style = typography.bodyMedium,
            color = colors.textSecondary
        )

        // Swatches
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(spacing.xs),
            verticalArrangement = Arrangement.spacedBy(spacing.xs)
        ) {
            ColorSwatch(name = "Primary", color = colors.brandPrimary, onColor = colors.backgroundPrimary)
            ColorSwatch(name = "Accent", color = colors.brandAccent, onColor = colors.onBrandAccent)
            ColorSwatch(name = "Success", color = colors.success, onColor = colors.backgroundPrimary)
            ColorSwatch(name = "Warning", color = colors.warning, onColor = colors.backgroundPrimary)
            ColorSwatch(name = "Error", color = colors.error, onColor = colors.backgroundPrimary)
        }
    }
}

@Composable
private fun ColorSwatch(
    name: String,
    color: androidx.compose.ui.graphics.Color,
    onColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 64.dp, height = 36.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            style = CanvasKitTheme.typography.labelSmall,
            color = onColor,
            maxLines = 1
        )
    }
}

@Composable
private fun AllPalettesPreviewContainer() {
    val spacing = CanvasKitTheme.spacing
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.md),
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.md)
    ) {
        CanvasPalette.values.forEach { palette ->
            CanvasKitTheme(palette = palette) {
                PaletteCard(palette = palette)
            }
        }
    }
}

@Preview(name = "Light Mode Matrix", showBackground = true)
@Composable
internal fun CanvasPaletteLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            AllPalettesPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode Matrix", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasPaletteDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            AllPalettesPreviewContainer()
        }
    }
}

@Preview(name = "Single Palette Provider", showBackground = true)
@Composable
internal fun CanvasPaletteSinglePreview(
    @PreviewParameter(CanvasPaletteParameterProvider::class) palette: CanvasPalette
) {
    CanvasKitTheme(palette = palette) {
        Box(modifier = Modifier.padding(16.dp)) {
            PaletteCard(palette = palette)
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
internal fun CanvasPaletteRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
                AllPalettesPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
internal fun CanvasPaletteFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            AllPalettesPreviewContainer()
        }
    }
}
