package es.joshluq.canvaskit.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanvasKitLoadingScaffoldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun strategyReplace_hidesContent_whenLoading() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitLoadingScaffold(
                    isLoading = true,
                    loadingStrategy = CanvasKitLoadingStrategy.ReplaceContent
                ) {
                    Text("Hidden Content")
                }
            }
        }

        // When replacing content, the content should not be in the hierarchy while loading
        composeTestRule.onNodeWithText("Hidden Content").assertDoesNotExist()
    }

    @Test
    fun strategyReplace_showsContent_whenNotLoading() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitLoadingScaffold(
                    isLoading = false,
                    loadingStrategy = CanvasKitLoadingStrategy.ReplaceContent
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        Text("Visible Content")
                    }
                }
            }
        }

        // Content should be visible
        composeTestRule.onNodeWithText("Visible Content").assertIsDisplayed()
    }

    @Test
    fun strategyOverlay_keepsContentVisible_whenLoading() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitLoadingScaffold(
                    isLoading = true,
                    loadingStrategy = CanvasKitLoadingStrategy.OverlayFullscreen
                ) {
                    Text("Background Content")
                }
            }
        }

        // In overlay strategy, the content remains under the dialog
        composeTestRule.onNodeWithText("Background Content").assertIsDisplayed()
    }
}
