package es.joshluq.canvaskit.components.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitBottomBar is a premium, flexible bottom navigation container.
 * It natively handles window insets for system navigation bars and applies
 * Material 3 Expressive styling with support for floating and docked variants.
 *
 * @param modifier Root layout modifier.
 * @param backgroundColor Optional color override. Defaults to [CanvasKitColors.backgroundPrimary].
 * @param showTopBorder When true, renders a thin separator border at the top of the bar (only for docked).
 * @param isFloating When true, detaches the bar from the edges and applies a shadow and full rounding.
 * @param content Composable Row scope containing [CanvasKitBottomBarItem] elements.
 */
@Composable
fun CanvasKitBottomBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = CanvasKitTheme.colors.backgroundPrimary,
    showTopBorder: Boolean = true,
    isFloating: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    val containerShape = if (isFloating) {
        shapes.pill // Fully rounded 24dp
    } else {
        RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    }

    val shadowElevation = if (isFloating) 12.dp else 0.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .then(if (isFloating) Modifier.padding(spacing.md) else Modifier)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(shadowElevation, containerShape)
                .clip(containerShape)
                .background(backgroundColor)
        ) {
            // Top separator border (only for docked mode)
            if (showTopBorder && !isFloating) {
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

    val activeColor = colors.brandAccent
    val inactiveColor = colors.textSecondary
    val iconColor = if (selected) activeColor else inactiveColor
    val textColor = if (selected) colors.textPrimary else inactiveColor

    val interactionSource = remember { MutableInteractionSource() }

    // Selection animation tokens (Spring for Expressive feel)
    val springSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )

    val indicatorAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = springSpec,
        label = "indicatorAlpha"
    )
    val indicatorScale by animateFloatAsState(
        targetValue = if (selected) 1f else 0.8f,
        animationSpec = springSpec,
        label = "indicatorScale"
    )

    // Icon micro-interaction: subtle scale up when selected
    val iconScale by animateFloatAsState(
        targetValue = if (selected) 1.15f else 1.0f,
        animationSpec = springSpec,
        label = "iconScale"
    )

    // Label animation tokens
    val labelAlpha by animateFloatAsState(
        targetValue = if (alwaysShowLabel || selected) 1f else 0f,
        animationSpec = springSpec,
        label = "labelAlpha"
    )
    val labelYOffset by animateDpAsState(
        targetValue = if (alwaysShowLabel || selected) 0.dp else 4.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "labelYOffset"
    )

    val selectionStateDescription = if (selected) "Seleccionado" else "No seleccionado"

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null // REMOVE RIPPLE
            )
            .semantics(mergeDescendants = true) {
                stateDescription = selectionStateDescription
            }
            .graphicsLayer {
                alpha = if (enabled) 1.0f else 0.4f
            }
    ) {
        // Selection Indicator and Content Container
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .graphicsLayer {
                    scaleX = indicatorScale
                    scaleY = indicatorScale
                }
                .padding(spacing.xxs)
                .defaultMinSize(minWidth = 64.dp, minHeight = 64.dp)
                .background(
                    color = colors.brandAccent.copy(alpha = 0.12f * indicatorAlpha),
                    shape = shapes.pill
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icon with optional Badge overlay
                Box(
                    modifier = Modifier.graphicsLayer {
                        scaleX = iconScale
                        scaleY = iconScale
                    }
                ) {
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
