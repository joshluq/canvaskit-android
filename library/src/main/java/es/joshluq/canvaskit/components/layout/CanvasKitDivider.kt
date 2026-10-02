package es.joshluq.canvaskit.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitDivider renders a subtle horizontal hairline separator line.
 *
 * It is commonly used between list rows, inside cards, or to delineate page sections.
 *
 * @param modifier Root layout modifier.
 * @param thickness Stroke thickness. Defaults to [CanvasKitStroke.thin].
 * @param color Line color. Defaults to [CanvasKitColors.borderSubtle].
 * @param startIndent Inset margin on the start/left edge. Useful for aligning with text rows.
 * @param endIndent Inset margin on the end/right edge.
 */
@Composable
fun CanvasKitDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = CanvasKitTheme.stroke.thin,
    color: Color = CanvasKitTheme.colors.borderSubtle,
    startIndent: Dp = 0.dp,
    endIndent: Dp = 0.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = startIndent, end = endIndent)
            .height(thickness)
            .background(color = color)
    )
}

/**
 * CanvasKitVerticalDivider renders a subtle vertical separator line.
 *
 * Commonly used inside toolbars, action rows, and segment dividers.
 *
 * @param modifier Root layout modifier.
 * @param thickness Stroke thickness. Defaults to [CanvasKitStroke.thin].
 * @param color Line color. Defaults to [CanvasKitColors.borderSubtle].
 * @param topIndent Inset margin on the top edge.
 * @param bottomIndent Inset margin on the bottom edge.
 */
@Composable
fun CanvasKitVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = CanvasKitTheme.stroke.thin,
    color: Color = CanvasKitTheme.colors.borderSubtle,
    topIndent: Dp = 0.dp,
    bottomIndent: Dp = 0.dp
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(top = topIndent, bottom = bottomIndent)
            .width(thickness)
            .background(color = color)
    )
}
