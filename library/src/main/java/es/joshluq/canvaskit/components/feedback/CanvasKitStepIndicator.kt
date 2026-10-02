package es.joshluq.canvaskit.components.feedback

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Step descriptor for [CanvasKitStepIndicator].
 */
@Immutable
data class CanvasKitStep(
    val title: String,
    val subtitle: String? = null
)

/**
 * Layout orientation for [CanvasKitStepIndicator].
 */
@Immutable
enum class CanvasKitStepOrientation {
    Horizontal,
    Vertical
}

/**
 * CanvasKitStepIndicator guides users across multi-stage wizards, checkouts, and timelines.
 *
 * ### Key Features:
 * - **Spring Connectors:** Connectors fill with [Spring.DampingRatioNoBouncy] physical animation.
 * - **Two Orientations:** Horizontal wizard bar or Vertical event timeline.
 * - **A11y Compliant:** TalkBack announces step position, label, and completed/active state.
 *
 * @param steps List of step descriptors.
 * @param currentStepIndex Zero-based index of the currently active step.
 * @param modifier Root layout modifier.
 * @param orientation Visual orientation ([CanvasKitStepOrientation.Horizontal] or [CanvasKitStepOrientation.Vertical]).
 * @param onStepClick Optional callback when tapping a step.
 */
@Composable
fun CanvasKitStepIndicator(
    steps: List<CanvasKitStep>,
    currentStepIndex: Int,
    modifier: Modifier = Modifier,
    orientation: CanvasKitStepOrientation = CanvasKitStepOrientation.Horizontal,
    onStepClick: ((Int) -> Unit)? = null
) {
    if (steps.isEmpty()) return

    when (orientation) {
        CanvasKitStepOrientation.Horizontal -> {
            HorizontalStepIndicator(
                steps = steps,
                currentStepIndex = currentStepIndex,
                modifier = modifier,
                onStepClick = onStepClick
            )
        }
        CanvasKitStepOrientation.Vertical -> {
            VerticalStepIndicator(
                steps = steps,
                currentStepIndex = currentStepIndex,
                modifier = modifier,
                onStepClick = onStepClick
            )
        }
    }
}

@Composable
private fun HorizontalStepIndicator(
    steps: List<CanvasKitStep>,
    currentStepIndex: Int,
    modifier: Modifier = Modifier,
    onStepClick: ((Int) -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val typography = CanvasKitTheme.typography
    val spacing = CanvasKitTheme.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = spacing.xs),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        steps.forEachIndexed { index, step ->
            val isCompleted = index < currentStepIndex
            val isActive = index == currentStepIndex
            val isUpcoming = index > currentStepIndex

            val isClickable = onStepClick != null && isCompleted
            val interactionSource = remember { MutableInteractionSource() }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .then(
                        if (isClickable) {
                            Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                role = Role.Button,
                                onClick = { onStepClick(index) }
                            )
                        } else Modifier
                    )
                    .semantics {
                        val status = when {
                            isCompleted -> "Completed"
                            isActive -> "Active"
                            else -> "Upcoming"
                        }
                        stateDescription = "Step ${index + 1} of ${steps.size}: ${step.title}, $status"
                        if (isClickable) role = Role.Button
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Node and connector line row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left connecting line
                    if (index > 0) {
                        StepConnector(
                            isFilled = index <= currentStepIndex,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    // Node Circle
                    StepNode(
                        stepNumber = index + 1,
                        isCompleted = isCompleted,
                        isActive = isActive
                    )

                    // Right connecting line
                    if (index < steps.size - 1) {
                        StepConnector(
                            isFilled = index < currentStepIndex,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Spacer(modifier = Modifier.height(spacing.xs))

                // Title label
                Text(
                    text = step.title,
                    style = typography.labelSmall.copy(
                        fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium
                    ),
                    color = when {
                        isActive -> colors.brandPrimary
                        isCompleted -> colors.textPrimary
                        else -> colors.textSecondary.copy(alpha = 0.6f)
                    },
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )

                if (step.subtitle != null) {
                    Text(
                        text = step.subtitle,
                        style = typography.labelSmall,
                        color = colors.textSecondary,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
private fun VerticalStepIndicator(
    steps: List<CanvasKitStep>,
    currentStepIndex: Int,
    modifier: Modifier = Modifier,
    onStepClick: ((Int) -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val typography = CanvasKitTheme.typography
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = spacing.xs)
    ) {
        steps.forEachIndexed { index, step ->
            val isCompleted = index < currentStepIndex
            val isActive = index == currentStepIndex
            val isClickable = onStepClick != null && isCompleted
            val interactionSource = remember { MutableInteractionSource() }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 48.dp)
                    .then(
                        if (isClickable) {
                            Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                role = Role.Button,
                                onClick = { onStepClick(index) }
                            )
                        } else Modifier
                    )
                    .semantics {
                        val status = when {
                            isCompleted -> "Completed"
                            isActive -> "Active"
                            else -> "Upcoming"
                        }
                        stateDescription = "Step ${index + 1} of ${steps.size}: ${step.title}, $status"
                        if (isClickable) role = Role.Button
                    },
                verticalAlignment = Alignment.Top
            ) {
                // Node + vertical connector column
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(32.dp)
                ) {
                    StepNode(
                        stepNumber = index + 1,
                        isCompleted = isCompleted,
                        isActive = isActive
                    )

                    if (index < steps.size - 1) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(32.dp)
                                .background(
                                    if (index < currentStepIndex) colors.brandAccent else colors.borderSubtle
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(spacing.md))

                // Text labels column
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = if (index < steps.size - 1) spacing.md else 0.dp)
                ) {
                    Text(
                        text = step.title,
                        style = typography.labelLarge.copy(
                            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium
                        ),
                        color = when {
                            isActive -> colors.brandPrimary
                            isCompleted -> colors.textPrimary
                            else -> colors.textSecondary.copy(alpha = 0.6f)
                        }
                    )

                    if (step.subtitle != null) {
                        Text(
                            text = step.subtitle,
                            style = typography.bodyMedium,
                            color = colors.textSecondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StepNode(
    stepNumber: Int,
    isCompleted: Boolean,
    isActive: Boolean
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val typography = CanvasKitTheme.typography

    val nodeColor by animateColorAsState(
        targetValue = when {
            isCompleted -> colors.brandAccent
            isActive -> colors.backgroundPrimary
            else -> colors.backgroundSecondary
        },
        label = "StepNodeBg"
    )

    val borderColor by animateColorAsState(
        targetValue = when {
            isCompleted || isActive -> colors.brandAccent
            else -> colors.borderSubtle
        },
        label = "StepNodeBorder"
    )

    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(shapes.pill)
            .background(nodeColor)
            .border(width = 2.dp, color = borderColor, shape = shapes.pill),
        contentAlignment = Alignment.Center
    ) {
        if (isCompleted) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = colors.backgroundPrimary,
                modifier = Modifier.size(16.dp)
            )
        } else {
            Text(
                text = stepNumber.toString(),
                style = typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = if (isActive) colors.brandAccent else colors.textSecondary
            )
        }
    }
}

@Composable
private fun StepConnector(
    isFilled: Boolean,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val animatedColor by animateColorAsState(
        targetValue = if (isFilled) colors.brandAccent else colors.borderSubtle,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "StepConnectorColor"
    )

    Box(
        modifier = modifier
            .height(2.dp)
            .background(animatedColor)
    )
}
