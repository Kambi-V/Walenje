package kambi.victor.walenje.feature.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kambi.victor.walenje.core.designsystem.NavItem
import kambi.victor.walenje.core.designsystem.WalenjeNavigationBar
import kambi.victor.walenje.core.designsystem.icons.WalenjeIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateBack: () -> Unit) {
  val navigationItems =
    mapOf(
      1 to NavItem("Home", WalenjeIcons.Home),
      2 to NavItem("Analytics", WalenjeIcons.Analytics),
      3 to NavItem("Profile", WalenjeIcons.Profile),
    )

  Scaffold(
    topBar = { TopAppBar(title = { Text("Home Screen") }) },
    bottomBar = { WalenjeNavigationBar(navigationItems, 1) },
  ) { innerPadding ->
    LazyColumn(modifier = Modifier.padding(innerPadding)) {
      item {}
      item {}
    }
  }
}
