package es.joshluq.canvaskit.components.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import kotlin.math.roundToInt

/**
 * Visual styling variants for the active tab indicator in [CanvasKitTabRow].
 */
@Immutable
enum class CanvasKitTabIndicatorVariant {
    /** Refined bottom underline indicator. */
    Underline,

    /** Soft background pill indicator enclosing the active tab. */
    Pill
}

/**
 * CanvasKitTabRow is a high-craft horizontal tab bar for switching between major views or sections.
 *
 * ### Key Features:
 * - **Pure Motion Indicator:** Spring-based physical transition ([Spring.DampingRatioLowBouncy]) between tabs.
 * - **Touch Target Guarantee:** Guarantees minimum 48dp touch region for every child tab.
 * - **A11y Compliant:** Automatically applies [Modifier.selectableGroup] and WCAG AA contrast rules.
 *
 * @param selectedTabIndex Index of the currently active tab.
 * @param modifier Root layout modifier.
 * @param indicatorVariant Visual style of the sliding indicator ([CanvasKitTabIndicatorVariant.Underline] or [CanvasKitTabIndicatorVariant.Pill]).
 * @param tabs Composable slot containing the individual [CanvasKitTab] elements.
 */
@Composable
fun CanvasKitTabRow(
    selectedTabIndex: Int,
    tabsCount: Int,
    modifier: Modifier = Modifier,
    indicatorVariant: CanvasKitTabIndicatorVariant = CanvasKitTabIndicatorVariant.Underline,
    tabs: @Composable () -> Unit
) {
    if (tabsCount <= 0) return

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp)
            .selectableGroup()
    ) {
        val totalWidthPx = constraints.maxWidth.toFloat()
        val tabWidthPx = if (tabsCount > 0) totalWidthPx / tabsCount else 0f
        val tabWidthDp = maxWidth / tabsCount

        // Animated horizontal travel using spring physics
        val indicatorOffsetPx by animateFloatAsState(
            targetValue = (selectedTabIndex.coerceIn(0, tabsCount - 1)) * tabWidthPx,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMediumLow
            ),
            label = "TabIndicatorOffset"
        )

        // Indicator Layer
        when (indicatorVariant) {
            CanvasKitTabIndicatorVariant.Underline -> {
                // Bottom divider line
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .align(Alignment.BottomCenter)
                        .background(colors.borderSubtle)
                )

                // Active underline
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset { IntOffset(x = indicatorOffsetPx.roundToInt(), y = 0) }
                        .width(tabWidthDp)
                        .height(3.dp)
                        .clip(shapes.pill)
                        .background(colors.brandAccent)
                )
            }
            CanvasKitTabIndicatorVariant.Pill -> {
                // Background capsule
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset { IntOffset(x = indicatorOffsetPx.roundToInt(), y = 0) }
                        .width(tabWidthDp)
                        .fillMaxHeight()
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .clip(shapes.pill)
                        .background(colors.backgroundSecondary)
                )
            }
        }

        // Tabs Row Layer
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs()
        }
    }
}

/**
 * CanvasKitTab is an individual tab item designed to be placed inside a [CanvasKitTabRow].
 *
 * @param selected Whether this tab is currently active.
 * @param onClick Callback triggered when tapped.
 * @param modifier Root modifier for this tab.
 * @param enabled Whether this tab is clickable.
 * @param icon Optional composable slot for an icon.
 * @param text Optional composable slot for text title.
 * @param badge Optional composable slot for a trailing counter or notification badge.
 */
@Composable
fun CanvasKitTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val motion = CanvasKitTheme.motion
    val opacity = CanvasKitTheme.opacity
    val spacing = CanvasKitTheme.spacing

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) motion.pressedScale else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "TabPressScale"
    )

    val contentAlpha = if (enabled) opacity.full else opacity.disabled

    Box(
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                alpha = contentAlpha
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Tab,
                onClick = onClick
            )
            .semantics {
                role = Role.Tab
                this.selected = selected
            }
            .padding(horizontal = spacing.sm, vertical = spacing.xs),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                icon()
                if (text != null) {
                    Box(modifier = Modifier.width(spacing.xs))
                }
            }

            if (text != null) {
                text()
            }

            if (badge != null) {
                Box(modifier = Modifier.width(spacing.xs))
                badge()
            }
        }
    }
}
