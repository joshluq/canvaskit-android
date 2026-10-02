package es.joshluq.canvaskit.components.lists

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
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
private fun SwipeToDismissPreviewContent() {
    CanvasKitTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundPrimary)
                .padding(CanvasKitTheme.spacing.md),
            verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg)
        ) {
            Text(
                text = "Swipe to Dismiss / Action Row",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )

            CanvasKitSwipeToDismissBox(
                onDismiss = {},
                backgroundContent = { direction ->
                    val isDelete = direction == CanvasKitDismissDirection.EndToStart
                    val bgColor = if (isDelete) CanvasKitTheme.colors.error else CanvasKitTheme.colors.brandAccent

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(bgColor)
                            .padding(horizontal = CanvasKitTheme.spacing.md),
                        horizontalArrangement = if (isDelete) Arrangement.End else Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (isDelete) Icons.Default.Delete else Icons.Default.Archive,
                            contentDescription = null,
                            tint = CanvasKitTheme.colors.backgroundPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            ) {
                CanvasKitListItem(
                    headline = { Text("Swipe Me Left or Right", color = CanvasKitTheme.colors.textPrimary) },
                    supportingText = { Text("Reveals Archive or Delete action", color = CanvasKitTheme.colors.textSecondary) },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = CanvasKitTheme.colors.brandAccent
                        )
                    },
                    modifier = Modifier.background(CanvasKitTheme.colors.backgroundSecondary)
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun CanvasKitSwipeToDismissBoxLightPreview() {
    SwipeToDismissPreviewContent()
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CanvasKitSwipeToDismissBoxDarkPreview() {
    SwipeToDismissPreviewContent()
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
private fun CanvasKitSwipeToDismissBoxRtlPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        SwipeToDismissPreviewContent()
    }
}

@Preview(name = "2.0x Font Scaling", showBackground = true, fontScale = 2.0f)
@Composable
private fun CanvasKitSwipeToDismissBoxFontScalePreview() {
    SwipeToDismissPreviewContent()
}
