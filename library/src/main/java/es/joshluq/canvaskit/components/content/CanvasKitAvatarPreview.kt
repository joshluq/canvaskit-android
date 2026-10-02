package es.joshluq.canvaskit.components.content

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.LayoutDirection
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

private val sampleUsers = listOf(
    CanvasKitAvatarData(id = "1", name = "Josh Luque"),
    CanvasKitAvatarData(id = "2", name = "Alex Morgan"),
    CanvasKitAvatarData(id = "3", name = "Sarah Connor"),
    CanvasKitAvatarData(id = "4", name = "David Miller"),
    CanvasKitAvatarData(id = "5", name = "Elena Rostova"),
    CanvasKitAvatarData(id = "6", name = "Carlos Santana")
)

@Composable
private fun AvatarPreviewContent() {
    CanvasKitTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundPrimary)
                .padding(CanvasKitTheme.spacing.md),
            verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg)
        ) {
            Text(
                text = "Avatar Sizes (Initials Fallback)",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.md),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CanvasKitAvatar(size = CanvasKitAvatarSize.Small, name = "JL")
                CanvasKitAvatar(size = CanvasKitAvatarSize.Medium, name = "Josh Luque")
                CanvasKitAvatar(size = CanvasKitAvatarSize.Large, name = "Alex Morgan")
                CanvasKitAvatar(size = CanvasKitAvatarSize.XLarge, name = "Sarah Connor")
            }

            Text(
                text = "Presence Indicators",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.md),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CanvasKitAvatar(
                    size = CanvasKitAvatarSize.Medium,
                    name = "Josh Luque",
                    presence = CanvasKitAvatarPresence.Online
                )
                CanvasKitAvatar(
                    size = CanvasKitAvatarSize.Medium,
                    name = "Alex Morgan",
                    presence = CanvasKitAvatarPresence.Busy
                )
                CanvasKitAvatar(
                    size = CanvasKitAvatarSize.Medium,
                    name = "Sarah Connor",
                    presence = CanvasKitAvatarPresence.Away
                )
                CanvasKitAvatar(
                    size = CanvasKitAvatarSize.Medium,
                    name = "David Miller",
                    presence = CanvasKitAvatarPresence.Offline
                )
            }

            Text(
                text = "Avatar Group (Overlapping with +2 Overflow)",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitAvatarGroup(
                avatars = sampleUsers,
                size = CanvasKitAvatarSize.Medium,
                maxVisible = 4
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun CanvasKitAvatarLightPreview() {
    AvatarPreviewContent()
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CanvasKitAvatarDarkPreview() {
    AvatarPreviewContent()
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
private fun CanvasKitAvatarRtlPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        AvatarPreviewContent()
    }
}

@Preview(name = "2.0x Font Scaling", showBackground = true, fontScale = 2.0f)
@Composable
private fun CanvasKitAvatarFontScalePreview() {
    AvatarPreviewContent()
}
