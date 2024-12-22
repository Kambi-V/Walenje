package kambi.victor.walenje.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kambi.victor.walenje.InitialScreen
import kambi.victor.walenje.feature.welcome.Welcome
import kotlinx.serialization.Serializable

sealed interface Route {
  @Serializable data object Welcome : Route

  @Serializable data object Initial : Route
}

@Serializable data object Welcome

@Serializable data object Initial

@Composable
fun WalenjeNavGraph(navController: NavHostController = rememberNavController()) {
  val currentBackStackEntry by navController.currentBackStackEntryAsState()
  //  val currentRoute = currentBackStackEntry?.destination?.route ?: startDestination
  NavHost(navController = navController, startDestination = Welcome) {
    composable<Welcome> { Welcome(onNavigateToInitial = { navController.navigate(Initial) }) }
    composable<Initial> { InitialScreen(onNavigateToWelcome = { navController.navigate(Welcome) }) }
  }
}
