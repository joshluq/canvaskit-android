package es.joshluq.canvaskit.components.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitLinkedText parses a string with [tag]text[/link] format and converts it
 * into interactive rich text.
 *
 * @param text The source string containing tags like "[tos]Terms of Service[/link]".
 * @param onLinkClick Callback triggered when a tagged link is clicked, receiving the tag ID.
 * @param modifier Root layout modifier.
 * @param style Base text style for the paragraph.
 * @param color Base text color.
 */
@Composable
fun CanvasKitLinkedText(
    text: String,
    onLinkClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = CanvasKitTheme.typography.bodyLarge,
    color: Color = CanvasKitTheme.colors.textPrimary
) {
    // Regex to match [tag]content[/link]
    val linkRegex = remember { Regex("\\[(.*?)\\](.*?)\\[/link\\]") }

    CanvasKitRichText(
        modifier = modifier,
        style = style,
        color = color
    ) {
        var lastIndex = 0
        linkRegex.findAll(text).forEach { matchResult ->
            // Append text before the match
            val preText = text.substring(lastIndex, matchResult.range.first)
            if (preText.isNotEmpty()) {
                append(preText)
            }

            // Extract tag and content
            val tag = matchResult.groupValues[1]
            val content = matchResult.groupValues[2]

            // Append as link
            appendLink(content) {
                onLinkClick(tag)
            }

            lastIndex = matchResult.range.last + 1
        }

        // Append remaining text
        if (lastIndex < text.length) {
            append(text.substring(lastIndex))
        }
    }
}
