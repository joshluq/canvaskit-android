package es.joshluq.canvaskit.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasStateDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanvasKitCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCardRendersHeaderContentAndFooter() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitCard(
                    header = { Text("Header Text") },
                    footer = { Text("Footer Text") }
                ) {
                    Text("Body Content")
                }
            }
        }

        composeTestRule.onNodeWithText("Header Text").assertIsDisplayed()
        composeTestRule.onNodeWithText("Body Content").assertIsDisplayed()
        composeTestRule.onNodeWithText("Footer Text").assertIsDisplayed()
    }

    @Test
    fun testCardHandlesClicksWhenInteractive() {
        var clicked = false
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitCard(
                    onClick = { clicked = true }
                ) {
                    Text("Clickable Card")
                }
            }
        }

        // Verify click action is present
        composeTestRule.onNodeWithText("Clickable Card").assertHasClickAction()

        // Perform click
        composeTestRule.onNodeWithText("Clickable Card").performClick()

        // Assert callback triggered
        assertTrue(clicked)
    }

    @Test
    fun testCardDoesNotClickWhenDisabled() {
        var clicked = false
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitCard(
                    onClick = { clicked = true },
                    enabled = false
                ) {
                    Text("Disabled Card")
                }
            }
        }

        // Try to perform click
        composeTestRule.onNodeWithText("Disabled Card").performClick()

        // Assert callback NOT triggered
        assertFalse(clicked)
    }

    @Test
    fun testCardSelectionSemantics() {
        composeTestRule.setContent {
            CanvasKitTheme {
                Column {
                    CanvasKitCard(
                        selected = true
                    ) {
                        Text("Selected Card")
                    }
                    CanvasKitCard(
                        selected = false
                    ) {
                        Text("Unselected Card")
                    }
                }
            }
        }

        // Verify state description
        composeTestRule.onNodeWithText("Selected Card")
            .assert(hasStateDescription("Seleccionado"))

        composeTestRule.onNodeWithText("Unselected Card")
            .assert(hasStateDescription("No seleccionado"))
    }
}
