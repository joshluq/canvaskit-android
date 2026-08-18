package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.inputs.CanvasKitDatePicker
import es.joshluq.canvaskit.components.inputs.CanvasKitDatePickerDialog
import es.joshluq.canvaskit.components.inputs.CanvasKitDatePickerField
import es.joshluq.canvaskit.components.layout.CanvasKitLoadingScaffold
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val inlineDatePickerState = rememberDatePickerState()
    var fieldDateMillis by remember { mutableStateOf<Long?>(null) }
    
    val dateFormatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
    val selectedDateText = datePickerState.selectedDateMillis?.let {
        dateFormatter.format(Date(it))
    } ?: "None selected"

    CanvasKitLoadingScaffold(
        isLoading = false,
        modifier = modifier.fillMaxSize(),
        topBar = {
            CanvasKitTopBar(
                title = {
                    Column {
                        Text(
                            text = "Date Selectors",
                            style = CanvasKitTheme.typography.headingLarge,
                            color = colors.textPrimary
                        )
                        Text(
                            text = "Premium and accessible temporal inputs.",
                            style = CanvasKitTheme.typography.labelSmall,
                            color = colors.textSecondary
                        )
                    }
                },
                navigationIcon = {
                    CanvasKitIconButton(
                        onClick = onBack,
                        contentDescription = "Back"
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.backgroundSecondary)
                    .verticalScroll(rememberScrollState())
                    .padding(spacing.md),
                verticalArrangement = Arrangement.spacedBy(spacing.lg)
            ) {
                // Introduction
                Column(modifier = Modifier.padding(horizontal = spacing.xs)) {
                    Text(
                        text = "Temporal\nPrecision",
                        style = CanvasKitTheme.typography.displayMedium,
                        color = colors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(spacing.sm))
                    Text(
                        text = "Refined date pickers that maintain the Atelier visual harmony while providing a technical feel for data entry.",
                        style = CanvasKitTheme.typography.bodyLarge,
                        color = colors.textSecondary
                    )
                }

                // Section: Dialog Picker
                SpecSectionCard(
                    title = "Modal Picker",
                    description = "Focused temporal selection in an exclusive container."
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                        Text(
                            text = "Selected date: $selectedDateText",
                            style = CanvasKitTheme.typography.bodyMedium,
                            color = colors.textPrimary
                        )
                        CanvasKitButton(onClick = { showDialog = true }) {
                            Text(
                                "Open Date Picker",
                                style = CanvasKitTheme.typography.labelLarge
                            )
                        }
                    }
                }

                // Section: Inline Picker
                SpecSectionCard(
                    title = "Inline Picker",
                    description = "Embedded date selector for direct surface interaction."
                ) {
                    CanvasKitDatePicker(
                        state = inlineDatePickerState,
                        showModeToggle = false,
                        title = null,
                        headline = null
                    )
                }

                // Section: Picker Field
                SpecSectionCard(
                    title = "Picker Field",
                    description = "Combination of a read-only text input and a modal selector."
                ) {
                    CanvasKitDatePickerField(
                        label = "Event Date",
                        selectedDateMillis = fieldDateMillis,
                        onDateSelected = { fieldDateMillis = it },
                        placeholder = "Choose a date..."
                    )
                }
            }
        }

        if (showDialog) {
            CanvasKitDatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    CanvasKitButton(
                        onClick = { showDialog = false },
                        variant = CanvasKitButtonVariant.Ghost
                    ) {
                        Text(
                            "Confirm",
                            style = CanvasKitTheme.typography.labelLarge,
                            color = colors.brandAccent
                        )
                    }
                },
                dismissButton = {
                    CanvasKitButton(
                        onClick = { showDialog = false },
                        variant = CanvasKitButtonVariant.Ghost
                    ) {
                        Text(
                            "Cancel",
                            style = CanvasKitTheme.typography.labelLarge,
                            color = colors.textSecondary
                        )
                    }
                }
            ) {
                CanvasKitDatePicker(state = datePickerState)
            }
        }
    }
}
