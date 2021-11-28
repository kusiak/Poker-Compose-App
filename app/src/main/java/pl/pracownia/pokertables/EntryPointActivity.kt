package pl.pracownia.pokertables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.pracownia.pokertables.ui.theme.PokerTablesTheme

class MainActivity : ComponentActivity() {
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

  }
}


@Composable
private fun RingsListDestination(navController: NavHostController) {

}

object NavigationKeys {


  object Route {
    const val RINGS_LIST = "rings_list"
  }

}