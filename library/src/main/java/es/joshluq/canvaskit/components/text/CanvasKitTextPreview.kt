package es.joshluq.canvaskit.components.text

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun TextLinksPreviewContainer() {
    val context = LocalContext.current
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.xl)
    ) {
        // DSL Approach
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            Text(
                text = "DSL Builder (Programmatic)",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitRichText {
                append("By creating an account, you agree to our ")
                appendLink("User Agreement") {
                    Toast.makeText(context, "User Agreement Clicked", Toast.LENGTH_SHORT).show()
                }
                append(" and acknowledge our ")
                appendLink("Global Privacy Policy") {
                    Toast.makeText(context, "Privacy Policy Clicked", Toast.LENGTH_SHORT).show()
                }
                append(".")
            }
        }

        // Tagged Approach
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            Text(
                text = "Tagged Parser (Dynamic/CMS)",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitLinkedText(
                text = "Need help? Visit our [help]Support Center[/link] or [contact]Contact Us[/link] directly.",
                onLinkClick = { tag ->
                    Toast.makeText(context, "Tag clicked: $tag", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitTextLinksLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        TextLinksPreviewContainer()
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitTextLinksDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        TextLinksPreviewContainer()
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
fun CanvasKitTextLinksRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            TextLinksPreviewContainer()
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
fun CanvasKitTextLinksFontScalePreview() {
    CanvasKitTheme {
        TextLinksPreviewContainer()
    }
}
