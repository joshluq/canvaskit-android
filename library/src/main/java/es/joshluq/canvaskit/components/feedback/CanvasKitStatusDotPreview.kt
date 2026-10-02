package es.joshluq.canvaskit.components.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

internal class StatusDotVariantProvider : PreviewParameterProvider<CanvasKitStatusDotVariant> {
    override val values: Sequence<CanvasKitStatusDotVariant> =
        CanvasKitStatusDotVariant.entries.asSequence()
}

@Composable
private fun StatusDotPreviewContainer() {
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography
    val colors = CanvasKitTheme.colors

    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.md),
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.md)
    ) {
        Text(
            text = "Tactical Status Dots",
            style = typography.headingMedium,
            color = colors.textPrimary
        )

        // Static Ambient Halos
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            CanvasKitStatusDotVariant.entries.forEach { variant ->
                CanvasKitStatusDot(
                    variant = variant,
                    label = {
                        Text(
                            text = "${variant.name} (Static Halo)",
                            style = typography.bodyMedium,
                            color = colors.textPrimary
                        )
                    }
                )
            }
        }

        // Radar Pulse Active
        Text(
            text = "Active Radar Pulse",
            style = typography.labelLarge,
            color = colors.textSecondary
        )
        CanvasKitStatusDot(
            variant = CanvasKitStatusDotVariant.Success,
            animatePulse = true,
            label = {
                Text(
                    text = "System Operational (Live Radar Pulse)",
                    style = typography.bodyMedium,
                    color = colors.textPrimary
                )
            }
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
internal fun CanvasKitStatusDotLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            StatusDotPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasKitStatusDotDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            StatusDotPreviewContainer()
        }
    }
}

@Preview(name = "Single Variant Parameter Provider", showBackground = true)
@Composable
internal fun CanvasKitStatusDotSinglePreview(
    @PreviewParameter(StatusDotVariantProvider::class) variant: CanvasKitStatusDotVariant
) {
    CanvasKitTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            CanvasKitStatusDot(
                variant = variant,
                label = {
                    Text(
                        text = "Status: ${variant.name}",
                        style = CanvasKitTheme.typography.bodyMedium,
                        color = CanvasKitTheme.colors.textPrimary
                    )
                }
            )
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
internal fun CanvasKitStatusDotRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.padding(16.dp)) {
                StatusDotPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
internal fun CanvasKitStatusDotFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            StatusDotPreviewContainer()
        }
    }
}
