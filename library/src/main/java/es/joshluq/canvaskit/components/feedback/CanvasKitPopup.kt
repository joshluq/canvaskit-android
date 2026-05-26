package es.joshluq.canvaskit.components.feedback

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import kotlinx.coroutines.launch

/**
 * CanvasKitPopup is a premium, animated, and accessible popup component.
 * It anchors relative to its parent container, displaying tooltips, context menus,
 * or general popovers with custom entry and exit scale/fade micro-animations.
 *
 * @param expanded Controls whether the popup is currently visible.
 * @param onDismissRequest Callback invoked when the user clicks outside or dismisses the popup.
 * @param modifier Styling modifier for the popup's container card.
 * @param alignment Alignment relative to the parent anchor.
 * @param offset Optional offset relative to the parent anchor.
 * @param properties Custom popup configurations.
 * @param shape Shape of the popup container. Defaults to [CanvasKitTheme.shapes.medium].
 * @param content The popup's layout body.
 */
@Composable
fun CanvasKitPopup(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.TopStart,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    properties: PopupProperties = PopupProperties(focusable = true),
    shape: Shape = CanvasKitTheme.shapes.medium,
    content: @Composable () -> Unit
) {
    var isRendered by remember { mutableStateOf(expanded) }
    val scale = remember { Animatable(if (expanded) 1.0f else 0.95f) }
    val alpha = remember { Animatable(if (expanded) 1.0f else 0.0f) }

    val motion = CanvasKitTheme.motion
    val short1 = motion.short1
    val short2 = motion.short2
    val standardEasing = motion.standard

    LaunchedEffect(expanded) {
        if (expanded) {
            isRendered = true
            launch {
                scale.animateTo(
                    targetValue = 1.0f,
                    animationSpec = tween(
                        durationMillis = short2,
                        easing = standardEasing
                    )
                )
            }
            launch {
                alpha.animateTo(
                    targetValue = 1.0f,
                    animationSpec = tween(
                        durationMillis = short2,
                        easing = standardEasing
                    )
                )
            }
        } else {
            launch {
                scale.animateTo(
                    targetValue = 0.95f,
                    animationSpec = tween(
                        durationMillis = short1,
                        easing = standardEasing
                    )
                )
            }
            launch {
                alpha.animateTo(
                    targetValue = 0.0f,
                    animationSpec = tween(
                        durationMillis = short1,
                        easing = standardEasing
                    )
                )
            }.join() // Wait for the fade-out exit animation to finish
            isRendered = false
        }
    }

    if (isRendered) {
        val density = LocalDensity.current
        val offsetPx = remember(offset, density) {
            with(density) {
                IntOffset(
                    x = offset.x.roundToPx(),
                    y = offset.y.roundToPx()
                )
            }
        }

        Popup(
            alignment = alignment,
            offset = offsetPx,
            onDismissRequest = onDismissRequest,
            properties = properties
        ) {
            val colors = CanvasKitTheme.colors
            val spacing = CanvasKitTheme.spacing

            Box(
                modifier = modifier
                    .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
                    .alpha(alpha.value)
                    .clip(shape)
                    .background(colors.backgroundSecondary)
                    .border(width = 1.dp, color = colors.borderSubtle, shape = shape)
                    .padding(spacing.sm)
            ) {
                content()
            }
        }
    }
}
