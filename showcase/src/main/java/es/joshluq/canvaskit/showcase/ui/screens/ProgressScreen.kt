package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonSize
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.feedback.CanvasKitCircularProgressBar
import es.joshluq.canvaskit.components.feedback.CanvasKitLinearProgressBar
import es.joshluq.canvaskit.components.feedback.CanvasKitProgressBarSize
import es.joshluq.canvaskit.components.feedback.CanvasKitStep
import es.joshluq.canvaskit.components.feedback.CanvasKitStepIndicator
import es.joshluq.canvaskit.components.feedback.CanvasKitStepOrientation
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * ProgressScreen showcases Progress Bars and Step Indicators.
 */
@Composable
fun ProgressScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    // Interactive progress states
    var progressVal by remember { mutableFloatStateOf(0.45f) }
    var currentStepIndex by remember { mutableIntStateOf(1) }

    val wizardSteps = remember {
        listOf(
            CanvasKitStep(title = "Account", subtitle = "Your details"),
            CanvasKitStep(title = "Payment", subtitle = "Card & billing"),
            CanvasKitStep(title = "Review", subtitle = "Confirm order"),
            CanvasKitStep(title = "Done", subtitle = "Receipt")
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundPrimary)
    ) {
        CanvasKitTopBar(
            title = {
                Text(
                    text = "Progress & Steppers",
                    style = typography.headingMedium,
                    color = colors.textPrimary
                )
            },
            navigationIcon = {
                CanvasKitIconButton(
                    onClick = onBack,
                    contentDescription = "Back"
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = colors.textPrimary
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {
            // 1. Linear Progress Bars
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Linear Progress Bars",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Determinate (${(progressVal * 100).toInt()}%):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitLinearProgressBar(
                        progress = progressVal,
                        size = CanvasKitProgressBarSize.Medium
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitButton(
                            text = "-15%",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Secondary,
                            onClick = { progressVal = (progressVal - 0.15f).coerceIn(0f, 1f) }
                        )
                        CanvasKitButton(
                            text = "+15%",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Secondary,
                            onClick = { progressVal = (progressVal + 0.15f).coerceIn(0f, 1f) }
                        )
                    }

                    Spacer(modifier = Modifier.height(spacing.xs))

                    Text(
                        text = "Indeterminate (Continuous Loop):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitLinearProgressBar(
                        progress = null,
                        size = CanvasKitProgressBarSize.Small
                    )
                }
            }

            // 2. Circular Progress Bars
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Circular Progress Bars",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Determinate (${(progressVal * 100).toInt()}%)",
                            style = typography.labelSmall,
                            color = colors.textSecondary,
                            modifier = Modifier.padding(bottom = spacing.xs)
                        )
                        CanvasKitCircularProgressBar(progress = progressVal)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Indeterminate",
                            style = typography.labelSmall,
                            color = colors.textSecondary,
                            modifier = Modifier.padding(bottom = spacing.xs)
                        )
                        CanvasKitCircularProgressBar(progress = null)
                    }
                }
            }

            // 3. Step Indicator (Wizard & Timeline)
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Step Indicators (Steppers)",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Horizontal Wizard Flow (Tap completed step to go back):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitStepIndicator(
                        steps = wizardSteps,
                        currentStepIndex = currentStepIndex,
                        orientation = CanvasKitStepOrientation.Horizontal,
                        onStepClick = { currentStepIndex = it }
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitButton(
                            text = "Prev Step",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Secondary,
                            enabled = currentStepIndex > 0,
                            onClick = { currentStepIndex-- }
                        )
                        CanvasKitButton(
                            text = "Next Step",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Primary,
                            enabled = currentStepIndex < wizardSteps.size - 1,
                            onClick = { currentStepIndex++ }
                        )
                    }

                    Spacer(modifier = Modifier.height(spacing.sm))

                    Text(
                        text = "Vertical Timeline:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitStepIndicator(
                        steps = wizardSteps,
                        currentStepIndex = currentStepIndex,
                        orientation = CanvasKitStepOrientation.Vertical,
                        onStepClick = { currentStepIndex = it }
                    )
                }
            }
        }
    }
}
