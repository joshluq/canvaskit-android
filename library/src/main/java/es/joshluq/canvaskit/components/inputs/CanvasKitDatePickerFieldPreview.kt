package es.joshluq.canvaskit.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Preview(showBackground = true, name = "Empty State")
@Composable
fun CanvasKitDatePickerFieldEmptyPreview() {
    CanvasKitTheme {
        CanvasKitDatePickerField(
            label = "Birth Date",
            selectedDateMillis = null,
            onDateSelected = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, name = "Selected State")
@Composable
fun CanvasKitDatePickerFieldSelectedPreview() {
    CanvasKitTheme {
        CanvasKitDatePickerField(
            label = "Appointment",
            selectedDateMillis = 1724025600000L, // Aug 19, 2024
            onDateSelected = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, name = "Interactive Dark Mode")
@Composable
fun CanvasKitDatePickerFieldDarkPreview() {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    
    CanvasKitTheme(darkTheme = true) {
        Column(modifier = Modifier.padding(16.dp)) {
            CanvasKitDatePickerField(
                label = "Event Date",
                selectedDateMillis = selectedDate,
                onDateSelected = { selectedDate = it },
                helperText = "Select the date for your next event"
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            CanvasKitDatePickerField(
                label = "Disabled Field",
                selectedDateMillis = null,
                onDateSelected = {},
                enabled = false
            )
        }
    }
}
