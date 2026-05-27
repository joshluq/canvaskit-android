package es.joshluq.canvaskit.components.inputs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.semantics.Role
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanvasKitTogglesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSwitchToggleBehavior() {
        var switchState = false

        composeTestRule.setContent {
            var checked by remember { mutableStateOf(false) }
            CanvasKitTheme {
                CanvasKitSwitch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        switchState = it
                    }
                )
            }
        }

        // Verify initial state via Switch Role
        val switchNode = composeTestRule.onNode(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Switch))
        switchNode.assertIsOff()

        // Perform click and check new state
        switchNode.performClick()
        switchNode.assertIsOn()
        assertTrue(switchState)

        // Perform click again and check toggle back
        switchNode.performClick()
        switchNode.assertIsOff()
        assertFalse(switchState)
    }

    @Test
    fun testCheckboxToggleBehavior() {
        var checkboxState = false

        composeTestRule.setContent {
            var checked by remember { mutableStateOf(false) }
            CanvasKitTheme {
                CanvasKitCheckbox(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        checkboxState = it
                    }
                )
            }
        }

        // Verify initial state via Checkbox Role
        val checkboxNode = composeTestRule.onNode(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Checkbox))
        checkboxNode.assertIsOff()

        // Perform click and check new state
        checkboxNode.performClick()
        checkboxNode.assertIsOn()
        assertTrue(checkboxState)
    }

    @Test
    fun testRadioButtonSelectionBehavior() {
        var selectedState = false

        composeTestRule.setContent {
            var selected by remember { mutableStateOf(false) }
            CanvasKitTheme {
                CanvasKitRadioButton(
                    selected = selected,
                    onClick = {
                        selected = true
                        selectedState = true
                    }
                )
            }
        }

        // Verify initial state via RadioButton Role
        val radioNode = composeTestRule.onNode(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.RadioButton))
        radioNode.assertIsNotSelected()

        // Perform click and check new state
        radioNode.performClick()
        radioNode.assertIsSelected()
        assertTrue(selectedState)
    }
}
