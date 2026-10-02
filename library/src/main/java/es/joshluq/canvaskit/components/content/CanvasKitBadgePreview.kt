package es.joshluq.canvaskit.components.content

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun BadgePreviewContent() {
    CanvasKitTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundPrimary)
                .padding(CanvasKitTheme.spacing.md),
            verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg)
        ) {
            Text(
                text = "Standalone Badges",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.md),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Dot
                CanvasKitBadge()

                // Count
                CanvasKitBadge(count = 5)

                // High count with 99+
                CanvasKitBadge(count = 120)

                // Custom Text Tag
                CanvasKitBadge(
                    text = "NEW",
                    containerColor = CanvasKitTheme.colors.brandAccent
                )
            }

            Text(
                text = "Badged Icons (BadgedBox)",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.xl),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bell with Dot
                CanvasKitBadgedBox(
                    badge = { CanvasKitBadge() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = CanvasKitTheme.colors.textPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Email with count 3
                CanvasKitBadgedBox(
                    badge = { CanvasKitBadge(count = 3) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Inbox",
                        tint = CanvasKitTheme.colors.textPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Cart with 99+
                CanvasKitBadgedBox(
                    badge = { CanvasKitBadge(count = 100) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = CanvasKitTheme.colors.textPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun CanvasKitBadgeLightPreview() {
    BadgePreviewContent()
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CanvasKitBadgeDarkPreview() {
    BadgePreviewContent()
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
private fun CanvasKitBadgeRtlPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        BadgePreviewContent()
    }
}

@Preview(name = "2.0x Font Scaling", showBackground = true, fontScale = 2.0f)
@Composable
private fun CanvasKitBadgeFontScalePreview() {
    BadgePreviewContent()
}
