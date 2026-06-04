package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.layout.CanvasKitLoadingScaffold
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * TypographyScreen showcases the "Atelier" design system's type scale.
 */
@Composable
fun TypographyScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    CanvasKitLoadingScaffold(
        isLoading = false,
        modifier = modifier.fillMaxSize(),
        topBar = {
            CanvasKitTopBar(
                title = {
                    Column {
                        Text(
                            text = "Typography System",
                            style = CanvasKitTheme.typography.headingLarge,
                            color = colors.textPrimary
                        )
                        Text(
                            text = "A technical and precise geometric scale.",
                            style = CanvasKitTheme.typography.labelSmall,
                            color = colors.textSecondary
                        )
                    }
                },
                navigationIcon = {
                    CanvasKitIconButton(
                        onClick = onBack,
                        contentDescription = "Back"
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.backgroundSecondary)
                    .verticalScroll(rememberScrollState())
                    .padding(spacing.md),
                verticalArrangement = Arrangement.spacedBy(spacing.lg)
            ) {
                // Introduction Section
                Column(modifier = Modifier.padding(horizontal = spacing.xs)) {
                    Text(
                        text = "The Type\nHierarchy",
                        style = CanvasKitTheme.typography.displayMedium,
                        color = colors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(spacing.sm))
                    Text(
                        text = "We use the Inter font family, optimized for maximum legibility and architectural feel across all device sizes.",
                        style = CanvasKitTheme.typography.bodyLarge,
                        color = colors.textSecondary
                    )
                }

                // Section: Display Styles
                SpecSectionCard(
                    title = "Display",
                    description = "Large, expressive styles for heroic headers and high-impact messaging."
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
                        TypographyItem("Display Large", CanvasKitTheme.typography.displayLarge)
                        TypographyItem("Display Medium", CanvasKitTheme.typography.displayMedium)
                    }
                }

                // Section: Heading Styles
                SpecSectionCard(
                    title = "Headings",
                    description = "Structural headers that guide the user through content blocks."
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
                        TypographyItem("Heading Large", CanvasKitTheme.typography.headingLarge)
                        TypographyItem("Heading Medium", CanvasKitTheme.typography.headingMedium)
                    }
                }

                // Section: Body Styles
                SpecSectionCard(
                    title = "Body",
                    description = "Optimized for long-form reading and interface descriptions."
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
                        TypographyItem("Body Large", CanvasKitTheme.typography.bodyLarge)
                        TypographyItem("Body Medium", CanvasKitTheme.typography.bodyMedium)
                    }
                }

                // Section: Label Styles
                SpecSectionCard(
                    title = "Labels & Metadata",
                    description = "Functional styles for navigation, tags, and secondary data."
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
                        TypographyItem("Label Large", CanvasKitTheme.typography.labelLarge)
                        TypographyItem("Label Small", CanvasKitTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}

@Composable
private fun TypographyItem(name: String, style: TextStyle) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = name,
            style = CanvasKitTheme.typography.labelSmall,
            color = colors.textSecondary,
            modifier = Modifier.padding(bottom = spacing.xs)
        )
        Text(
            text = "Atelier Design System",
            style = style,
            color = colors.textPrimary
        )
    }
}
