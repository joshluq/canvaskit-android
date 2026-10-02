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
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonSize
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.inputs.CanvasKitOtpField
import es.joshluq.canvaskit.components.inputs.CanvasKitSearchField
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * AdvancedInputsScreen showcases OTP / PIN codes and Search fields with micro-animations.
 */
@Composable
fun AdvancedInputsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    // Interactive states
    var otpSms by remember { mutableStateOf("123") }
    var otpPin by remember { mutableStateOf("45") }
    var otpError by remember { mutableStateOf(false) }

    var searchQuery by remember { mutableStateOf("") }
    var isSearchLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundPrimary)
    ) {
        CanvasKitTopBar(
            title = {
                Text(
                    text = "OTP & Search Inputs",
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
            // 1. Search Bar
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Search Field",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Type text to see animated Clear button with spring physics:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitSearchField(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        placeholder = "Search transactions, users...",
                        isLoading = isSearchLoading
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitButton(
                            text = if (isSearchLoading) "Stop Loading" else "Simulate Async Loading",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Secondary,
                            onClick = { isSearchLoading = !isSearchLoading }
                        )
                    }
                }
            }

            // 2. OTP 6-Digit Code
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "OTP 6-Digit Verification Code",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Single consolidated field — supports SMS auto-fill & paste:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitOtpField(
                        value = otpSms,
                        onValueChange = { otpSms = it },
                        otpLength = 6
                    )

                    Text(
                        text = "Entered value: \"$otpSms\" (${otpSms.length}/6)",
                        style = typography.labelSmall,
                        color = colors.brandAccent
                    )
                }
            }

            // 3. Secret Masked PIN & Error State
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Secret Masked PIN (4 Digits)",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Digits are obscured with spring-animated secure dots:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitOtpField(
                        value = otpPin,
                        onValueChange = { otpPin = it },
                        otpLength = 4,
                        isMasked = true,
                        isError = otpError
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CanvasKitButton(
                            text = if (otpError) "Clear Error" else "Trigger Error State",
                            size = CanvasKitButtonSize.Small,
                            variant = CanvasKitButtonVariant.Secondary,
                            onClick = { otpError = !otpError }
                        )
                    }
                }
            }
        }
    }
}
