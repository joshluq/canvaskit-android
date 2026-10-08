package es.joshluq.canvaskit.components.inputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.feedback.CanvasKitCircularProgressBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitSearchField is an artisanal search input bar adhering to Material 3 Expressive guidelines.
 *
 * It features a pill silhouette, integrated search icon, spring-animated clear action,
 * and loading indicator state.
 *
 * ### Key Features:
 * - **Pure Motion Transitions:** The clear button pops in and out using spring physics.
 * - **Built-in Async Loading:** Replaces clear icon with [CanvasKitCircularProgressBar] while searching.
 * - **Touch Target Guarantee:** Guarantees minimum 48dp height without text clipping at 2.0x font scaling.
 * - **A11y Compliant:** Supports [ImeAction.Search], hardware navigation, and screen reader announcements.
 *
 * @param query Current search query text.
 * @param onQueryChange Callback invoked when query text changes.
 * @param modifier Root layout modifier.
 * @param placeholder Hint text displayed when input is empty. Defaults to "Search...".
 * @param isLoading When true, displays a progress spinner inside the trailing slot.
 * @param enabled Whether the search field is interactive.
 * @param onSearch Optional callback triggered when the user presses the search IME action on the keyboard.
 * @param onClear Optional callback triggered when the clear ("X") button is tapped.
 */
@Composable
fun CanvasKitSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search...",
    isLoading: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onSearch: ((String) -> Unit)? = null,
    onClear: (() -> Unit)? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val stroke = CanvasKitTheme.stroke
    val opacity = CanvasKitTheme.opacity
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography
    val motion = CanvasKitTheme.motion

    val contentAlpha = if (enabled) opacity.full else opacity.disabled

    val borderColor by animateColorAsState(
        targetValue = if (isFocused) colors.brandAccent else colors.borderSubtle,
        animationSpec = tween(durationMillis = motion.short2),
        label = "SearchBorderColor"
    )

    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        enabled = enabled,
        singleLine = true,
        textStyle = typography.bodyLarge.copy(color = colors.textPrimary),
        cursorBrush = SolidColor(colors.brandAccent),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch?.invoke(query) }),
        interactionSource = interactionSource,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused }
            .graphicsLayer { alpha = contentAlpha },
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 48.dp)
                    .clip(shapes.pill)
                    .background(colors.backgroundSecondary)
                    .border(
                        width = stroke.thin,
                        color = borderColor,
                        shape = shapes.pill
                    )
                    .padding(horizontal = spacing.md, vertical = spacing.xs),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Leading Search Icon
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = if (isFocused) colors.brandAccent else colors.textSecondary,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = spacing.xs)
                    )

                    // Text Field and Placeholder Area
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = spacing.xs),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (query.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = typography.bodyLarge,
                                color = colors.textSecondary.copy(alpha = 0.7f)
                            )
                        }
                        innerTextField()
                    }

                    // Trailing Loading Spinner or Clear Button
                    if (isLoading) {
                        CanvasKitCircularProgressBar(
                            progress = null,
                            size = 20.dp,
                            strokeWidth = 2.dp,
                            color = colors.brandAccent,
                            modifier = Modifier.padding(start = spacing.xs)
                        )
                    } else {
                        val springSpec = spring<Float>(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMedium
                        )

                        AnimatedVisibility(
                            visible = query.isNotEmpty(),
                            enter = fadeIn() + scaleIn(springSpec),
                            exit = fadeOut() + scaleOut()
                        ) {
                            CanvasKitIconButton(
                                onClick = {
                                    onQueryChange("")
                                    onClear?.invoke()
                                },
                                contentDescription = "Clear search",
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = colors.textSecondary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
