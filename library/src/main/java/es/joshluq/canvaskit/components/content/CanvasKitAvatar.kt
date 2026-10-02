package es.joshluq.canvaskit.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import kotlin.math.abs

/**
 * Standard sizing definitions for [CanvasKitAvatar].
 */
@Immutable
enum class CanvasKitAvatarSize(
    val size: Dp,
    val fontSize: Int,
    val presenceDotSize: Dp,
    val presenceBorderWidth: Dp,
    val overlapOffset: Dp
) {
    Small(size = 32.dp, fontSize = 11, presenceDotSize = 8.dp, presenceBorderWidth = 1.5.dp, overlapOffset = (-8).dp),
    Medium(size = 40.dp, fontSize = 14, presenceDotSize = 10.dp, presenceBorderWidth = 2.dp, overlapOffset = (-10).dp),
    Large(size = 56.dp, fontSize = 18, presenceDotSize = 14.dp, presenceBorderWidth = 2.dp, overlapOffset = (-14).dp),
    XLarge(size = 72.dp, fontSize = 24, presenceDotSize = 18.dp, presenceBorderWidth = 2.5.dp, overlapOffset = (-18).dp)
}

/**
 * Presence badge status for [CanvasKitAvatar].
 */
@Immutable
enum class CanvasKitAvatarPresence {
    Online,
    Offline,
    Busy,
    Away
}

/**
 * Model item for [CanvasKitAvatarGroup].
 *
 * @param id Unique identifier.
 * @param name Full name used for initials fallback.
 * @param image Optional image slot.
 */
@Immutable
data class CanvasKitAvatarData(
    val id: String,
    val name: String? = null,
    val image: (@Composable () -> Unit)? = null
)

/**
 * CanvasKitAvatar is a premium representation of users, teams, or organizations.
 *
 * It uses a slot-based architecture with initials generation fallback and optional presence dots.
 *
 * ### Key Features:
 * - **Zero Vendor Lock-In:** Image slot allows using Coil, Glide, or local painters.
 * - **Smart Initials Fallback:** Extracts up to 2 uppercase initials with deterministic color tinting.
 * - **Presence Badges:** Supports Online, Away, Busy, Offline indicators with a cutout ring.
 * - **A11y Compliant:** TalkBack announces user name and presence status.
 *
 * @param modifier Root layout modifier.
 * @param size Predefined size ([CanvasKitAvatarSize.Small], [CanvasKitAvatarSize.Medium], [CanvasKitAvatarSize.Large], [CanvasKitAvatarSize.XLarge]).
 * @param name Optional user name used to derive initials (e.g. "Josh Luque" -> "JL").
 * @param presence Optional presence status dot.
 * @param image Optional image slot. When null, falls back to initials or generic person icon.
 */
@Composable
fun CanvasKitAvatar(
    modifier: Modifier = Modifier,
    size: CanvasKitAvatarSize = CanvasKitAvatarSize.Medium,
    name: String? = null,
    presence: CanvasKitAvatarPresence? = null,
    image: (@Composable () -> Unit)? = null
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes

    val initials = name?.let { extractInitials(it) }

    // Deterministic subtle pastel background for initials based on name hash
    val initialsBgColor = if (!name.isNullOrBlank()) {
        val colorPalette = listOf(
            colors.brandPrimary,
            colors.brandAccent,
            colors.warning,
            colors.success,
            colors.textSecondary
        )
        val hash = abs(name.hashCode()) % colorPalette.size
        colorPalette[hash].copy(alpha = 0.15f)
    } else {
        colors.backgroundSecondary
    }

    val initialsTextColor = if (!name.isNullOrBlank()) {
        colors.brandPrimary
    } else {
        colors.textSecondary
    }

    Box(
        modifier = modifier
            .size(size.size)
            .semantics(mergeDescendants = true) {
                contentDescription = buildString {
                    append(name ?: "User avatar")
                    if (presence != null) {
                        append(", ${presence.name}")
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        // Main Avatar Circle
        Box(
            modifier = Modifier
                .size(size.size)
                .clip(shapes.pill)
                .background(initialsBgColor),
            contentAlignment = Alignment.Center
        ) {
            when {
                image != null -> image()
                !initials.isNullOrEmpty() -> {
                    Text(
                        text = initials,
                        style = TextStyle(
                            fontSize = size.fontSize.sp,
                            fontWeight = FontWeight.Bold,
                            color = initialsTextColor
                        )
                    )
                }
                else -> {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = colors.textSecondary,
                        modifier = Modifier.size(size.size * 0.5f)
                    )
                }
            }
        }

        // Presence Status Indicator with Cutout Ring
        if (presence != null) {
            val presenceColor = when (presence) {
                CanvasKitAvatarPresence.Online -> colors.success
                CanvasKitAvatarPresence.Busy -> colors.error
                CanvasKitAvatarPresence.Away -> colors.warning
                CanvasKitAvatarPresence.Offline -> colors.textSecondary.copy(alpha = 0.5f)
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(size.presenceDotSize)
                    .clip(shapes.pill)
                    .background(presenceColor)
                    .border(
                        width = size.presenceBorderWidth,
                        color = colors.backgroundPrimary,
                        shape = shapes.pill
                    )
            )
        }
    }
}

/**
 * CanvasKitAvatarGroup displays a horizontal stack of overlapping avatars.
 *
 * When the list exceeds [maxVisible], renders a trailing "+X" count bubble.
 *
 * @param avatars List of avatar data items.
 * @param modifier Root layout modifier.
 * @param size Avatar size for all group members. Defaults to [CanvasKitAvatarSize.Medium].
 * @param maxVisible Maximum number of avatars to render before showing "+X". Defaults to 4.
 */
@Composable
fun CanvasKitAvatarGroup(
    avatars: List<CanvasKitAvatarData>,
    modifier: Modifier = Modifier,
    size: CanvasKitAvatarSize = CanvasKitAvatarSize.Medium,
    maxVisible: Int = 4
) {
    if (avatars.isEmpty()) return

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes

    val visibleAvatars = avatars.take(maxVisible)
    val overflowCount = avatars.size - maxVisible

    Row(
        modifier = modifier.semantics(mergeDescendants = true) {
            contentDescription = "Group of ${avatars.size} members"
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        visibleAvatars.forEachIndexed { index, avatar ->
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(x = if (index > 0) (size.overlapOffset * index).roundToPx() else 0, y = 0)
                    }
                    .border(width = 2.dp, color = colors.backgroundPrimary, shape = shapes.pill)
            ) {
                CanvasKitAvatar(
                    size = size,
                    name = avatar.name,
                    image = avatar.image
                )
            }
        }

        if (overflowCount > 0) {
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(x = (size.overlapOffset * visibleAvatars.size).roundToPx(), y = 0)
                    }
                    .size(size.size)
                    .clip(shapes.pill)
                    .background(colors.backgroundSecondary)
                    .border(width = 2.dp, color = colors.backgroundPrimary, shape = shapes.pill),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+$overflowCount",
                    style = TextStyle(
                        fontSize = (size.fontSize - 1).sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.brandPrimary
                    )
                )
            }
        }
    }
}

private fun extractInitials(name: String): String {
    val words = name.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }
    return when {
        words.isEmpty() -> ""
        words.size == 1 -> words[0].take(2).uppercase()
        else -> "${words[0].first()}${words[1].first()}".uppercase()
    }
}
