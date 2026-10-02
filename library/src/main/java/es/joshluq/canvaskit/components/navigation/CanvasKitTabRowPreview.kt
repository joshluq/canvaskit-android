package es.joshluq.canvaskit.components.navigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun TabRowPreviewContent() {
    var selectedTab1 by remember { mutableIntStateOf(0) }
    var selectedTab2 by remember { mutableIntStateOf(1) }

    val tabs = listOf("Overview", "Analytics", "Settings")

    CanvasKitTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundPrimary)
                .padding(CanvasKitTheme.spacing.md),
            verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.xl)
        ) {
            // Underline Variant with icons and text
            Text(
                text = "Underline Variant",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitTabRow(
                selectedTabIndex = selectedTab1,
                tabsCount = tabs.size,
                indicatorVariant = CanvasKitTabIndicatorVariant.Underline
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTab1 == index
                    val icon = when (index) {
                        0 -> Icons.Default.Home
                        1 -> Icons.Default.Favorite
                        else -> Icons.Default.Person
                    }
                    CanvasKitTab(
                        selected = isSelected,
                        onClick = { selectedTab1 = index },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = if (isSelected) CanvasKitTheme.colors.brandAccent else CanvasKitTheme.colors.textSecondary,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        text = {
                            Text(
                                text = title,
                                style = CanvasKitTheme.typography.labelLarge.copy(
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                                ),
                                color = if (isSelected) CanvasKitTheme.colors.brandAccent else CanvasKitTheme.colors.textSecondary
                            )
                        }
                    )
                }
            }

            // Pill Variant (Secondary)
            Text(
                text = "Pill Variant",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitTabRow(
                selectedTabIndex = selectedTab2,
                tabsCount = tabs.size,
                indicatorVariant = CanvasKitTabIndicatorVariant.Pill
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTab2 == index
                    CanvasKitTab(
                        selected = isSelected,
                        onClick = { selectedTab2 = index },
                        modifier = Modifier.weight(1f),
                        text = {
                            Text(
                                text = title,
                                style = CanvasKitTheme.typography.labelLarge.copy(
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                                ),
                                color = if (isSelected) CanvasKitTheme.colors.brandPrimary else CanvasKitTheme.colors.textSecondary
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun CanvasKitTabRowLightPreview() {
    TabRowPreviewContent()
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CanvasKitTabRowDarkPreview() {
    TabRowPreviewContent()
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
private fun CanvasKitTabRowRtlPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        TabRowPreviewContent()
    }
}

@Preview(name = "2.0x Font Scaling", showBackground = true, fontScale = 2.0f)
@Composable
private fun CanvasKitTabRowFontScalePreview() {
    TabRowPreviewContent()
}
