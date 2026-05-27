package es.joshluq.canvaskit.components.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitBottomBar is a premium, flexible bottom navigation container.
 * It natively handles window insets for system navigation bars, applies a top
 * border line separator, and lays out navigation items horizontally.
 *
 * @param modifier Root layout modifier.
 * @param backgroundColor Optional color override. Defaults to [CanvasKitColors.backgroundPrimary].
 * @param showTopBorder When true, renders a thin separator border at the top of the bar.
 * @param content Composable Row scope containing [CanvasKitBottomBarItem] elements.
 */
@Composable
fun CanvasKitBottomBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = CanvasKitTheme.colors.backgroundPrimary,
    showTopBorder: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val colors = CanvasKitTheme.colors

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .navigationBarsPadding()
    ) {
        // Top separator border
        if (showTopBorder) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(colors.borderSubtle)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}

/**
 * CanvasKitBottomBarItem represents an individual navigation destination inside the bottom bar.
 * It supports selectable states, selection micro-animations (pill width/fade), and custom badges.
 *
 * @param selected When true, displays item in its active state with the selection pill indicator.
 * @param onClick Triggered when the navigation item is tapped.
 * @param icon Composable slot receiving the active color tint.
 * @param modifier Item layout modifier.
 * @param label Optional destination label slot receiving the active color tint.
 * @param badge Optional Composable badge slot (e.g. [CanvasKitBadge]).
 * @param alwaysShowLabel When true, keeps label visible. When false, slides and fades label on selection.
 * @param enabled When false, makes item non-clickable and transparent.
 */
@Composable
fun RowScope.CanvasKitBottomBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable (tint: Color) -> Unit,
    modifier: Modifier = Modifier,
    label: (@Composable (tint: Color) -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    enabled: Boolean = true
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing
    val motion = CanvasKitTheme.motion

    val activeColor = colors.brandAccent
    val inactiveColor = colors.textSecondary
    val iconColor = if (selected) activeColor else inactiveColor
    val textColor = if (selected) colors.textPrimary else inactiveColor

    // Selection animation tokens
    val animationSpec = tween<Float>(
        durationMillis = motion.short2,
        easing = motion.standard
    )
    val indicatorAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = animationSpec,
        label = "indicatorAlpha"
    )
    val indicatorWidthScale by animateFloatAsState(
        targetValue = if (selected) 1f else 0.5f,
        animationSpec = animationSpec,
        label = "indicatorWidthScale"
    )

    // Label animation tokens
    val labelAlpha by animateFloatAsState(
        targetValue = if (alwaysShowLabel || selected) 1f else 0f,
        animationSpec = animationSpec,
        label = "labelAlpha"
    )
    val labelYOffset by animateDpAsState(
        targetValue = if (alwaysShowLabel || selected) 0.dp else 4.dp,
        animationSpec = tween(
            durationMillis = motion.short2,
            easing = motion.standard
        ),
        label = "labelYOffset"
    )

    val selectionStateDescription = if (selected) "Seleccionado" else "No seleccionado"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab
            )
            .semantics(mergeDescendants = true) {
                stateDescription = selectionStateDescription
            }
            .graphicsLayer {
                alpha = if (enabled) 1.0f else 0.4f
            }
    ) {
        // Icon Container with Active Pill Indicator
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(32.dp)
        ) {
            // Pill background
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .width(64.dp)
                    .graphicsLayer {
                        alpha = indicatorAlpha
                        scaleX = indicatorWidthScale
                    }
                    .background(
                        color = colors.brandAccent.copy(alpha = 0.15f),
                        shape = shapes.pill
                    )
            )

            // Icon with optional Badge overlay
            Box {
                icon(iconColor)

                if (badge != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .graphicsLayer {
                                translationX = 6.dp.toPx()
                                translationY = (-4).dp.toPx()
                            }
                    ) {
                        badge()
                    }
                }
            }
        }

        // Animated text label
        if (label != null) {
            Spacer(modifier = Modifier.height(spacing.xxs))
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = labelAlpha
                        translationY = labelYOffset.toPx()
                    }
            ) {
                label(textColor)
            }
        }
    }
}

/**
 * CanvasKitBadge is a premium notification badge utility.
 * Renders as a small dot when empty, or an auto-expanding pill when text content is provided.
 *
 * @param modifier Layout modifier.
 * @param backgroundColor Badge background color. Defaults to [CanvasKitColors.error].
 * @param contentColor Text content color. Defaults to [CanvasKitColors.backgroundPrimary].
 * @param content Optional Composable text/number content.
 */
@Composable
fun CanvasKitBadge(
    modifier: Modifier = Modifier,
    backgroundColor: Color = CanvasKitTheme.colors.error,
    contentColor: Color = CanvasKitTheme.colors.backgroundPrimary,
    content: (@Composable () -> Unit)? = null
) {
    val spacing = CanvasKitTheme.spacing
    val shapes = CanvasKitTheme.shapes

    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = shapes.pill)
            .padding(
                horizontal = if (content != null) spacing.xxs else 0.dp,
                vertical = if (content != null) 2.dp else 0.dp
            )
            .defaultMinSize(
                minWidth = if (content != null) 16.dp else 8.dp,
                minHeight = if (content != null) 16.dp else 8.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        if (content != null) {
            content()
        }
    }
}
