package pl.pracownia.pokertables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import pl.pracownia.pokertables.NavigationKeys.Arg
import pl.pracownia.pokertables.NavigationKeys.Arg.RING_ID
import pl.pracownia.pokertables.ui.feature.ring_details.RingDetailsScreen
import pl.pracownia.pokertables.ui.feature.ring_details.RingDetailsViewModel
import pl.pracownia.pokertables.ui.feature.rings.RingsContract
import pl.pracownia.pokertables.ui.feature.rings.RingsScreen
import pl.pracownia.pokertables.ui.feature.rings.RingsViewModel
import pl.pracownia.pokertables.ui.theme.PokerTablesTheme

@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PokerTablesTheme {
        PokerApp()
      }
    }
  }
}

@Composable
fun PokerApp() {
  val navController = rememberNavController()
  NavHost(navController, startDestination = NavigationKeys.Route.RINGS_LIST) {
    composable(route = NavigationKeys.Route.RINGS_LIST) {
      RingsListDestination(navController)
    }
    composable(
      route = NavigationKeys.Route.RING_DETAILS,
      arguments = listOf(navArgument(Arg.RING_ID) {
        type = NavType.LongType
      })
    ) {
      RingDetailsDestination()
    }
  }
}

@Composable
private fun RingsListDestination(navController: NavHostController) {
  val viewModel: RingsViewModel = hiltViewModel()
  val state = viewModel.uiState.value
  RingsScreen(
    state = state,
    effectFlow = viewModel.uiEffect,
    onEventSent = { event -> viewModel.setEvent(event) },
    onNavigationRequested = { navigationEffect ->
      if (navigationEffect is RingsContract.Effect.Navigation.ToCategoryDetails) {
        navController.navigate("${NavigationKeys.Route.RINGS_LIST}/${navigationEffect.ringId}")
      }
    })
}

@Composable
private fun RingDetailsDestination() {
  val viewModel: RingDetailsViewModel = hiltViewModel()
  val state = viewModel.uiState.value
  RingDetailsScreen(state)
}


object NavigationKeys {

  object Arg {
    const val RING_ID = "ringCategoryName"
  }

  object Route {
    const val RINGS_LIST = "ring_categories_list"
    const val RING_DETAILS = "$RINGS_LIST/{$RING_ID}"
  }
}