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
import es.joshluq.canvaskit.showcase.ui.screens.CardsScreen
import es.joshluq.canvaskit.showcase.ui.screens.DialogsScreen
import es.joshluq.canvaskit.showcase.ui.screens.HomeScreen
import es.joshluq.canvaskit.showcase.ui.screens.PopupsScreen
import es.joshluq.canvaskit.showcase.ui.screens.TextFieldsScreen
import es.joshluq.canvaskit.showcase.ui.screens.BottomBarScreen
import es.joshluq.canvaskit.showcase.ui.screens.TopBarScreen
import es.joshluq.canvaskit.showcase.ui.screens.TogglesScreen
import es.joshluq.canvaskit.showcase.ui.screens.BannersScreen
import es.joshluq.canvaskit.showcase.ui.screens.ChipsScreen
import es.joshluq.canvaskit.showcase.ui.screens.SkeletonsScreen
import es.joshluq.canvaskit.showcase.ui.screens.LoadingScaffoldScreen
import es.joshluq.canvaskit.showcase.ui.screens.ListsScreen
import es.joshluq.canvaskit.showcase.ui.screens.SheetsScreen
import es.joshluq.canvaskit.showcase.ui.screens.TextLinksScreen
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

@Serializable
object CardsRoute : NavKey

@Serializable
object TopBarRoute : NavKey

@Serializable
object BottomBarRoute : NavKey

@Serializable
object TogglesRoute : NavKey

@Serializable
object BannersRoute : NavKey

@Serializable
object ChipsRoute : NavKey

@Serializable
object SkeletonsRoute : NavKey

@Serializable
object LoadingScaffoldRoute : NavKey

@Serializable
object ListsRoute : NavKey

@Serializable
object SheetsRoute : NavKey

@Serializable
object TextLinksRoute : NavKey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowcaseTheme {
                ShowcaseAppNavigation()
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
                    onNavigateToPopups = { backStack.add(PopupsRoute) },
                    onNavigateToCards = { backStack.add(CardsRoute) },
                    onNavigateToTopBar = { backStack.add(TopBarRoute) },
                    onNavigateToBottomBar = { backStack.add(BottomBarRoute) },
                    onNavigateToToggles = { backStack.add(TogglesRoute) },
                    onNavigateToBanners = { backStack.add(BannersRoute) },
                    onNavigateToChips = { backStack.add(ChipsRoute) },
                    onNavigateToSkeletons = { backStack.add(SkeletonsRoute) },
                    onNavigateToLoadingScaffold = { backStack.add(LoadingScaffoldRoute) },
                    onNavigateToLists = { backStack.add(ListsRoute) },
                    onNavigateToSheets = { backStack.add(SheetsRoute) },
                    onNavigateToTextLinks = { backStack.add(TextLinksRoute) }
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
            entry<CardsRoute> {
                CardsScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<TopBarRoute> {
                TopBarScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<BottomBarRoute> {
                BottomBarScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<TogglesRoute> {
                TogglesScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<BannersRoute> {
                BannersScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<ChipsRoute> {
                ChipsScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<SkeletonsRoute> {
                SkeletonsScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<LoadingScaffoldRoute> {
                LoadingScaffoldScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<ListsRoute> {
                ListsScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<SheetsRoute> {
                SheetsScreen(onBack = { backStack.removeLastOrNull() })
            }
            entry<TextLinksRoute> {
                TextLinksScreen(onBack = { backStack.removeLastOrNull() })
            }
        }
    )
}
