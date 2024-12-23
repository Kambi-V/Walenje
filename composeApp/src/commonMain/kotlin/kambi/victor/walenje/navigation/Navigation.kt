package kambi.victor.walenje.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kambi.victor.walenje.InitialScreen
import kambi.victor.walenje.feature.welcome.SecureWalletScreen
import kambi.victor.walenje.feature.welcome.SetPinScreen
import kambi.victor.walenje.feature.welcome.Welcome
import kotlinx.serialization.Serializable

sealed interface Route {
  @Serializable data object Welcome : Route

  @Serializable data object Initial : Route

  @Serializable data object SecureWallet : Route

  @Serializable data object SetPin : Route
}

@Composable
fun WalenjeNavGraph(navController: NavHostController = rememberNavController()) {
  NavHost(navController = navController, startDestination = Route.Welcome) {
    composable<Route.Welcome> {
      Welcome(onNavigateToSecureWallet = { navController.navigate(Route.SecureWallet) })
    }
    composable<Route.Initial> {
      InitialScreen(onNavigateToWelcome = { navController.popBackStack() })
    }
    composable<Route.SecureWallet> {
      SecureWalletScreen(
        onNavigateBack = { navController.popBackStack() },
        onNavigateToSetPin = { navController.navigate(Route.SetPin) },
      )
    }
    composable<Route.SetPin> {
      SetPinScreen(
        onNavigateBack = { navController.popBackStack() },
        onNavigateToHomeScreen = { navController.popBackStack(Route.Welcome, false) },
      )
    }
  }
}
