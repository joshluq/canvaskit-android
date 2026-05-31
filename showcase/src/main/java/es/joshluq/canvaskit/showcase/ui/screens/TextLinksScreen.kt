package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.components.text.CanvasKitLinkedText
import es.joshluq.canvaskit.components.text.CanvasKitRichText
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * TextLinksScreen showcases the two approaches for interactive links within text.
 */
@Composable
fun TextLinksScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Rich Text & Links",
                        style = typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Programmatic and tag-based interactive paragraphs.",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(
                    onClick = onBack,
                    contentDescription = "Back"
                ) { contentColor ->
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = contentColor
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {
            
            // Introduction Section
            Column(modifier = Modifier.padding(horizontal = spacing.xs)) {
                Text(
                    text = "Semantic\nConnectors",
                    style = typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Crafting interactive copy shouldn't be difficult. CanvasKit provides DSL and Tag-based systems for consistent linking.",
                    style = typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: DSL Approach
            SpecSectionCard(
                title = "Programmatic DSL",
                description = "Type-safe builder for developer-driven paragraphs."
            ) {
                CanvasKitRichText {
                    append("By proceeding, you agree to our ")
                    appendLink("Terms of Use") {
                        Toast.makeText(context, "Clicked: Terms of Use", Toast.LENGTH_SHORT).show()
                    }
                    append(" and our ")
                    appendLink("Cookie Management Policy") {
                        Toast.makeText(context, "Clicked: Cookies", Toast.LENGTH_SHORT).show()
                    }
                    append(". We value your privacy.")
                }
            }

            // Section: Tagged Approach
            SpecSectionCard(
                title = "Tag-Based Parser",
                description = "Ideal for strings from translation files or CMS."
            ) {
                CanvasKitLinkedText(
                    text = "Need more information? Visit our [help]Help Center[/link] or [support]Contact Support[/link] for 24/7 assistance.",
                    onLinkClick = { tag ->
                        Toast.makeText(context, "Link tag: $tag", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
