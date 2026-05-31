package es.joshluq.canvaskit.components.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

// ---------------------------------------------------------------------------
// Preview helpers
// ---------------------------------------------------------------------------

@Composable
private fun AllBannersPreviewContent() {
    val colors = CanvasKitTheme.colors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CanvasKitBanner(
            variant = CanvasKitAlertVariant.Info,
            visible = true,
            title = {
                Text(
                    text = "New version available",
                    style = CanvasKitTheme.typography.labelLarge,
                    color = colors.brandAccent
                )
            },
            message = {
                Text(
                    text = "Update to get the latest system improvements.",
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textPrimary
                )
            },
            onDismiss = {}
        )
        CanvasKitBanner(
            variant = CanvasKitAlertVariant.Success,
            visible = true,
            title = {
                Text(
                    text = "Changes saved",
                    style = CanvasKitTheme.typography.labelLarge,
                    color = colors.success
                )
            },
            message = {
                Text(
                    text = "Your settings were applied successfully.",
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textPrimary
                )
            },
            onDismiss = {}
        )
        CanvasKitBanner(
            variant = CanvasKitAlertVariant.Warning,
            visible = true,
            title = {
                Text(
                    text = "Unstable connection",
                    style = CanvasKitTheme.typography.labelLarge,
                    color = colors.warning
                )
            },
            message = {
                Text(
                    text = "Some data might not be synchronized.",
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textPrimary
                )
            },
            onDismiss = {}
        )
        CanvasKitBanner(
            variant = CanvasKitAlertVariant.Error,
            visible = true,
            title = {
                Text(
                    text = "Error loading",
                    style = CanvasKitTheme.typography.labelLarge,
                    color = colors.error
                )
            },
            message = {
                Text(
                    text = "The request could not be processed. Try again.",
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textPrimary
                )
            },
            onDismiss = {}
        )
    }
}

@Composable
private fun AllInlineAlertsPreviewContent() {
    val colors = CanvasKitTheme.colors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CanvasKitInlineAlert(
            variant = CanvasKitAlertVariant.Info,
            message = {
                Text(
                    text = "Changes will be applied in the next sync cycle.",
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textPrimary
                )
            }
        )
        CanvasKitInlineAlert(
            variant = CanvasKitAlertVariant.Success,
            message = {
                Text(
                    text = "File uploaded successfully.",
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textPrimary
                )
            }
        )
        CanvasKitInlineAlert(
            variant = CanvasKitAlertVariant.Warning,
            message = {
                Text(
                    text = "This action cannot be undone.",
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textPrimary
                )
            }
        )
        CanvasKitInlineAlert(
            variant = CanvasKitAlertVariant.Error,
            message = {
                Text(
                    text = "Email field is required.",
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textPrimary
                )
            }
        )
    }
}

// ---------------------------------------------------------------------------
// Banners Previews
// ---------------------------------------------------------------------------

@Preview(name = "Banners — Light Mode", showBackground = true)
@Composable
fun CanvasKitBannersLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        AllBannersPreviewContent()
    }
}

@Preview(name = "Banners — Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitBannersDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
            AllBannersPreviewContent()
        }
    }
}

@Preview(name = "Banners — RTL", showBackground = true)
@Composable
fun CanvasKitBannersRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            AllBannersPreviewContent()
        }
    }
}

@Preview(name = "Banners — Large Font (1.5x)", showBackground = true, fontScale = 1.5f)
@Composable
fun CanvasKitBannersFontScalePreview() {
    CanvasKitTheme {
        AllBannersPreviewContent()
    }
}

// ---------------------------------------------------------------------------
// Inline Alerts Previews
// ---------------------------------------------------------------------------

@Preview(name = "InlineAlert — Light Mode", showBackground = true)
@Composable
fun CanvasKitInlineAlertLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        AllInlineAlertsPreviewContent()
    }
}

@Preview(name = "InlineAlert — Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitInlineAlertDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
            AllInlineAlertsPreviewContent()
        }
    }
}

@Preview(name = "InlineAlert — RTL", showBackground = true)
@Composable
fun CanvasKitInlineAlertRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            AllInlineAlertsPreviewContent()
        }
    }
}

@Preview(name = "InlineAlert — Large Font (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
fun CanvasKitInlineAlertFontScalePreview() {
    CanvasKitTheme {
        AllInlineAlertsPreviewContent()
    }
}
