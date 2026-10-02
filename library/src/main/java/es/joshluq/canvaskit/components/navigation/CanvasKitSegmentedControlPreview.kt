package es.joshluq.canvaskit.components.navigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

private val sampleSegments = listOf(
    CanvasKitSegmentItem(id = "day", label = "Daily", icon = Icons.Default.DateRange),
    CanvasKitSegmentItem(id = "week", label = "Weekly", icon = Icons.Default.List),
    CanvasKitSegmentItem(id = "month", label = "Monthly", icon = Icons.Default.Person)
)

private val twoSegments = listOf(
    CanvasKitSegmentItem(id = "login", label = "Log In"),
    CanvasKitSegmentItem(id = "signup", label = "Sign Up")
)

@Composable
private fun SegmentedControlPreviewContent() {
    var selectedIndex1 by remember { mutableIntStateOf(0) }
    var selectedIndex2 by remember { mutableIntStateOf(1) }

    CanvasKitTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CanvasKitTheme.colors.backgroundPrimary)
                .padding(CanvasKitTheme.spacing.md),
            verticalArrangement = Arrangement.spacedBy(CanvasKitTheme.spacing.lg)
        ) {
            // Standard 3 items with icons
            CanvasKitSegmentedControl(
                items = sampleSegments,
                selectedIndex = selectedIndex1,
                onSegmentSelected = { selectedIndex1 = it }
            )

            // 2 items simple text
            CanvasKitSegmentedControl(
                items = twoSegments,
                selectedIndex = selectedIndex2,
                onSegmentSelected = { selectedIndex2 = it }
            )

            // Disabled state
            CanvasKitSegmentedControl(
                items = sampleSegments,
                selectedIndex = 0,
                onSegmentSelected = {},
                enabled = false
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun CanvasKitSegmentedControlLightPreview() {
    SegmentedControlPreviewContent()
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CanvasKitSegmentedControlDarkPreview() {
    SegmentedControlPreviewContent()
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
private fun CanvasKitSegmentedControlRtlPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        SegmentedControlPreviewContent()
    }
}

@Preview(name = "2.0x Font Scaling", showBackground = true, fontScale = 2.0f)
@Composable
private fun CanvasKitSegmentedControlFontScalePreview() {
    SegmentedControlPreviewContent()
}
