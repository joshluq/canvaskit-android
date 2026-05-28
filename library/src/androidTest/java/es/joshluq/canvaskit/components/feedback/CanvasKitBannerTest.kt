package es.joshluq.canvaskit.components.feedback

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanvasKitBannerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // ---------------------------------------------------------------------------
    // CanvasKitBanner Tests
    // ---------------------------------------------------------------------------

    @Test
    fun testBanner_visibleTrue_isDisplayed() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitBanner(
                    variant = CanvasKitAlertVariant.Info,
                    visible = true,
                    message = { Text("Banner visible message") }
                )
            }
        }
        composeTestRule.onNodeWithText("Banner visible message").assertIsDisplayed()
    }

    @Test
    fun testBanner_visibleFalse_isNotDisplayed() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitBanner(
                    variant = CanvasKitAlertVariant.Error,
                    visible = false,
                    message = { Text("Hidden banner message") }
                )
            }
        }
        composeTestRule.onNodeWithText("Hidden banner message").assertDoesNotExist()
    }

    @Test
    fun testBanner_dismissButton_triggersCallback() {
        var dismissed = false

        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitBanner(
                    variant = CanvasKitAlertVariant.Warning,
                    visible = true,
                    message = { Text("Dismissible banner") },
                    onDismiss = { dismissed = true }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Cerrar notificación").performClick()
        assertTrue("onDismiss callback should have been called", dismissed)
    }

    @Test
    fun testBanner_noDismissButton_whenOnDismissIsNull() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitBanner(
                    variant = CanvasKitAlertVariant.Success,
                    visible = true,
                    message = { Text("Non-dismissible banner") },
                    onDismiss = null
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("Cerrar notificación").assertDoesNotExist()
    }

    @Test
    fun testBanner_allVariants_renderWithoutException() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitAlertVariant.entries.forEach { variant ->
                    CanvasKitBanner(
                        variant = variant,
                        visible = true,
                        message = { Text("Message for ${variant.name}") }
                    )
                }
            }
        }

        CanvasKitAlertVariant.entries.forEach { variant ->
            composeTestRule.onNodeWithText("Message for ${variant.name}").assertIsDisplayed()
        }
    }

    @Test
    fun testBanner_titleAndMessage_bothDisplayed() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitBanner(
                    variant = CanvasKitAlertVariant.Error,
                    visible = true,
                    title = { Text("Error title") },
                    message = { Text("Error description") }
                )
            }
        }
        composeTestRule.onNodeWithText("Error title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Error description").assertIsDisplayed()
    }

    @Test
    fun testBanner_dismissThenHide_removesFromComposition() {
        composeTestRule.setContent {
            CanvasKitTheme {
                var visible by remember { mutableStateOf(true) }
                CanvasKitBanner(
                    variant = CanvasKitAlertVariant.Info,
                    visible = visible,
                    message = { Text("Toggle banner") },
                    onDismiss = { visible = false }
                )
            }
        }

        composeTestRule.onNodeWithText("Toggle banner").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Cerrar notificación").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Toggle banner").assertDoesNotExist()
    }

    // ---------------------------------------------------------------------------
    // CanvasKitInlineAlert Tests
    // ---------------------------------------------------------------------------

    @Test
    fun testInlineAlert_messageIsDisplayed() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitInlineAlert(
                    variant = CanvasKitAlertVariant.Info,
                    message = { Text("Inline alert message") }
                )
            }
        }
        composeTestRule.onNodeWithText("Inline alert message").assertIsDisplayed()
    }

    @Test
    fun testInlineAlert_titleAndMessage_bothDisplayed() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitInlineAlert(
                    variant = CanvasKitAlertVariant.Warning,
                    title = { Text("Warning title") },
                    message = { Text("This cannot be undone.") }
                )
            }
        }
        composeTestRule.onNodeWithText("Warning title").assertIsDisplayed()
        composeTestRule.onNodeWithText("This cannot be undone.").assertIsDisplayed()
    }

    @Test
    fun testInlineAlert_allVariants_renderWithoutException() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitAlertVariant.entries.forEach { variant ->
                    CanvasKitInlineAlert(
                        variant = variant,
                        message = { Text("Inline ${variant.name}") }
                    )
                }
            }
        }

        CanvasKitAlertVariant.entries.forEach { variant ->
            composeTestRule.onNodeWithText("Inline ${variant.name}").assertIsDisplayed()
        }
    }
}
