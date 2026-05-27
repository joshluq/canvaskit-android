package es.joshluq.canvaskit.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanvasKitBottomBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testBottomBarRendersItemsAndLabels() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitBottomBar {
                    CanvasKitBottomBarItem(
                        selected = true,
                        onClick = {},
                        icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "HomeIcon", tint = tint) },
                        label = { tint -> Text("Inicio", color = tint) }
                    )
                    CanvasKitBottomBarItem(
                        selected = false,
                        onClick = {},
                        icon = { tint -> Icon(imageVector = Icons.Default.Search, contentDescription = "SearchIcon", tint = tint) },
                        label = { tint -> Text("Buscar", color = tint) }
                    )
                }
            }
        }

        // Verify elements are displayed
        composeTestRule.onNodeWithContentDescription("HomeIcon").assertIsDisplayed()
        composeTestRule.onNodeWithText("Inicio").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("SearchIcon").assertIsDisplayed()
        composeTestRule.onNodeWithText("Buscar").assertIsDisplayed()
    }

    @Test
    fun testBottomBarHandlesSelectionChange() {
        var selectedIndex = 0

        composeTestRule.setContent {
            var activeIndex by remember { mutableIntStateOf(0) }
            CanvasKitTheme {
                CanvasKitBottomBar {
                    CanvasKitBottomBarItem(
                        selected = activeIndex == 0,
                        onClick = {
                            activeIndex = 0
                            selectedIndex = 0
                        },
                        icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "HomeIcon", tint = tint) },
                        label = { tint -> Text("Inicio", color = tint) }
                    )
                    CanvasKitBottomBarItem(
                        selected = activeIndex == 1,
                        onClick = {
                            activeIndex = 1
                            selectedIndex = 1
                        },
                        icon = { tint -> Icon(imageVector = Icons.Default.Search, contentDescription = "SearchIcon", tint = tint) },
                        label = { tint -> Text("Buscar", color = tint) }
                    )
                }
            }
        }

        // Verify initial state: Node with text "Inicio" is selected, node with "Buscar" is not
        composeTestRule.onNodeWithText("Inicio").assertIsSelected()
        composeTestRule.onNodeWithText("Buscar").assertIsNotSelected()

        // Perform click on Search tab
        composeTestRule.onNodeWithText("Buscar").performClick()

        // Verify state changed
        composeTestRule.onNodeWithText("Inicio").assertIsNotSelected()
        composeTestRule.onNodeWithText("Buscar").assertIsSelected()
        assertEquals(1, selectedIndex)
    }

    @Test
    fun testBottomBarDisplaysBadges() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitBottomBar {
                    CanvasKitBottomBarItem(
                        selected = true,
                        onClick = {},
                        icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "HomeIcon", tint = tint) },
                        label = { tint -> Text("Inicio", color = tint) },
                        badge = {
                            CanvasKitBadge {
                                Text("5")
                            }
                        }
                    )
                }
            }
        }

        // Verify badge content is displayed
        composeTestRule.onNodeWithText("5").assertIsDisplayed()
    }
}
