package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonSize
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.layout.CanvasKitDivider
import es.joshluq.canvaskit.components.layout.CanvasKitVerticalDivider
import es.joshluq.canvaskit.components.lists.CanvasKitDismissDirection
import es.joshluq.canvaskit.components.lists.CanvasKitListItem
import es.joshluq.canvaskit.components.lists.CanvasKitSwipeToDismissBox
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

private data class MockMessage(val id: Int, val title: String, val preview: String)

/**
 * GesturesScreen showcases Swipe-To-Dismiss rows and Surface Dividers.
 */
@Composable
fun GesturesScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    var lastActionLog by remember { mutableStateOf("Swipe any item below to trigger an action") }

    val messages = remember {
        mutableStateListOf(
            MockMessage(1, "Quarterly Strategy Review", "Attached are the slide decks for tomorrow..."),
            MockMessage(2, "Design System Atelier V2", "New motion tokens and A11y standards merged..."),
            MockMessage(3, "Security Alert: New Sign-in", "We noticed a login from Chrome on Windows...")
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
                    text = "Gestures & Surfaces",
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
            // 1. Swipe To Dismiss Interactive List
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Swipe to Dismiss / Action Rows",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    Text(
                        text = "Swipe Right to Archive • Swipe Left to Delete:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )

                    // Action feedback badge
                    Text(
                        text = lastActionLog,
                        style = typography.labelSmall,
                        color = colors.brandAccent,
                        modifier = Modifier.padding(bottom = spacing.xs)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shapes.container)
                            .background(colors.backgroundSecondary)
                    ) {
                        messages.forEachIndexed { index, msg ->
                            CanvasKitSwipeToDismissBox(
                                onDismiss = { direction ->
                                    val actionName = if (direction == CanvasKitDismissDirection.EndToStart) "Deleted" else "Archived"
                                    lastActionLog = "$actionName: \"${msg.title}\""
                                    messages.remove(msg)
                                },
                                backgroundContent = { direction ->
                                    val isDelete = direction == CanvasKitDismissDirection.EndToStart
                                    val bgColor = if (isDelete) colors.error else colors.brandAccent

                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(bgColor)
                                            .padding(horizontal = spacing.md),
                                        horizontalArrangement = if (isDelete) Arrangement.End else Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = if (isDelete) Icons.Default.Delete else Icons.Default.Archive,
                                            contentDescription = null,
                                            tint = colors.backgroundPrimary,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                }
                            ) {
                                CanvasKitListItem(
                                    headline = { Text(msg.title, color = colors.textPrimary) },
                                    supportingText = { Text(msg.preview, color = colors.textSecondary) },
                                    leadingContent = {
                                        Icon(
                                            imageVector = Icons.Default.Email,
                                            contentDescription = null,
                                            tint = colors.brandAccent
                                        )
                                    },
                                    modifier = Modifier.background(colors.backgroundSecondary)
                                )
                            }

                            if (index < messages.size - 1) {
                                CanvasKitDivider(startIndent = 56.dp)
                            }
                        }

                        if (messages.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(spacing.lg),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "All items cleared!",
                                    style = typography.bodyMedium,
                                    color = colors.textSecondary
                                )
                            }
                        }
                    }

                    if (messages.isEmpty()) {
                        CanvasKitButton(
                            text = "Reset List",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Secondary,
                            onClick = {
                                messages.addAll(
                                    listOf(
                                        MockMessage(1, "Quarterly Strategy Review", "Attached are the slide decks for tomorrow..."),
                                        MockMessage(2, "Design System Atelier V2", "New motion tokens and A11y standards merged..."),
                                        MockMessage(3, "Security Alert: New Sign-in", "We noticed a login from Chrome on Windows...")
                                    )
                                )
                                lastActionLog = "List restored"
                            }
                        )
                    }
                }
            }

            // 2. Dividers & Separators
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Dividers & Hairline Separators",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Full-width divider:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitDivider()

                    Text(
                        text = "Inset divider (startIndent = 48dp):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitDivider(startIndent = 48.dp)

                    Spacer(modifier = Modifier.height(spacing.xs))

                    Text(
                        text = "Vertical dividers inside action row:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Copy", style = typography.labelLarge, color = colors.textPrimary)
                        CanvasKitVerticalDivider()
                        Text(text = "Share", style = typography.labelLarge, color = colors.textPrimary)
                        CanvasKitVerticalDivider()
                        Text(text = "Delete", style = typography.labelLarge, color = colors.error)
                    }
                }
            }
        }
    }
}
