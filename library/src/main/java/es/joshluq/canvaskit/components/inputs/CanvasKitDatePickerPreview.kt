package es.joshluq.canvaskit.components.inputs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.setSelectedDate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import java.time.LocalDate

/**
 * Multi-preview configuration for CanvasKit components.
 */
@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
internal annotation class CanvasKitComponentPreview

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@CanvasKitComponentPreview
@Composable
private fun CanvasKitDatePickerPreview() {
    CanvasKitTheme {
        val datePickerState = rememberDatePickerState()
        datePickerState.setSelectedDate(LocalDate.now())
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundSecondary)
                .padding(CanvasKitTheme.spacing.md)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CanvasKitTheme.colors.backgroundPrimary)
            ) {
                CanvasKitDatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                    title = null,
                    headline = null
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Font Scaling", fontScale = 1.5f)
@Composable
private fun CanvasKitDatePickerFontScalePreview() {
    CanvasKitTheme {
        val datePickerState = rememberDatePickerState()
        datePickerState.setSelectedDate(LocalDate.now())
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundPrimary)
        ) {
            CanvasKitDatePicker(state = datePickerState)
        }
    }
}
