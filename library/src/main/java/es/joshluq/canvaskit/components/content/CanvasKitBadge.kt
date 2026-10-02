package es.joshluq.canvaskit.components.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitBadge is a compact notification indicator or counter.
 *
 * It supports count numbers (e.g., "3", "99+"), status dots, or short label tags (e.g., "NEW").
 *
 * ### Key Features:
 * - **Pure Motion Transitions:** Pops in elastically with [Spring.DampingRatioLowBouncy].
 * - **Smart Truncation:** Automatically appends "+" when count exceeds [maxCount].
 * - **A11y Compliant:** Informs screen readers of unread count status.
 *
 * @param modifier Root layout modifier.
 * @param count Numeric count to display. When null and [text] is null, renders a discreet status dot.
 * @param text Optional short text tag (e.g., "PRO", "NEW").
 * @param maxCount Threshold above which the count displays "$maxCount+". Defaults to 99.
 * @param containerColor Background color. Defaults to [CanvasKitColors.error].
 * @param contentColor Text/icon color. Defaults to [CanvasKitColors.backgroundPrimary].
 * @param visible Controls whether the badge is displayed. Drives spring entry/exit animations.
 */
@Composable
fun CanvasKitBadge(
    modifier: Modifier = Modifier,
    count: Int? = null,
    text: String? = null,
    maxCount: Int = 99,
    containerColor: Color = CanvasKitTheme.colors.error,
    contentColor: Color = CanvasKitTheme.colors.backgroundPrimary,
    visible: Boolean = true
) {
    val shapes = CanvasKitTheme.shapes
    val typography = CanvasKitTheme.typography
    val spacing = CanvasKitTheme.spacing

    val displayString = when {
        text != null -> text
        count != null -> if (count > maxCount) "$maxCount+" else count.toString()
        else -> null
    }

    val isDot = displayString == null

    val springSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMedium
    )

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + scaleIn(springSpec),
        exit = fadeOut() + scaleOut(),
        modifier = modifier
    ) {
        if (isDot) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(shapes.pill)
                    .background(containerColor)
                    .semantics {
                        this.contentDescription = "New notification"
                    }
            )
        } else {
            Box(
                modifier = Modifier
                    .defaultMinSize(minWidth = 18.dp, minHeight = 18.dp)
                    .clip(shapes.pill)
                    .background(containerColor)
                    .padding(horizontal = spacing.xxs, vertical = 1.dp)
                    .semantics {
                        this.contentDescription = "$displayString notifications"
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = displayString.orEmpty(),
                    style = typography.labelSmall.copy(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = contentColor
                )
            }
        }
    }
}

/**
 * CanvasKitBadgedBox wraps an anchor element (like an Icon or Avatar) and anchors a [CanvasKitBadge]
 * to its top-end corner with precise offset alignment.
 *
 * @param badge Slot for the [CanvasKitBadge].
 * @param modifier Root layout modifier.
 * @param content The main content over which the badge will be displayed (e.g. an icon or avatar).
 */
@Composable
fun CanvasKitBadgedBox(
    badge: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        propagateMinConstraints = false
    ) {
        content()
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset { IntOffset(x = 6.dp.roundToPx(), y = (-4).dp.roundToPx()) }
        ) {
            badge()
        }
    }
}
