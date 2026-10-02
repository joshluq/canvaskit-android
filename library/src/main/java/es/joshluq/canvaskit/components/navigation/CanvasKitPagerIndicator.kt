package es.joshluq.canvaskit.components.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Visual styling variants for [CanvasKitPagerIndicator].
 */
@Immutable
enum class CanvasKitPagerIndicatorVariant {
    /** The active dot expands into an organic pill with spring mechanics. */
    Worm,

    /** Discreet circular dots with color transitions. */
    Dots
}

/**
 * CanvasKitPagerIndicator is an artisanal page indicator component commonly used in
 * on-boarding flows, image carousels, and multi-step view pagers.
 *
 * ### Key Features:
 * - **Pure Motion Springs:** Dots expand and shift with organic [Spring.DampingRatioLowBouncy] physics.
 * - **Zero Vendor Lock-in:** Decoupled from specific Pager implementations; consumes simple page indices.
 * - **A11y Compliant:** Meets WCAG AA contrast rules and provides informative TalkBack announcements.
 *
 * @param pageCount Total count of available pages.
 * @param currentPage Index of the currently active page (0-indexed).
 * @param modifier Root layout modifier.
 * @param variant Visual style ([CanvasKitPagerIndicatorVariant.Worm] or [CanvasKitPagerIndicatorVariant.Dots]).
 * @param dotSize Base diameter for standard dots. Defaults to 8.dp.
 * @param activeDotWidth Expanded width for the active dot in Worm mode. Defaults to 24.dp.
 * @param spacing Distance between indicator dots. Defaults to 6.dp.
 * @param onPageClick Optional callback when a dot is clicked. When non-null, ensures minimum 48dp touch targets.
 */
@Composable
fun CanvasKitPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    variant: CanvasKitPagerIndicatorVariant = CanvasKitPagerIndicatorVariant.Worm,
    dotSize: Dp = 8.dp,
    activeDotWidth: Dp = 24.dp,
    spacing: Dp = 6.dp,
    onPageClick: ((Int) -> Unit)? = null
) {
    if (pageCount <= 1) return

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes

    Row(
        modifier = modifier
            .semantics(mergeDescendants = onPageClick == null) {
                stateDescription = "Page ${currentPage + 1} of $pageCount"
            }
            .padding(vertical = CanvasKitTheme.spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (index in 0 until pageCount) {
            val isSelected = index == currentPage

            // Spring-based width animation for the Worm variant
            val animatedWidth by animateDpAsState(
                targetValue = if (isSelected && variant == CanvasKitPagerIndicatorVariant.Worm) {
                    activeDotWidth
                } else {
                    dotSize
                },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                ),
                label = "PagerIndicatorWidth_$index"
            )

            // Animated color transition between active and inactive dots
            val animatedColor by animateColorAsState(
                targetValue = if (isSelected) {
                    colors.brandAccent
                } else {
                    colors.borderSubtle
                },
                label = "PagerIndicatorColor_$index"
            )

            val interactionSource = remember { MutableInteractionSource() }

            val clickModifier = if (onPageClick != null) {
                Modifier
                    .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp) // A11y minimum touch target
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        role = Role.Button,
                        onClick = { onPageClick(index) }
                    )
                    .semantics {
                        role = Role.Button
                        contentDescription = "Go to page ${index + 1}"
                    }
            } else {
                Modifier
            }

            Box(
                modifier = Modifier
                    .then(clickModifier),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(dotSize)
                        .width(animatedWidth)
                        .clip(shapes.pill)
                        .background(animatedColor)
                )
            }
        }
    }
}
