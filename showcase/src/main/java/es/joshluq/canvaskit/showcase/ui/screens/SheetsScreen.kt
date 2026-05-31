package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.components.sheets.CanvasKitBottomSheet
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import kotlinx.coroutines.launch

/**
 * SheetsScreen showcases the "Artisanal Precision" modal sheets.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val scope = rememberCoroutineScope()
    
    var showBasicSheet by remember { mutableStateOf(false) }
    val basicSheetState = rememberModalBottomSheetState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Modal Overlays",
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Refined bottom sheets for contextual flows.",
                        style = CanvasKitTheme.typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(
                    onClick = onBack,
                    contentDescription = "Back"
                ) { contentColor ->
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = contentColor
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
                    text = "Tactile\nSurfaces",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "Our bottom sheets are designed to feel like physical layers, with organic rounding that harmonizes with the ecosystem.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Basic Sheet
            SpecSectionCard(
                title = "Modal Precision",
                description = "Standard modal sheet for complementary actions."
            ) {
                CanvasKitButton(
                    onClick = { showBasicSheet = true },
                    modifier = Modifier.fillMaxWidth()
                ) { contentColor ->
                    Text("Open Bottom Sheet", color = contentColor)
                }
            }
        }
    }

    // Modal Bottom Sheet Implementation
    if (showBasicSheet) {
        CanvasKitBottomSheet(
            onDismissRequest = { showBasicSheet = false },
            sheetState = basicSheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.lg),
                verticalArrangement = Arrangement.spacedBy(spacing.md)
            ) {
                Text(
                    text = "Atelier Sheet",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = colors.textPrimary
                )
                Text(
                    text = "This surface provides a focused context without losing the main application state.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
                
                Spacer(modifier = Modifier.height(spacing.md))
                
                CanvasKitButton(
                    onClick = {
                        scope.launch { basicSheetState.hide() }.invokeOnCompletion {
                            if (!basicSheetState.isVisible) {
                                showBasicSheet = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirm Selection")
                }
                
                Spacer(modifier = Modifier.height(spacing.xl))
            }
        }
    }
}
