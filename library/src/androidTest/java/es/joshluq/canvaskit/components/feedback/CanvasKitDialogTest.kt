package es.joshluq.canvaskit.components.feedback

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanvasKitDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDialogRendersContentAndHandlesDismiss() {
        var dismissCalled = false

        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitDialog(
                    onDismissRequest = { dismissCalled = true }
                ) {
                    CanvasKitDialogContent(
                        title = { Text("Dialog Title") },
                        content = { Text("Dialog Content Body") },
                        buttons = {
                            CanvasKitButton(
                                onClick = { dismissCalled = true }
                            ) {
                                Text("Dismiss")
                            }
                        }
                    )
                }
            }
        }

        // Verify elements are displayed
        composeTestRule.onNodeWithText("Dialog Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dialog Content Body").assertIsDisplayed()

        // Click the action button
        composeTestRule.onNodeWithText("Dismiss").performClick()

        // Assert dismiss callback was triggered
        assertTrue(dismissCalled)
    }
}
