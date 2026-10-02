package es.joshluq.canvaskit.components.inputs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun OtpFieldPreviewContent() {
    var otpCode1 by remember { mutableStateOf("123") }
    var otpCode2 by remember { mutableStateOf("9876") }
    var otpCode3 by remember { mutableStateOf("45") }

    CanvasKitTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundPrimary)
                .padding(CanvasKitTheme.spacing.md),
            verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg)
        ) {
            Text(
                text = "6-Digit SMS Code",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitOtpField(
                value = otpCode1,
                onValueChange = { otpCode1 = it },
                otpLength = 6
            )

            Text(
                text = "4-Digit Masked Secret PIN",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitOtpField(
                value = otpCode2,
                onValueChange = { otpCode2 = it },
                otpLength = 4,
                isMasked = true
            )

            Text(
                text = "Error Validation State",
                style = CanvasKitTheme.typography.labelSmall,
                color = CanvasKitTheme.colors.textSecondary
            )
            CanvasKitOtpField(
                value = otpCode3,
                onValueChange = { otpCode3 = it },
                otpLength = 4,
                isError = true
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun CanvasKitOtpFieldLightPreview() {
    OtpFieldPreviewContent()
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CanvasKitOtpFieldDarkPreview() {
    OtpFieldPreviewContent()
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
private fun CanvasKitOtpFieldRtlPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        OtpFieldPreviewContent()
    }
}

@Preview(name = "2.0x Font Scaling", showBackground = true, fontScale = 2.0f)
@Composable
private fun CanvasKitOtpFieldFontScalePreview() {
    OtpFieldPreviewContent()
}
