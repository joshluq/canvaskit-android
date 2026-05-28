package es.joshluq.canvaskit.components.feedback

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.compose.ui.test.hasStateDescription

@RunWith(AndroidJUnit4::class)
class CanvasKitSkeletonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun skeleton_rendersAndExposesSemantics() {
        composeTestRule.setContent {
            CanvasKitTheme {
                CanvasKitSkeleton(
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        // Verify that the skeleton is in the semantic tree with the "Cargando" state description
        composeTestRule.onNode(hasStateDescription("Cargando"))
            .assertIsDisplayed()
    }
}
