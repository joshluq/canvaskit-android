package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.chips.CanvasKitChip
import es.joshluq.canvaskit.components.feedback.CanvasKitStateView
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

private enum class StateType { Empty, Error, Success }

@Composable
fun FeedbackStatesScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentState by remember { mutableStateOf(StateType.Empty) }
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Text(
                    text = "Feedback States",
                    style = CanvasKitTheme.typography.headingLarge,
                    color = colors.textPrimary
                )
            },
            navigationIcon = {
                CanvasKitIconButton(onClick = onBack, contentDescription = "Back") { contentColor ->
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = contentColor)
                }
            }
        )

        // State Selector
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.md),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Select View State",
                    style = CanvasKitTheme.typography.labelSmall,
                    color = colors.textSecondary,
                    modifier = Modifier.padding(bottom = spacing.xs)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    StateType.entries.forEach { state ->
                        CanvasKitChip(
                            selected = currentState == state,
                            onClick = { currentState = state },
                            label = {
                                Text(
                                    text = state.name,
                                    style = CanvasKitTheme.typography.labelLarge,
                                    color = if (currentState == state) colors.backgroundPrimary else colors.textPrimary
                                )
                            }
                        )
                    }
                }
            }
        }

        // Live Preview Container
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = spacing.md, end = spacing.md, bottom = spacing.md)
                .background(colors.backgroundPrimary, CanvasKitTheme.shapes.container),
            contentAlignment = Alignment.Center
        ) {
            when (currentState) {
                StateType.Empty -> CanvasKitStateView(
                    title = "No Project Data",
                    description = "We couldn't find any projects matching your current filters. Try adjusting your search criteria.",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Inbox,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = colors.brandAccent
                        )
                    }
                )
                StateType.Error -> CanvasKitStateView(
                    title = "Network Timeout",
                    description = "The request took too long to respond. This might be due to a poor connection or server maintenance.",
                    variantColor = colors.error,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.CloudOff,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = colors.error
                        )
                    },
                    action = {
                        CanvasKitButton(onClick = { }) {
                            Text("Retry Request")
                        }
                    }
                )
                StateType.Success -> CanvasKitStateView(
                    title = "Sync Complete",
                    description = "Your local workspace is now fully synchronized with the Atelier cloud infrastructure.",
                    variantColor = colors.success,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.TaskAlt,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = colors.success
                        )
                    }
                )
            }
        }
    }
}
