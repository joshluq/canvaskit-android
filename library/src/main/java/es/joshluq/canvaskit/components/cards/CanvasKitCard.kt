package es.joshluq.canvaskit.components.cards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Variants for the CanvasKitCard component.
 */
enum class CanvasKitCardVariant {
    Outlined,
    Elevated,
    Flat
}

/**
 * CanvasKitCard is a highly-polished, accessible, and customizable container component.
 * It follows Composition over Configuration principles, providing slot layouts for
 * header, footer, and main content while managing interactive states and scale motions.
 *
 * ### Structural Slots:
 * To maintain visual consistency across the application, use the predefined slots:
 * - **Header:** For titles, subtitles, or top-aligned icons.
 * - **Content:** For the main body of information.
 * - **Footer:** For actions, buttons, or secondary metadata.
 *
 * Using these slots ensures that horizontal separators and internal paddings are applied
 * according to the Atelier Design System.
 *
 * @param onClick Optional callback when card is clicked. When provided, card is focusable and interactive.
 * @param modifier Root layout modifier.
 * @param variant Visual variant of the card container (Outlined, Elevated, Flat).
 * @param selected Whether the card is currently selected. Triggers accent borders and screen-reader announcements.
 * @param enabled Whether the card interactions are enabled (ignored if onClick is null).
 * @param shape Shape of the card container. Defaults to CanvasKitTheme.shapes.medium.
 * @param header Composable slot for the header section of the card.
 * @param footer Composable slot for the footer section of the card.
 * @param content Composable slot for the main content body of the card.
 */
@Composable
fun CanvasKitCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    variant: CanvasKitCardVariant = CanvasKitCardVariant.Outlined,
    selected: Boolean = false,
    enabled: Boolean = true,
    shape: Shape = CanvasKitTheme.shapes.container,
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    // 1. Interaction & Press animation handling
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled && onClick != null) 0.98f else 1.0f,
        animationSpec = tween(
            durationMillis = CanvasKitTheme.motion.short1,
            easing = CanvasKitTheme.motion.standard
        ),
        label = "CardPressScale"
    )

    // 2. Select visual attributes (Background & Border styling)
    val backgroundColor = when (variant) {
        CanvasKitCardVariant.Flat -> colors.backgroundSecondary
        CanvasKitCardVariant.Outlined -> colors.backgroundPrimary
        CanvasKitCardVariant.Elevated -> colors.backgroundPrimary
    }

    val borderStroke = when {
        selected -> BorderStroke(width = 2.dp, color = colors.brandAccent)
        variant == CanvasKitCardVariant.Outlined -> BorderStroke(width = 0.5.dp, color = colors.borderSubtle)
        else -> null
    }

    // 3. Elevation shadow
    val shadowElevation = if (variant == CanvasKitCardVariant.Elevated) 4.dp else 0.dp

    // 4. Root Card Container
    Box(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .shadow(elevation = shadowElevation, shape = shape, clip = false)
            .clip(shape)
            .background(backgroundColor)
            .then(
                if (borderStroke != null) Modifier.border(borderStroke, shape) else Modifier
            )
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null, // Custom scale animation replaces default ripple
                        enabled = enabled,
                        role = Role.Button,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            )
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            .semantics(mergeDescendants = true) {
                if (onClick != null) {
                    role = Role.Button
                }
                stateDescription = if (selected) "Selected" else "Not selected"
            }
    ) {
        Column(
            modifier = Modifier.padding(spacing.lg)
        ) {
            if (header != null) {
                header()
                Spacer(modifier = Modifier.height(spacing.sm))
            }
            content()
            if (footer != null) {
                Spacer(modifier = Modifier.height(spacing.sm))
                footer()
            }
        }
    }
}
