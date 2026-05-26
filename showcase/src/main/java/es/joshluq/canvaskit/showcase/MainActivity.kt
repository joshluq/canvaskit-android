package es.joshluq.canvaskit.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import es.joshluq.canvaskit.showcase.ui.screens.ButtonsScreen
import es.joshluq.canvaskit.showcase.ui.screens.DialogsScreen
import es.joshluq.canvaskit.showcase.ui.screens.HomeScreen
import es.joshluq.canvaskit.showcase.ui.screens.PopupsScreen
import es.joshluq.canvaskit.showcase.ui.screens.TextFieldsScreen
import es.joshluq.canvaskit.showcase.ui.theme.ShowcaseTheme
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute : NavKey

@Serializable
object ButtonsRoute : NavKey

@Serializable
object TextFieldsRoute : NavKey

@Serializable
object DialogsRoute : NavKey

@Serializable
object PopupsRoute : NavKey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowcaseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = CanvasKitTheme.colors.backgroundPrimary
                ) { innerPadding ->
                    ShowcaseAppNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ShowcaseAppNavigation(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(HomeRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        modifier = modifier,
        entryProvider = entryProvider {
            entry<HomeRoute> {
                HomeScreen(
                    onNavigateToButtons = { backStack.add(ButtonsRoute) },
                    onNavigateToTextFields = { backStack.add(TextFieldsRoute) },
                    onNavigateToDialogs = { backStack.add(DialogsRoute) },
                    onNavigateToPopups = { backStack.add(PopupsRoute) }
                )
            }
            entry<ButtonsRoute> {
                ButtonsScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<TextFieldsRoute> {
                TextFieldsScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<DialogsRoute> {
                DialogsScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<PopupsRoute> {
                PopupsScreen(onBack = { backStack.removeLastOrNull() })
            }
        }
    )
}
