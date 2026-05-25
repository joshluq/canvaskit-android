package es.joshluq.canvaskit.showcase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import es.joshluq.canvaskit.showcase.ui.theme.ShowcaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowcaseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = CanvasKitTheme.colors.backgroundPrimary
                ) { innerPadding ->
                    ShowcaseScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowcaseScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val onButtonClick = {
        Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(CanvasKitTheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg)
    ) {
        // Header
        Column {
            Text(
                text = "CanvasKit Showcase",
                style = CanvasKitTheme.typography.displayMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(CanvasKitTheme.spacing.xxs))
            Text(
                text = "Atelier Clean Design System Components",
                style = CanvasKitTheme.typography.bodyLarge,
                color = CanvasKitTheme.colors.textSecondary
            )
        }

        // Section: Primary Buttons
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Primary Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                CanvasKitButton(onClick = onButtonClick) {
                    Text(
                        "Primary Default",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.backgroundPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, enabled = false) {
                    Text(
                        "Disabled",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.backgroundPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, loading = true) {
                    Text(
                        "Loading",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.backgroundPrimary
                    )
                }
            }
        }

        // Section: Secondary Buttons
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Secondary Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Secondary) {
                    Text(
                        "Secondary Default",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Secondary, enabled = false) {
                    Text(
                        "Disabled",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Secondary, loading = true) {
                    Text(
                        "Loading",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }
            }
        }

        // Section: Ghost Buttons
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Ghost Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                modifier = Modifier.fillMaxWidth()
            ) {
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Ghost) {
                    Text(
                        "Ghost Default",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandAccent
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Ghost, enabled = false) {
                    Text(
                        "Disabled",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandAccent
                    )
                }
                CanvasKitButton(onClick = onButtonClick, variant = CanvasKitButtonVariant.Ghost, loading = true) {
                    Text(
                        "Loading",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = CanvasKitTheme.colors.brandAccent
                    )
                }
            }
        }

        // Section: Icon Buttons
        Column(verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm)) {
            Text(
                text = "Icon Buttons",
                style = CanvasKitTheme.typography.headingMedium,
                color = CanvasKitTheme.colors.textPrimary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Circle Default
                CanvasKitIconButton(onClick = onButtonClick) {
                    Text(
                        "＋",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }

                // Rounded Container Background
                CanvasKitIconButton(
                    onClick = onButtonClick,
                    shape = CanvasKitTheme.shapes.medium,
                    backgroundColor = CanvasKitTheme.colors.backgroundSecondary,
                    contentColor = CanvasKitTheme.colors.brandAccent
                ) {
                    Text(
                        "⚙",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = CanvasKitTheme.colors.brandAccent
                    )
                }

                // Disabled
                CanvasKitIconButton(onClick = onButtonClick, enabled = false) {
                    Text(
                        "⚙",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }

                // Loading
                CanvasKitIconButton(onClick = onButtonClick, loading = true) {
                    Text(
                        "⚙",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = CanvasKitTheme.colors.brandPrimary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowcasePreview() {
    ShowcaseTheme {
        ShowcaseScreen()
    }
}


