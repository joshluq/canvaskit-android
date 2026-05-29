package es.joshluq.canvaskit.components.navigation

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanvasKitTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTopBarRendersTitleAndElements() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitTopBar(
                    title = { Text("App Title") },
                    navigationIcon = { Text("BackIcon") },
                    actions = { Text("SettingsAction") }
                )
            }
        }

        // Verify elements are displayed
        composeTestRule.onNodeWithText("App Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("BackIcon").assertIsDisplayed()
        composeTestRule.onNodeWithText("SettingsAction").assertIsDisplayed()
    }

    @Test
    fun testTopBarCenteredTitleRenders() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitTopBar(
                    title = { Text("Centered Title") },
                    centeredTitle = true
                )
            }
        }

        composeTestRule.onNodeWithText("Centered Title").assertIsDisplayed()
    }

    @Test
    fun testTopBarHandlesNavigationAndActionClicks() {
        var navClicked = false
        var actionClicked = false

        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitTopBar(
                    title = { Text("Interactive TopBar") },
                    navigationIcon = {
                        CanvasKitIconButton(
                            onClick = { navClicked = true },
                            contentDescription = "Back"
                        ) {
                            Text("Back")
                        }
                    },
                    actions = {
                        CanvasKitIconButton(
                            onClick = { actionClicked = true },
                            contentDescription = "Save"
                        ) {
                            Text("Save")
                        }
                    }
                )
            }
        }

        // Click navigation icon
        composeTestRule.onNodeWithText("Back").performClick()
        assertTrue(navClicked)

        // Click action button
        composeTestRule.onNodeWithText("Save").performClick()
        assertTrue(actionClicked)
    }
}
