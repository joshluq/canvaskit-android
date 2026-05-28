package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
fun HomeScreen(
    onNavigateToButtons: () -> Unit,
    onNavigateToTextFields: () -> Unit,
    onNavigateToDialogs: () -> Unit,
    onNavigateToPopups: () -> Unit,
    onNavigateToCards: () -> Unit,
    onNavigateToTopBar: () -> Unit,
    onNavigateToBottomBar: () -> Unit,
    onNavigateToToggles: () -> Unit,
    onNavigateToBanners: () -> Unit,
    onNavigateToChips: () -> Unit,
    onNavigateToSkeletons: () -> Unit,
    onNavigateToLoadingScaffold: () -> Unit,
    modifier: Modifier = Modifier
) {

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
            .verticalScroll(rememberScrollState())
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.lg)
    ) {

        val activeComponents = listOf(
            Triple(
                "Buttons",
                "Refined Primary, Secondary, and Ghost variants.",
                onNavigateToButtons
            ),
            Triple(
                "Text Fields",
                "Precision inputs with semantic error states.",
                onNavigateToTextFields
            ),
            Triple("Dialogs", "Modal experiences with premium transitions.", onNavigateToDialogs),
            Triple(
                "Popups & Menus",
                "Floating modules for contextual actions.",
                onNavigateToPopups
            ),
            Triple(
                "Cards & Containers",
                "The core of our modular pureza modular.",
                onNavigateToCards
            ),
            Triple(
                "Top App Bar",
                "Clean navigation headers with inset support.",
                onNavigateToTopBar
            ),
            Triple("Bottom Bar", "Accessible and elegant app navigation.", onNavigateToBottomBar),
            Triple(
                "Switches & Toggles",
                "Tactile interactive selection controls.",
                onNavigateToToggles
            ),
            Triple(
                "Banners & Alerts",
                "System-wide notifications and inline alerts.",
                onNavigateToBanners
            ),
            Triple("Chips", "Compact components for actions, filtering, or selection.", onNavigateToChips),
            Triple("Skeletons", "Loading placeholders with premium shimmer effects.", onNavigateToSkeletons),
            Triple("Loading Scaffold", "Advanced layout container for loading states.", onNavigateToLoadingScaffold)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shapes.container)
                .background(colors.backgroundPrimary)
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            Text(
                text = "Core Components",
                style = CanvasKitTheme.typography.headingMedium,
                color = colors.textPrimary,
                modifier = Modifier.padding(bottom = spacing.xs)
            )

            activeComponents.forEach { (name, description, onClick) ->
                ComponentCard(
                    name = name,
                    description = description,
                    iconVector = when (name) {
                        "Buttons" -> Icons.Default.PlayArrow
                        "Text Fields" -> Icons.Default.Edit
                        "Dialogs" -> Icons.Default.Warning
                        "Cards & Containers" -> Icons.AutoMirrored.Filled.List
                        "Top App Bar" -> Icons.Default.Home
                        "Bottom Bar" -> Icons.Default.Menu
                        "Switches & Toggles" -> Icons.Default.Check
                        "Banners & Alerts" -> Icons.Default.Notifications
                        "Chips" -> Icons.Default.Add
                        "Skeletons" -> Icons.Default.Info
                        "Loading Scaffold" -> Icons.Default.Refresh
                        else -> Icons.Default.Info
                    },
                    iconBg = colors.backgroundSecondary,
                    iconColor = colors.brandPrimary,
                    onClick = onClick
                )
            }
        }

    }
}

@Composable
private fun ComponentCard(
    name: String,
    description: String,
    iconVector: ImageVector,
    iconBg: androidx.compose.ui.graphics.Color,
    iconColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    val cardAlpha = if (enabled) 1.0f else 0.6f

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shapes.extraLarge)
            .background(colors.backgroundSecondary.copy(alpha = if (enabled) 1f else 0.5f))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(spacing.md)
            .alpha(cardAlpha),
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Rounded Icon Box
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(shapes.medium)
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = iconVector,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }

        // Description block
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.xs)
            ) {
                Text(
                    text = name,
                    style = CanvasKitTheme.typography.headingMedium,
                    color = colors.textPrimary
                )
                if (!enabled) {
                    Box(
                        modifier = Modifier
                            .clip(shapes.pill)
                            .background(colors.borderSubtle)
                            .padding(horizontal = spacing.xs, vertical = spacing.xxxs)
                    ) {
                        Text(
                            text = "Coming Soon",
                            style = CanvasKitTheme.typography.labelSmall,
                            color = colors.textSecondary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(spacing.xxxs))
            Text(
                text = description,
                style = CanvasKitTheme.typography.bodyMedium,
                color = colors.textSecondary
            )
        }

        if (enabled) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = colors.brandPrimary.copy(alpha = 0.3f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
