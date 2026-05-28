package es.joshluq.canvaskit.components.chips

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasText
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
class CanvasKitChipTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun chip_rendersLabelAndClickable() {
        var clicked = false
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitChip(
                    onClick = { clicked = true },
                    label = { Text("Filter") }
                )
            }
        }

        composeTestRule.onNodeWithText("Filter")
            .assertIsDisplayed()
            .assertHasClickAction()
            .performClick()

        assertTrue(clicked)
    }

    @Test
    fun chip_selectedState_exposedInSemantics() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitChip(
                    onClick = {},
                    selected = true,
                    label = { Text("Selected Chip") }
                )
            }
        }

        composeTestRule.onNodeWithText("Selected Chip")
            .assertIsSelected()
    }

    @Test
    fun chip_unselectedState_exposedInSemantics() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitChip(
                    onClick = {},
                    selected = false,
                    label = { Text("Unselected Chip") }
                )
            }
        }

        composeTestRule.onNodeWithText("Unselected Chip")
            .assertIsNotSelected()
    }

    @Test
    fun chip_leadingAndTrailingIcons_areDisplayed() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitChip(
                    onClick = {},
                    leadingIcon = { Text("L_ICON") },
                    trailingIcon = { Text("T_ICON") },
                    label = { Text("Chip with Icons") }
                )
            }
        }

        composeTestRule.onNodeWithText("L_ICON").assertIsDisplayed()
        composeTestRule.onNodeWithText("Chip with Icons").assertIsDisplayed()
        composeTestRule.onNodeWithText("T_ICON").assertIsDisplayed()
    }

    @Test
    fun chip_allVariantsRender_withoutException() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitChipVariant.entries.forEach { variant ->
                    CanvasKitChip(
                        onClick = {},
                        variant = variant,
                        label = { Text("Variant ${variant.name}") }
                    )
                }
            }
        }

        CanvasKitChipVariant.entries.forEach { variant ->
            composeTestRule.onNodeWithText("Variant ${variant.name}").assertIsDisplayed()
        }
    }
}
