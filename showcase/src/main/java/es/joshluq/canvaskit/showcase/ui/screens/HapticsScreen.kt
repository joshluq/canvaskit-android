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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
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
import es.joshluq.canvaskit.components.inputs.CanvasKitCheckbox
import es.joshluq.canvaskit.components.inputs.CanvasKitOtpField
import es.joshluq.canvaskit.components.inputs.CanvasKitSwitch
import es.joshluq.canvaskit.components.navigation.CanvasKitSegmentItem
import es.joshluq.canvaskit.components.navigation.CanvasKitSegmentedControl
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * HapticsScreen showcases and tests the CanvasKit expressive haptic patterns.
 */
@Composable
fun HapticsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography
    val haptics = CanvasKitTheme.haptics

    var lastTriggered by remember { mutableStateOf("Tap any button below to feel the haptic pulse") }

    // Interactive component states
    var switchState by remember { mutableStateOf(true) }
    var checkboxState by remember { mutableStateOf(true) }
    var segmentIndex by remember { mutableIntStateOf(0) }
    var otpValue by remember { mutableStateOf("12") }

    val segments = remember {
        listOf(
            CanvasKitSegmentItem(id = "1", label = "Low"),
            CanvasKitSegmentItem(id = "2", label = "Medium"),
            CanvasKitSegmentItem(id = "3", label = "High")
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
                    text = "Haptics Lab",
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
            // Live Status Banner
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                    Text(
                        text = "LAST HAPTIC EVENT",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    Text(
                        text = lastTriggered,
                        style = typography.bodyLarge,
                        color = colors.brandAccent
                    )
                }
            }

            // 1. Direct Pattern Triggers
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Semantic Haptic Patterns",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Test each physical vibration profile directly:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                    ) {
                        CanvasKitButton(
                            text = "Click",
                            size = CanvasKitButtonSize.Medium,
                            variant = CanvasKitButtonVariant.Secondary,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                haptics.click()
                                lastTriggered = "haptics.click() — Keyboard/Toggle Tap"
                            }
                        )
                        CanvasKitButton(
                            text = "Tick",
                            size = CanvasKitButtonSize.Medium,
                            variant = CanvasKitButtonVariant.Secondary,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                haptics.tick()
                                lastTriggered = "haptics.tick() — Segment/Clock Tick"
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                    ) {
                        CanvasKitButton(
                            text = "Success",
                            size = CanvasKitButtonSize.Medium,
                            variant = CanvasKitButtonVariant.Primary,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                haptics.success()
                                lastTriggered = "haptics.success() — Positive Confirmation Pulse"
                            }
                        )
                        CanvasKitButton(
                            text = "Error",
                            size = CanvasKitButtonSize.Medium,
                            variant = CanvasKitButtonVariant.Ghost,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                haptics.error()
                                lastTriggered = "haptics.error() — Rejection Buzz"
                            }
                        )
                    }

                    CanvasKitButton(
                        text = "Gesture Threshold Snap",
                        size = CanvasKitButtonSize.Medium,
                        variant = CanvasKitButtonVariant.Secondary,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            haptics.gestureThreshold()
                            lastTriggered = "haptics.gestureThreshold() — Threshold Activation Snap"
                        }
                    )
                }
            }

            // 2. Components With Built-in Haptics
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Integrated Components",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "These components automatically trigger haptics on interaction:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )

                    // Segmented Control
                    CanvasKitSegmentedControl(
                        items = segments,
                        selectedIndex = segmentIndex,
                        onSegmentSelected = {
                            segmentIndex = it
                            lastTriggered = "SegmentedControl: option ${it + 1} clicked (haptics.click())"
                        }
                    )

                    Spacer(modifier = Modifier.height(spacing.xs))

                    // Switch & Checkbox Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CanvasKitSwitch(
                                checked = switchState,
                                onCheckedChange = {
                                    switchState = it
                                    lastTriggered = "Switch toggled to $it (haptics.click())"
                                }
                            )
                            Text(
                                text = " Switch",
                                style = typography.bodyMedium,
                                color = colors.textPrimary,
                                modifier = Modifier.padding(start = spacing.xs)
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CanvasKitCheckbox(
                                checked = checkboxState,
                                onCheckedChange = {
                                    checkboxState = it
                                    lastTriggered = "Checkbox toggled to $it (haptics.click())"
                                }
                            )
                            Text(
                                text = " Checkbox",
                                style = typography.bodyMedium,
                                color = colors.textPrimary,
                                modifier = Modifier.padding(start = spacing.xs)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(spacing.xs))

                    // OTP Input
                    Text(
                        text = "OTP Input (haptics on each digit & on completion):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitOtpField(
                        value = otpValue,
                        onValueChange = {
                            otpValue = it
                            lastTriggered = "OTP: entered digit (length ${it.length}/4)"
                        },
                        otpLength = 4,
                        onComplete = {
                            lastTriggered = "OTP: completed! (haptics.success())"
                        }
                    )
                }
            }
        }
    }
}
