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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import es.joshluq.canvaskit.components.content.CanvasKitAvatar
import es.joshluq.canvaskit.components.content.CanvasKitAvatarData
import es.joshluq.canvaskit.components.content.CanvasKitAvatarGroup
import es.joshluq.canvaskit.components.content.CanvasKitAvatarPresence
import es.joshluq.canvaskit.components.content.CanvasKitAvatarSize
import es.joshluq.canvaskit.components.content.CanvasKitBadge
import es.joshluq.canvaskit.components.content.CanvasKitBadgedBox
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * AvatarsBadgesScreen showcases Avatars, Avatar Groups, Badges, and Badged Boxes.
 */
@Composable
fun AvatarsBadgesScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    // Interactive badge counters
    var notificationCount by remember { mutableIntStateOf(3) }
    var showDot by remember { mutableStateOf(true) }

    val teamMembers = remember {
        listOf(
            CanvasKitAvatarData(id = "1", name = "Josh Luque"),
            CanvasKitAvatarData(id = "2", name = "Alex Morgan"),
            CanvasKitAvatarData(id = "3", name = "Sarah Connor"),
            CanvasKitAvatarData(id = "4", name = "David Miller"),
            CanvasKitAvatarData(id = "5", name = "Elena Rostova"),
            CanvasKitAvatarData(id = "6", name = "Carlos Santana")
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
                    text = "Avatars & Badges",
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
            // 1. Avatars & Presence
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Avatars & Presence Indicators",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Sizes with Initials Fallback & Presence Rings:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.md),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitAvatar(
                            size = CanvasKitAvatarSize.Small,
                            name = "JL",
                            presence = CanvasKitAvatarPresence.Online
                        )
                        CanvasKitAvatar(
                            size = CanvasKitAvatarSize.Medium,
                            name = "Josh Luque",
                            presence = CanvasKitAvatarPresence.Online
                        )
                        CanvasKitAvatar(
                            size = CanvasKitAvatarSize.Large,
                            name = "Alex Morgan",
                            presence = CanvasKitAvatarPresence.Busy
                        )
                        CanvasKitAvatar(
                            size = CanvasKitAvatarSize.XLarge,
                            name = "Sarah Connor",
                            presence = CanvasKitAvatarPresence.Away
                        )
                    }

                    Spacer(modifier = Modifier.height(spacing.xs))

                    Text(
                        text = "Generic Fallback (No Name / Anonymous):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.md),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitAvatar(size = CanvasKitAvatarSize.Medium)
                        CanvasKitAvatar(
                            size = CanvasKitAvatarSize.Medium,
                            presence = CanvasKitAvatarPresence.Offline
                        )
                    }
                }
            }

            // 2. Avatar Group (Stack with Overlap)
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Avatar Group (Overlapping Stack)",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "6 Team Members with maxVisible = 4 (+2 Overflow Pill):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitAvatarGroup(
                        avatars = teamMembers,
                        size = CanvasKitAvatarSize.Medium,
                        maxVisible = 4
                    )

                    Spacer(modifier = Modifier.height(spacing.xs))

                    Text(
                        text = "Compact Small Stack (maxVisible = 3):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitAvatarGroup(
                        avatars = teamMembers,
                        size = CanvasKitAvatarSize.Small,
                        maxVisible = 3
                    )
                }
            }

            // 3. Badges and Badged Box
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Notification Badges (Pure Motion)",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Badged Icons (Interactive Counter):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.xl),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitBadgedBox(
                            badge = {
                                CanvasKitBadge(
                                    count = if (notificationCount > 0) notificationCount else null,
                                    visible = notificationCount > 0
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = colors.textPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        CanvasKitBadgedBox(
                            badge = { CanvasKitBadge(visible = showDot) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = colors.textPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        CanvasKitBadgedBox(
                            badge = { CanvasKitBadge(count = 120) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart",
                                tint = colors.textPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitButton(
                            text = "-1 Count",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Secondary,
                            onClick = { if (notificationCount > 0) notificationCount-- }
                        )
                        CanvasKitButton(
                            text = "+1 Count",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Secondary,
                            onClick = { notificationCount++ }
                        )
                        CanvasKitButton(
                            text = if (showDot) "Hide Dot" else "Show Dot",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Secondary,
                            onClick = { showDot = !showDot }
                        )
                    }
                }
            }
        }
    }
}
