package es.joshluq.canvaskit.components.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitOtpField is an artisanal one-time passcode / PIN input component.
 *
 * It uses a single consolidated native input under the hood to ensure full compatibility
 * with hardware keyboards, SMS auto-fill, clipboard paste, and third-party soft keyboards,
 * while rendering separate high-craft tactile cells.
 *
 * ### Key Features:
 * - **SMS Auto-Fill Friendly:** Uses a single underlying input to never drop characters.
 * - **Pure Motion Springs:** Active cell indicator and digit entry pop with organic spring dynamics.
 * - **Masked Mode:** Supports secret PIN entry with animated dot masks.
 * - **A11y Compliant:** 48dp+ cell targets and unified screen reader semantics.
 *
 * @param value Current string value of the OTP/PIN.
 * @param onValueChange Callback invoked when the code changes. Only receives digits up to [otpLength].
 * @param modifier Root layout modifier.
 * @param otpLength Number of code cells (typically 4 or 6). Defaults to 6.
 * @param isMasked When true, obscures the digits with secure dots.
 * @param isError Renders the cells in an error validation state.
 * @param enabled Whether the field is interactive.
 * @param cellWidth Custom width for each cell. Defaults to 46.dp.
 * @param cellHeight Custom height for each cell. Defaults to 56.dp.
 * @param onComplete Optional callback fired immediately when all [otpLength] digits are entered.
 */
@Composable
fun CanvasKitOtpField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    otpLength: Int = 6,
    isMasked: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    cellWidth: Dp = 46.dp,
    cellHeight: Dp = 56.dp,
    onComplete: ((String) -> Unit)? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val stroke = CanvasKitTheme.stroke
    val opacity = CanvasKitTheme.opacity
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    val contentAlpha = if (enabled) opacity.full else opacity.disabled

    BasicTextField(
        value = value,
        onValueChange = { input ->
            // Only accept digits up to otpLength
            val filtered = input.filter { it.isDigit() }.take(otpLength)
            onValueChange(filtered)
            if (filtered.length == otpLength) {
                onComplete?.invoke(filtered)
            }
        },
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isMasked) KeyboardType.NumberPassword else KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (value.length == otpLength) {
                    onComplete?.invoke(value)
                }
            }
        ),
        cursorBrush = SolidColor(Color.Transparent), // Hide native cursor; cells visually indicate focus
        modifier = modifier
            .onFocusChanged { isFocused = it.isFocused }
            .graphicsLayer { alpha = contentAlpha },
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.xs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (index in 0 until otpLength) {
                    val char = value.getOrNull(index)
                    val isCellActive = isFocused && (index == value.length || (index == otpLength - 1 && value.length == otpLength))
                    val hasChar = char != null

                    // Animated cell border color
                    val borderColor by animateColorAsState(
                        targetValue = when {
                            isError -> colors.error
                            isCellActive -> colors.brandAccent
                            hasChar -> colors.textPrimary.copy(alpha = 0.5f)
                            else -> colors.borderSubtle
                        },
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        ),
                        label = "OtpCellBorderColor_$index"
                    )

                    // Animated cell background
                    val cellBgColor = when {
                        isError -> colors.errorContainer.copy(alpha = 0.15f)
                        isCellActive -> colors.brandAccent.copy(alpha = 0.05f)
                        else -> colors.backgroundSecondary
                    }

                    // Digit entry scale spring animation
                    val digitScale by animateFloatAsState(
                        targetValue = if (hasChar) 1f else 0.5f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMedium
                        ),
                        label = "OtpDigitScale_$index"
                    )

                    Box(
                        modifier = Modifier
                            .width(cellWidth)
                            .height(cellHeight)
                            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                            .clip(shapes.container)
                            .background(cellBgColor)
                            .border(
                                width = if (isCellActive || isError) stroke.thick else stroke.thin,
                                color = borderColor,
                                shape = shapes.container
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (hasChar) {
                            if (isMasked) {
                                // Masked bullet dot
                                Box(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .graphicsLayer {
                                            scaleX = digitScale
                                            scaleY = digitScale
                                        }
                                        .clip(shapes.pill)
                                        .background(if (isError) colors.error else colors.textPrimary)
                                )
                            } else {
                                // Plain text digit
                                Text(
                                    text = char.toString(),
                                    style = typography.headingLarge.copy(fontWeight = FontWeight.Bold),
                                    color = if (isError) colors.error else colors.textPrimary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.graphicsLayer {
                                        scaleX = digitScale
                                        scaleY = digitScale
                                    }
                                )
                            }
                        } else if (isCellActive) {
                            // Subtle flashing/steady cursor indicator bar in active empty cell
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .clip(shapes.pill)
                                    .background(colors.brandAccent)
                            )
                        }
                    }
                }
            }
        }
    )
}
