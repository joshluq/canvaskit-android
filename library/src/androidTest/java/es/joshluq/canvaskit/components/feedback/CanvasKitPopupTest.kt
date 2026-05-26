package es.joshluq.canvaskit.components.feedback

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanvasKitPopupTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPopupRendersContentWhenExpanded() {
        val expandedState = mutableStateOf(false)

        composeTestRule.setContent {
            CanvasKitTheme {
                Box {
                    CanvasKitPopup(
                        expanded = expandedState.value,
                        onDismissRequest = {}
                    ) {
                        Text("Popup Content Text")
                    }
                }
            }
        }

        // Verify popup is NOT displayed initially
        composeTestRule.onNodeWithText("Popup Content Text").assertDoesNotExist()

        // Toggle state to true
        composeTestRule.runOnUiThread {
            expandedState.value = true
        }

        // Wait for animations/layout pass
        composeTestRule.waitForIdle()

        // Verify popup is displayed
        composeTestRule.onNodeWithText("Popup Content Text").assertIsDisplayed()
    }
}
