package es.joshluq.canvaskit.components.feedback

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

private val sampleSteps = listOf(
    CanvasKitStep(title = "Account", subtitle = "Your details"),
    CanvasKitStep(title = "Payment", subtitle = "Card & billing"),
    CanvasKitStep(title = "Confirm", subtitle = "Review order")
)

@Composable
private fun StepIndicatorPreviewContent() {
    var currentStep by remember { mutableIntStateOf(1) }

    CanvasKitTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundPrimary)
                .padding(CanvasKitTheme.spacing.md),
            verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.xl)
        ) {
            Text(
                text = "Horizontal Wizard (Step 2 Active)",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitStepIndicator(
                steps = sampleSteps,
                currentStepIndex = currentStep,
                orientation = CanvasKitStepOrientation.Horizontal,
                onStepClick = { currentStep = it }
            )

            Text(
                text = "Vertical Timeline",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitStepIndicator(
                steps = sampleSteps,
                currentStepIndex = currentStep,
                orientation = CanvasKitStepOrientation.Vertical,
                onStepClick = { currentStep = it }
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun CanvasKitStepIndicatorLightPreview() {
    StepIndicatorPreviewContent()
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CanvasKitStepIndicatorDarkPreview() {
    StepIndicatorPreviewContent()
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
private fun CanvasKitStepIndicatorRtlPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        StepIndicatorPreviewContent()
    }
}

@Preview(name = "2.0x Font Scaling", showBackground = true, fontScale = 2.0f)
@Composable
private fun CanvasKitStepIndicatorFontScalePreview() {
    StepIndicatorPreviewContent()
}
