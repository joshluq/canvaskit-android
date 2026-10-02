package es.joshluq.canvaskit.components.navigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun PagerIndicatorPreviewContent() {
    var page1 by remember { mutableIntStateOf(1) }
    var page2 by remember { mutableIntStateOf(2) }

    CanvasKitTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundPrimary)
                .padding(CanvasKitTheme.spacing.md),
            verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Worm Variant (Clickable)",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitPagerIndicator(
                pageCount = 5,
                currentPage = page1,
                variant = CanvasKitPagerIndicatorVariant.Worm,
                onPageClick = { page1 = it }
            )

            Text(
                text = "Dots Variant",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitPagerIndicator(
                pageCount = 5,
                currentPage = page2,
                variant = CanvasKitPagerIndicatorVariant.Dots,
                onPageClick = { page2 = it }
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun CanvasKitPagerIndicatorLightPreview() {
    PagerIndicatorPreviewContent()
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CanvasKitPagerIndicatorDarkPreview() {
    PagerIndicatorPreviewContent()
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
private fun CanvasKitPagerIndicatorRtlPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        PagerIndicatorPreviewContent()
    }
}

@Preview(name = "2.0x Font Scaling", showBackground = true, fontScale = 2.0f)
@Composable
private fun CanvasKitPagerIndicatorFontScalePreview() {
    PagerIndicatorPreviewContent()
}
