package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.feedback.CanvasKitPopup
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * PopupsScreen showcases the "Artisanal Precision" contextual overlays.
 */
@Composable
fun PopupsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showMenuPopup by remember { mutableStateOf(false) }
    var showTooltipPopup by remember { mutableStateOf(false) }

    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Contextual Precision",
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Refined floating modules and anchored tooltips.",
                        style = CanvasKitTheme.typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Atrás",
                        tint = colors.brandPrimary
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
            
            // Introduction Section
            Column(modifier = Modifier.padding(horizontal = spacing.xs)) {
                Text(
                    text = "Anchored\nRefinement",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Floating components that appear exactly where they are needed, maintaining the modular purity of the interface.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Contextual Menu
            SpecSectionCard(
                title = "Dropdown Precision",
                description = "Anchored modules for contextual actions."
            ) {
                Box {
                    CanvasKitIconButton(
                        onClick = { showMenuPopup = true },
                        backgroundColor = colors.backgroundSecondary
                    ) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", tint = colors.brandPrimary)
                    }

                    CanvasKitPopup(
                        expanded = showMenuPopup,
                        onDismissRequest = { showMenuPopup = false },
                        offset = DpOffset(0.dp, 48.dp)
                    ) {
                        Column(modifier = Modifier.width(200.dp)) {
                            listOf("Edit Precision", "Duplicate Module", "Export Tokens").forEach { item ->
                                Text(
                                    text = item,
                                    style = CanvasKitTheme.typography.bodyMedium,
                                    color = colors.textPrimary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { 
                                            showMenuPopup = false
                                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                        }
                                        .padding(vertical = spacing.xs)
                                )
                            }
                        }
                    }
                }
            }

            // Section: Tooltips
            SpecSectionCard(
                title = "Floating Insights",
                description = "Non-intrusive informational overlays."
            ) {
                Box {
                    CanvasKitIconButton(
                        onClick = { showTooltipPopup = true },
                        backgroundColor = colors.backgroundSecondary
                    ) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Info", tint = colors.brandPrimary)
                    }

                    CanvasKitPopup(
                        expanded = showTooltipPopup,
                        onDismissRequest = { showTooltipPopup = false },
                        offset = DpOffset(0.dp, 48.dp)
                    ) {
                        Column(modifier = Modifier.width(240.dp)) {
                            Text(
                                text = "Artisanal Insight",
                                style = CanvasKitTheme.typography.labelSmall,
                                color = colors.brandPrimary
                            )
                            Spacer(modifier = Modifier.height(spacing.xxs))
                            Text(
                                text = "Every popup uses the 24dp signature rounding for visual consistency across the ecosystem.",
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = colors.textSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}
