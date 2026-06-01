package es.joshluq.canvaskit.components.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.lists.CanvasKitListItem
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitAccordion is a premium molecular component for grouping and collapsing content.
 * It features a rich header based on [CanvasKitListItem] and organic spring-based animations.
 *
 * @param expanded Whether the accordion is currently open.
 * @param onExpandedChange Callback triggered when the expansion state changes.
 * @param headline The principal text of the header.
 * @param modifier Root layout modifier.
 * @param supportingText Optional secondary text below the headline.
 * @param leadingContent Optional leading slot for icons or category indicators.
 * @param trailingContent Optional trailing slot for metadata or status badges.
 * @param content The collapsible body content.
 */
@Composable
fun CanvasKitAccordion(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    headline: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    supportingText: (@Composable () -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    // Rotation animation for the expansion indicator
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = spring(),
        label = "AccordionChevronRotation"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shapes.container)
            .background(if (expanded) colors.backgroundSecondary.copy(alpha = 0.5f) else colors.backgroundPrimary)
            .semantics {
                stateDescription = if (expanded) "Expanded" else "Collapsed"
            }
    ) {
        // Header Row
        CanvasKitListItem(
            headline = headline,
            supportingText = supportingText,
            leadingContent = leadingContent,
            trailingContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = spacing.sm)
                ) {
                    if (trailingContent != null) {
                        trailingContent()
                        Spacer(modifier = Modifier.width(spacing.sm))
                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = colors.textSecondary.copy(alpha = 0.7f),
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(rotation)
                    )
                }
            },
            onClick = { onExpandedChange(!expanded) },
            modifier = Modifier.fillMaxWidth()
        )

        // Collapsible Content Body
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = spacing.md, end = spacing.md, bottom = spacing.md)
            ) {
                content()
            }
        }
    }
}
