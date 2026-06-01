package es.joshluq.canvaskit.components.feedback

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitStateView is a premium molecular component used to represent informative view states
 * such as Empty States, Error Messages, or Success Confirmations.
 *
 * It features a large circular focal point for the icon, semantic text hierarchy,
 * and spring-based entry animations.
 *
 * @param title The main headline of the state.
 * @param icon The central icon or illustration.
 * @param modifier Root layout modifier.
 * @param description Optional supporting text for more context.
 * @param variantColor Color applied to the icon container background (at low opacity).
 * @param action Optional slot for a primary action button (e.g. [CanvasKitButton]).
 * @param visible Whether the state view should be visible. Triggers the entry animation.
 */
@Composable
fun CanvasKitStateView(
    title: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    variantColor: Color = CanvasKitTheme.colors.brandAccent,
    action: (@Composable () -> Unit)? = null,
    visible: Boolean = true
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    val springSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMediumLow
    )

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = springSpec) +
            scaleIn(initialScale = 0.8f, animationSpec = springSpec) +
            slideInVertically(initialOffsetY = { 40 }),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Focal Circular Icon Container
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CanvasKitTheme.shapes.pill)
                    .background(variantColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }

            Spacer(modifier = Modifier.height(spacing.lg))

            // Text Content
            Text(
                text = title,
                style = typography.headingLarge,
                color = colors.textPrimary,
                textAlign = TextAlign.Center
            )

            if (!description.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(spacing.xs))
                Text(
                    text = description,
                    style = typography.bodyLarge,
                    color = colors.textSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.85f)
                )
            }

            // Optional Action
            if (action != null) {
                Spacer(modifier = Modifier.height(spacing.xl))
                action()
            }
        }
    }
}
