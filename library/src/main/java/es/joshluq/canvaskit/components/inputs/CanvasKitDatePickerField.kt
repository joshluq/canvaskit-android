package es.joshluq.canvaskit.components.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * CanvasKitDatePickerField combines a read-only [CanvasKitTextField] with a [CanvasKitDatePickerDialog].
 * It simplifies the implementation of date selection in forms by handling the dialog state and
 * providing a consistent visual entry point.
 *
 * ### Why use this instead of manual DatePicker?
 * This component manages the selection lifecycle automatically. It provides a standard hit-target
 * that triggers a modal picker and formats the result back into the field, ensuring consistency
 * across all date-entry points in the app.
 *
 * @param label Descriptor text above the input.
 * @param selectedDateMillis The currently selected date in milliseconds (UTC).
 * @param onDateSelected Callback fired when a date is picked and confirmed.
 * @param modifier Root modifier.
 * @param placeholder Text shown when no date is selected.
 * @param dateFormat Pattern used to format the date in the field (e.g., "dd/MM/yyyy").
 * @param isError Sets the field into an error state.
 * @param errorText Error message displayed below when isError is true.
 * @param helperText Informational support text.
 * @param enabled Controls interactivity.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CanvasKitDatePickerField(
    label: String,
    selectedDateMillis: Long?,
    onDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Select date",
    dateFormat: String = "dd/MM/yyyy",
    isError: Boolean = false,
    errorText: String? = null,
    helperText: String? = null,
    enabled: Boolean = true
) {
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedDateMillis)

    val formattedDate = remember(selectedDateMillis, dateFormat) {
        selectedDateMillis?.let {
            val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            sdf.format(Date(it))
        } ?: ""
    }

    Box(modifier = modifier) {
        CanvasKitTextField(
            value = formattedDate,
            onValueChange = {},
            label = label,
            placeholder = placeholder,
            readOnly = true,
            enabled = enabled,
            isError = isError,
            errorText = errorText,
            helperText = helperText,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Open date picker"
                )
            }
        )

        // Hit-test overlay to capture clicks reliably on the whole field
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable(enabled = enabled) {
                    showDialog = true
                }
        )

        if (showDialog) {
            CanvasKitDatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    CanvasKitButton(
                        variant = CanvasKitButtonVariant.Ghost,
                        text = "OK",
                        onClick = {
                            onDateSelected(datePickerState.selectedDateMillis)
                            showDialog = false
                        }
                    )
                },
                dismissButton = {
                    CanvasKitButton(
                        variant = CanvasKitButtonVariant.Ghost,
                        text = "Cancel",
                        onClick = { showDialog = false }
                    )
                }
            ) {
                CanvasKitDatePicker(state = datePickerState)
            }
        }
    }
}
