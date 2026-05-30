package es.joshluq.canvaskit.components.menus

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanvasKitMenuTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMenuDisplaysItemsWhenExpanded() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitDropdownMenu(
                    expanded = true,
                    onDismissRequest = {}
                ) {
                    CanvasKitDropdownMenuItem(
                        text = "Item 1",
                        onClick = {}
                    )
                    CanvasKitDropdownMenuItem(
                        text = "Item 2",
                        onClick = {}
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 2").assertIsDisplayed()
    }

    @Test
    fun testMenuItemClickTriggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitDropdownMenu(
                    expanded = true,
                    onDismissRequest = {}
                ) {
                    CanvasKitDropdownMenuItem(
                        text = "Clickable Item",
                        onClick = { clicked = true }
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Clickable Item").performClick()
        assertTrue(clicked)
    }

    @Test
    fun testDisabledMenuItemIsNotClickable() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitDropdownMenu(
                    expanded = true,
                    onDismissRequest = {}
                ) {
                    CanvasKitDropdownMenuItem(
                        text = "Disabled Item",
                        onClick = {},
                        enabled = false
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Disabled Item").assertIsNotEnabled()
    }

    @Test
    fun testMenuItemWithTrailingContent() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitDropdownMenu(
                    expanded = true,
                    onDismissRequest = {}
                ) {
                    CanvasKitDropdownMenuItem(
                        text = "Main Text",
                        onClick = {},
                        trailingContent = {
                            Text("Badge")
                        }
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Main Text").assertIsDisplayed()
        composeTestRule.onNodeWithText("Badge").assertIsDisplayed()
    }
}
