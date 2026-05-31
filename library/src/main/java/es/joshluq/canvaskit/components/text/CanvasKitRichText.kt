package es.joshluq.canvaskit.components.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * DSL Builder for creating rich text with interactive links.
 */
class CanvasKitRichTextBuilder(
    private val builder: AnnotatedString.Builder,
    private val linkStyle: SpanStyle
) {
    /**
     * Appends normal text to the paragraph.
     */
    fun append(text: String) {
        builder.append(text)
    }

    /**
     * Appends a clickable link with artisanal styling.
     *
     * @param text The visible text for the link.
     * @param onClick Callback triggered when the link is clicked.
     */
    fun appendLink(text: String, onClick: () -> Unit) {
        builder.withLink(
            link = LinkAnnotation.Clickable(
                tag = text, // Used as a unique identifier for the link session
                styles = TextLinkStyles(style = linkStyle),
                linkInteractionListener = { onClick() }
            )
        ) {
            append(text)
        }
    }
}

/**
 * CanvasKitRichText allows building paragraphs with embedded interactive links using a DSL.
 * It follows the "Pure Motion" philosophy with high-end accessibility via [LinkAnnotation].
 *
 * @param modifier Root layout modifier.
 * @param style Base text style for the paragraph.
 * @param color Base text color.
 * @param builder DSL block to construct the rich content.
 */
@Composable
fun CanvasKitRichText(
    modifier: Modifier = Modifier,
    style: TextStyle = CanvasKitTheme.typography.bodyLarge,
    color: Color = CanvasKitTheme.colors.textPrimary,
    builder: CanvasKitRichTextBuilder.() -> Unit
) {
    val colors = CanvasKitTheme.colors

    // Artisanal link styling
    val linkStyle = remember(colors.brandAccent) {
        SpanStyle(
            color = colors.brandAccent,
            fontWeight = FontWeight.Medium,
            textDecoration = TextDecoration.Underline
        )
    }

    val annotatedString = remember(builder, linkStyle) {
        buildAnnotatedString {
            val richBuilder = CanvasKitRichTextBuilder(this, linkStyle)
            richBuilder.builder()
        }
    }

    BasicText(
        text = annotatedString,
        modifier = modifier,
        style = style.copy(color = color)
    )
}
