package es.joshluq.canvaskit.components.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import kotlin.math.roundToInt

/**
 * Item specification for [CanvasKitSegmentedControl].
 *
 * @param id Unique identifier for the segment.
 * @param label Text displayed inside the segment.
 * @param icon Optional leading icon.
 * @param enabled When false, this individual segment cannot be selected.
 */
@Immutable
data class CanvasKitSegmentItem(
    val id: String,
    val label: String,
    val icon: ImageVector? = null,
    val enabled: Boolean = true
)

/**
 * CanvasKitSegmentedControl is an artisanal segmented button switch following
 * Material 3 Expressive and iOS HIG designs. It features a sliding pill indicator
 * with physical spring dynamics and strict WCAG AA accessibility compliance.
 *
 * ### Key Features:
 * - **Spring Physics:** Active pill glides elastically between segments using [Spring.DampingRatioLowBouncy].
 * - **Touch Target Guarantee:** Outer container ensures a minimum 48dp touch region.
 * - **A11y Semantics:** Implements [Role.Tab], [Modifier.selectableGroup], and dynamic "X of Y" announcements.
 *
 * @param items List of segment items.
 * @param selectedIndex Index of the currently active segment.
 * @param onSegmentSelected Callback invoked when a segment is tapped.
 * @param modifier Root layout modifier.
 * @param enabled Whether the entire segmented control is interactive.
 */
@Composable
fun CanvasKitSegmentedControl(
    items: List<CanvasKitSegmentItem>,
    selectedIndex: Int,
    onSegmentSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    CanvasKitSegmentedControl(
        itemCount = items.size,
        selectedIndex = selectedIndex,
        onSegmentSelected = { index ->
            if (items[index].enabled) {
                onSegmentSelected(index)
            }
        },
        modifier = modifier,
        enabled = enabled,
        isItemEnabled = { index -> items[index].enabled }
    ) { index, isSelected ->
        val item = items[index]
        val colors = CanvasKitTheme.colors
        val typography = CanvasKitTheme.typography
        val spacing = CanvasKitTheme.spacing

        val textColor by animateColorAsState(
            targetValue = when {
                !item.enabled -> colors.textSecondary.copy(alpha = 0.5f)
                isSelected -> colors.brandPrimary
                else -> colors.textSecondary
            },
            label = "SegmentTextColor"
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = spacing.xs)
        ) {
            if (item.icon != null) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(16.dp)
                )
                Box(modifier = Modifier.width(spacing.xxs))
            }

            Text(
                text = item.label,
                style = typography.labelLarge.copy(
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                ),
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Slot-based overload for [CanvasKitSegmentedControl], providing maximum flexibility
 * for custom segment layouts (e.g., custom badges, avatars, or multi-line text).
 *
 * @param itemCount Total number of segments.
 * @param selectedIndex Index of the currently selected segment.
 * @param onSegmentSelected Callback fired when a segment is clicked.
 * @param modifier Root layout modifier.
 * @param enabled Whether the control is interactive.
 * @param isItemEnabled Lambda to check if an individual segment is enabled.
 * @param segmentContent Composable content for each segment.
 */
@Composable
fun CanvasKitSegmentedControl(
    itemCount: Int,
    selectedIndex: Int,
    onSegmentSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isItemEnabled: (Int) -> Boolean = { true },
    segmentContent: @Composable (index: Int, isSelected: Boolean) -> Unit
) {
    if (itemCount <= 0) return

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val stroke = CanvasKitTheme.stroke
    val opacity = CanvasKitTheme.opacity
    val motion = CanvasKitTheme.motion

    val controlAlpha = if (enabled) opacity.full else opacity.disabled

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp)
            .graphicsLayer { alpha = controlAlpha }
            .clip(shapes.pill)
            .background(colors.backgroundSecondary)
            .border(
                width = stroke.thin,
                color = colors.borderSubtle,
                shape = shapes.pill
            )
            .padding(4.dp)
            .selectableGroup()
    ) {
        val totalWidthPx = constraints.maxWidth.toFloat()
        val segmentWidthPx = if (itemCount > 0) totalWidthPx / itemCount else 0f
        val segmentWidthDp = maxWidth / itemCount

        // Animated Pill Offset using Spring Physics (No ripple, elastic travel)
        val pillOffsetPx by animateFloatAsState(
            targetValue = (selectedIndex.coerceIn(0, itemCount - 1)) * segmentWidthPx,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMediumLow
            ),
            label = "SegmentPillOffset"
        )

        // Sliding Indicator Pill
        Box(
            modifier = Modifier
                .offset { IntOffset(x = pillOffsetPx.roundToInt(), y = 0) }
                .width(segmentWidthDp)
                .fillMaxHeight()
                .shadow(elevation = 2.dp, shape = shapes.pill)
                .clip(shapes.pill)
                .background(colors.backgroundPrimary)
        )

        // Interactive Segment Row
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (index in 0 until itemCount) {
                val isSelected = index == selectedIndex
                val isSegmentEnabled = enabled && isItemEnabled(index)
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()

                val segmentScale by animateFloatAsState(
                    targetValue = if (isPressed && isSegmentEnabled) motion.pressedScale else 1.0f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    label = "SegmentPressScale"
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .graphicsLayer {
                            scaleX = segmentScale
                            scaleY = segmentScale
                        }
                        .clip(shapes.pill)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            enabled = isSegmentEnabled,
                            role = Role.Tab,
                            onClick = { onSegmentSelected(index) }
                        )
                        .semantics {
                            role = Role.Tab
                            selected = isSelected
                            stateDescription = "Option ${index + 1} of $itemCount"
                        },
                    contentAlignment = Alignment.Center
                ) {
                    segmentContent(index, isSelected)
                }
            }
        }
    }
}
