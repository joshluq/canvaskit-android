package es.joshluq.canvaskit.components.lists

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Direction in which the swipe action is being executed.
 */
@Immutable
enum class CanvasKitDismissDirection {
    StartToEnd,
    EndToStart
}

/**
 * CanvasKitSwipeToDismissBox is a high-craft gestural container allowing list rows or cards
 * to be swiped horizontally to reveal underlying actions (e.g. Delete, Archive, Mark Read).
 *
 * ### Key Features:
 * - **Pure Motion Elasticity:** Rebounds smoothly with [Spring.DampingRatioLowBouncy] if threshold is not reached.
 * - **Bidirectional Support:** Configurable swipe directions (start-to-end, end-to-start, or both).
 * - **A11y Compliant:** TalkBack users can trigger dismiss actions via standard accessibility menus without gestures.
 *
 * @param onDismiss Callback invoked when the swipe threshold is crossed and released.
 * @param modifier Root layout modifier.
 * @param enableStartToEnd Whether swiping to the right is enabled.
 * @param enableEndToStart Whether swiping to the left is enabled.
 * @param dismissThresholdFraction Fraction of width needed to trigger dismiss (0.0 to 1.0). Defaults to 0.35f.
 * @param backgroundContent Composable slot rendered behind the swiped element showing revealed actions.
 * @param content The main foreground composable (e.g., [CanvasKitListItem]).
 */
@Composable
fun CanvasKitSwipeToDismissBox(
    onDismiss: (CanvasKitDismissDirection) -> Unit,
    modifier: Modifier = Modifier,
    enableStartToEnd: Boolean = true,
    enableEndToStart: Boolean = true,
    dismissThresholdFraction: Float = 0.35f,
    backgroundContent: @Composable (direction: CanvasKitDismissDirection?) -> Unit,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val widthPx = constraints.maxWidth.toFloat()
        val thresholdPx = widthPx * dismissThresholdFraction.coerceIn(0.1f, 0.9f)

        val currentDirection = when {
            offsetX.value > 0 -> CanvasKitDismissDirection.StartToEnd
            offsetX.value < 0 -> CanvasKitDismissDirection.EndToStart
            else -> null
        }

        val draggableState = rememberDraggableState { delta ->
            val targetOffset = offsetX.value + delta
            val isAllowed = (targetOffset > 0 && enableStartToEnd) || (targetOffset < 0 && enableEndToStart)
            if (isAllowed) {
                coroutineScope.launch {
                    offsetX.snapTo(targetOffset)
                }
            }
        }

        // Accessibility actions for non-touch navigation
        val a11yModifier = Modifier.semantics {
            val actions = mutableListOf<CustomAccessibilityAction>()
            if (enableEndToStart) {
                actions.add(
                    CustomAccessibilityAction("Dismiss or Delete") {
                        onDismiss(CanvasKitDismissDirection.EndToStart)
                        true
                    }
                )
            }
            if (enableStartToEnd) {
                actions.add(
                    CustomAccessibilityAction("Archive or Complete") {
                        onDismiss(CanvasKitDismissDirection.StartToEnd)
                        true
                    }
                )
            }
            customActions = actions
        }

        Box(modifier = Modifier.fillMaxWidth().then(a11yModifier)) {
            // Background revealed content
            Box(
                modifier = Modifier.matchParentSize(),
                contentAlignment = Alignment.Center
            ) {
                backgroundContent(currentDirection)
            }

            // Foreground swiped content
            Box(
                modifier = Modifier
                    .offset { IntOffset(x = offsetX.value.roundToInt(), y = 0) }
                    .fillMaxWidth()
                    .draggable(
                        state = draggableState,
                        orientation = Orientation.Horizontal,
                        onDragStopped = {
                            val isDismissed = Math.abs(offsetX.value) >= thresholdPx
                            if (isDismissed) {
                                val direction = if (offsetX.value > 0) {
                                    CanvasKitDismissDirection.StartToEnd
                                } else {
                                    CanvasKitDismissDirection.EndToStart
                                }
                                onDismiss(direction)
                                // Elastic snap back after callback
                                coroutineScope.launch {
                                    offsetX.animateTo(
                                        targetValue = 0f,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessMediumLow
                                        )
                                    )
                                }
                            } else {
                                // Snap back elastically
                                coroutineScope.launch {
                                    offsetX.animateTo(
                                        targetValue = 0f,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessMedium
                                        )
                                    )
                                }
                            }
                        }
                    )
            ) {
                content()
            }
        }
    }
}
