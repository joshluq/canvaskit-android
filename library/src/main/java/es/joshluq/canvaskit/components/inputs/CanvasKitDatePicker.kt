package es.joshluq.canvaskit.components.inputs

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitDatePicker applies the precision tokens of the Atelier Design System
 * to the standard Material 3 DatePicker component.
 *
 * @param state state of the date picker. See [androidx.compose.material3.rememberDatePickerState].
 * @param modifier the [Modifier] to be applied to this date picker.
 * @param showModeToggle indicates if this DatePicker should show a mode toggle action that transforms it into a date input.
 * @param title the title to be displayed in the date picker.
 * @param headline the headline to be displayed in the date picker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CanvasKitDatePicker(
    state: DatePickerState,
    modifier: Modifier = Modifier,
    showModeToggle: Boolean = true,
    title: @Composable (() -> Unit)? = null,
    headline: @Composable (() -> Unit)? = null,
) {
    DatePicker(
        state = state,
        modifier = modifier,
        showModeToggle = showModeToggle,
        title = title,
        headline = headline,
        colors = canvasKitDatePickerColors()
    )
}

/**
 * CanvasKitDatePickerDialog wraps a [DatePicker] in a dialog with Atelier Design System styling.
 *
 * @param onDismissRequest called when the user taps outside the dialog or on the back button.
 * @param confirmButton button which is meant to confirm a proposed action, thus resolving the dialog's purpose.
 * @param modifier the [Modifier] to be applied to this dialog's content.
 * @param dismissButton button which is meant to dismiss the dialog.
 * @param shape defines the shape of this dialog's container.
 * @param tonalElevation when [shape] is a surface, its elevation will be recorded and used for drawing tonal color.
 * @param content the content of the dialog (usually a [CanvasKitDatePicker]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CanvasKitDatePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
    shape: Shape = CanvasKitTheme.shapes.container,
    tonalElevation: Dp = 6.dp, // Default M3 DatePicker elevation
    content: @Composable ColumnScope.() -> Unit
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        modifier = modifier,
        dismissButton = dismissButton,
        shape = shape,
        colors = DatePickerDefaults.colors(
            containerColor = CanvasKitTheme.colors.backgroundPrimary
        ),
        tonalElevation = tonalElevation,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun canvasKitDatePickerColors(): DatePickerColors {
    val colors = CanvasKitTheme.colors
    
    return DatePickerDefaults.colors(
        containerColor = colors.backgroundPrimary,
        titleContentColor = colors.textSecondary,
        headlineContentColor = colors.textPrimary,
        weekdayContentColor = colors.textSecondary,
        subheadContentColor = colors.textSecondary,
        navigationContentColor = colors.brandPrimary,
        yearContentColor = colors.textSecondary,
        currentYearContentColor = colors.brandAccent,
        selectedYearContentColor = colors.onBrandAccent,
        selectedYearContainerColor = colors.brandAccent,
        dayContentColor = colors.textPrimary,
        selectedDayContentColor = colors.onBrandAccent,
        selectedDayContainerColor = colors.brandAccent,
        todayContentColor = colors.brandAccent,
        dayInSelectionRangeContentColor = colors.brandAccent,
        dayInSelectionRangeContainerColor = colors.brandAccent.copy(alpha = 0.12f),
        todayDateBorderColor = colors.brandAccent,
        dateTextFieldColors = TextFieldDefaults.colors(
            focusedTextColor = colors.textPrimary,
            unfocusedTextColor = colors.textPrimary,
            focusedContainerColor = colors.backgroundPrimary,
            unfocusedContainerColor = colors.backgroundPrimary,
            cursorColor = colors.brandAccent,
            focusedIndicatorColor = colors.brandAccent,
            unfocusedIndicatorColor = colors.borderSubtle,
            focusedLabelColor = colors.brandAccent,
            unfocusedLabelColor = colors.textSecondary
            )
    )
}
