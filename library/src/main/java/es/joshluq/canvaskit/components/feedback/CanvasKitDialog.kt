package es.joshluq.canvaskit.components.feedback

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import kotlinx.coroutines.launch

/**
 * CanvasKitDialog is a premium, animated, and accessible modal dialog container.
 * It uses a custom window overlay (wrapping Compose's standard [Dialog]) and applies
 * Atelier Design System values for standard padding, entry animations (fade & scale), and styling.
 *
 * @param onDismissRequest Callback invoked when the user dismisses the dialog (e.g. by back button or tapping outside).
 * @param modifier Modifier applied to the outer layout bounds inside the dialog window.
 * @param properties Configuration properties for the underlying dialog window.
 * @param content The composable content slot to render inside the dialog window.
 */
@Composable
fun CanvasKitDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable () -> Unit
) {
    val scale = remember { Animatable(0.92f) }
    val alpha = remember { Animatable(0f) }

    val motion = CanvasKitTheme.motion
    val duration = motion.medium2
    val easing = motion.standard

    LaunchedEffect(Unit) {
        launch {
            scale.animateTo(
                targetValue = 1.0f,
                animationSpec = tween(durationMillis = duration, easing = easing)
            )
        }
        launch {
            alpha.animateTo(
                targetValue = 1.0f,
                animationSpec = tween(durationMillis = duration, easing = easing)
            )
        }
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Box(
            modifier = modifier
                .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
                .alpha(alpha.value)
                .padding(CanvasKitTheme.spacing.md)
                .fillMaxWidth(0.92f) // Prevents the dialog from stretching full width on tablets/wide devices
                .wrapContentHeight()
        ) {
            content()
        }
    }
}

/**
 * CanvasKitDialogContent is a molecular template providing a standard, beautifully aligned
 * layout structure for modal dialogs. It features slots for an optional icon, a title,
 * custom body/description content, and a row of action buttons.
 *
 * @param title The title Composable (typically using [CanvasKitTheme.typography.headingLarge]).
 * @param buttons Composable block for confirming/dismissing action buttons.
 * @param modifier Root styling modifier for the content card.
 * @param icon Optional leading header icon.
 * @param shape Shape of the dialog card. Defaults to [CanvasKitTheme.shapes.extraLarge].
 * @param content Optional middle body Composable block.
 */
@Composable
fun CanvasKitDialogContent(
    title: @Composable () -> Unit,
    buttons: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    shape: Shape = CanvasKitTheme.shapes.extraLarge,
    content: @Composable (() -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    Box(
        modifier = modifier
            .clip(shape)
            .background(colors.backgroundPrimary)
            .border(width = 1.dp, color = colors.borderSubtle, shape = shape)
            .padding(spacing.md)
            .semantics(mergeDescendants = true) {
                // Merge descendants so that screen readers read the dialog contents as a single card announcement
            }
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(spacing.sm)
        ) {
            // Optional Icon
            if (icon != null) {
                Box(
                    modifier = Modifier.padding(bottom = spacing.xxs),
                    contentAlignment = Alignment.Center
                ) {
                    icon()
                }
            }

            // Title
            Box(modifier = Modifier.fillMaxWidth()) {
                title()
            }

            // Body content
            if (content != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spacing.xs)
                ) {
                    content()
                }
            } else {
                Spacer(modifier = Modifier.height(spacing.xxs))
            }

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.sm, Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                buttons()
            }
        }
    }
}
