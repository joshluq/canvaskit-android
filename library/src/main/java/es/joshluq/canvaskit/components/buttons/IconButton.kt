package es.joshluq.canvaskit.components.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.feedback.CanvasKitLoadingSpinner
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitContentColor
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitIconButton is a specialized icon-only interactive component.
 *
 * @param onClick Callback to execute on click.
 * @param contentDescription Text description for accessibility services.
 * @param modifier Root layout modifier.
 * @param enabled Controls whether the icon button is clickable.
 * @param loading Shows progress spinner and disables clicking when true.
 * @param shape Shape of the background container. Defaults to circle.
 * @param backgroundColor Background fill color. Defaults to transparent.
 * @param contentColor Color applied to the icon / spinner.
 * @param interactionSource Custom interaction source.
 * @param icon Composable slot for the icon content.
 */
@Composable
fun CanvasKitIconButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    shape: Shape = CanvasKitTheme.shapes.pill,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = CanvasKitTheme.contentColor,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    icon: @Composable () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val theme = CanvasKitTheme

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled && !loading) theme.motion.pressedScale else 1.0f,
        animationSpec = tween(durationMillis = theme.motion.short1, easing = theme.motion.standard),
        label = "IconButtonScale"
    )

    val contentAlpha = if (enabled) theme.opacity.full else theme.opacity.disabled

    Box(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .alpha(contentAlpha)
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            .clip(shape)
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled && !loading,
                role = Role.Button,
                onClick = onClick
            )
            .padding(theme.spacing.xs)
            .semantics(mergeDescendants = true) {
                role = Role.Button
                this.contentDescription = contentDescription
                if (loading) {
                    stateDescription = "Loading"
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CanvasKitLoadingSpinner(
                color = contentColor,
                modifier = Modifier.size(20.dp)
            )
        } else {
            CompositionLocalProvider(LocalCanvasKitContentColor provides contentColor) {
                icon()
            }
        }
    }
}
