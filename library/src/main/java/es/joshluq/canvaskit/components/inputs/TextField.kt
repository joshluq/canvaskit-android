package es.joshluq.canvaskit.components.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * Variants for the CanvasKitTextField component.
 */
enum class CanvasKitTextFieldVariant {
    Filled,
    Outlined
}

/**
 * CanvasKitTextField is a highly customizable modern text input component following the latest M3 guidelines.
 * Supports filled and outlined variations, floating animated labels, support text, error status, and custom icons.
 *
 * @param value Input text.
 * @param onValueChange Callback to fire when input text changes.
 * @param label Floating descriptor text inside/above input box.
 * @param modifier Root layout modifier.
 * @param variant Visual style variant (Filled, Outlined).
 * @param enabled Controls interactive capability and styling.
 * @param isError Sets the field into an error validation state.
 * @param helperText Informational support text displayed below the field.
 * @param errorText Error message displayed below when isError is true.
 * @param leadingIcon Start alignment icon slot.
 * @param trailingIcon End alignment action/icon slot.
 * @param visualTransformation Visual text filter (e.g. password masking).
 * @param keyboardOptions Soft keyboard layout configuration.
 * @param keyboardActions Action callbacks from keyboard.
 * @param singleLine Forces the field into a single line.
 * @param maxLines Maximum height constraints in lines.
 * @param interactionSource Interaction tracking source.
 */
@Composable
fun CanvasKitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    variant: CanvasKitTextFieldVariant = CanvasKitTextFieldVariant.Outlined,
    enabled: Boolean = true,
    isError: Boolean = false,
    helperText: String? = null,
    errorText: String? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography
    val motion = CanvasKitTheme.motion

    // Color definitions based on state
    val containerBgColor = when (variant) {
        CanvasKitTextFieldVariant.Filled -> if (enabled) {
            colors.backgroundSecondary
        } else {
            colors.backgroundSecondary.copy(
                alpha = 0.5f
            )
        }
        CanvasKitTextFieldVariant.Outlined -> Color.Transparent
    }

    val borderColor by animateColorAsState(
        targetValue = when {
            isError -> colors.error
            isFocused -> colors.brandAccent
            else -> colors.borderSubtle
        },
        animationSpec = tween(durationMillis = motion.short2, easing = motion.standard),
        label = "BorderColor"
    )

    val borderWidth = if (isFocused || isError) 2.dp else 1.dp
    val textAlpha = if (enabled) 1.0f else 0.38f

    Column(
        modifier = modifier
            .fillMaxWidth()
            .alpha(textAlpha)
    ) {
        if (!label.isNullOrEmpty()) {
            Text(
                text = label,
                color = if (isError) colors.error else colors.textPrimary,
                style = typography.labelSmall,
                modifier = Modifier.padding(bottom = spacing.xs, start = spacing.lg)
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { isFocused = it.isFocused },
            enabled = enabled,
            textStyle = typography.bodyMedium.copy(
                color = if (enabled) colors.textPrimary else colors.textSecondary
            ),
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(colors.brandAccent),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(shapes.pill)
                        .background(containerBgColor)
                        .then(
                            if (variant == CanvasKitTextFieldVariant.Outlined || isFocused || isError) {
                                Modifier.border(BorderStroke(borderWidth, borderColor), shapes.pill)
                            } else {
                                Modifier
                            }
                        )
                        .padding(horizontal = spacing.lg),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (leadingIcon != null) {
                            Box(modifier = Modifier.padding(end = spacing.xs)) {
                                leadingIcon()
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            // Static Placeholder
                            if (value.isEmpty() && !placeholder.isNullOrEmpty()) {
                                Text(
                                    text = placeholder,
                                    color = colors.textSecondary,
                                    style = typography.bodyMedium
                                )
                            }

                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                innerTextField()
                            }
                        }

                        if (trailingIcon != null) {
                            Box(modifier = Modifier.padding(start = spacing.xs)) {
                                trailingIcon()
                            }
                        }
                    }
                }
            }
        )

        // Helper and Error Sub-Layout
        val supportText = if (isError && !errorText.isNullOrEmpty()) errorText else helperText
        val supportColor = if (isError) colors.error else colors.textSecondary

        if (!supportText.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(spacing.xxs))
            Text(
                text = supportText,
                color = supportColor,
                style = typography.labelSmall,
                modifier = Modifier.padding(horizontal = spacing.lg)
            )
        }
    }
}
