package kambi.victor.walenje.core.designsystem

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(val name: String, val icon: ImageVector)

@Immutable
data class NavigationItem(
  val label: String,
  val icon: ImageVector,
  val selectedIcon: ImageVector,
  val iconLabel: ImageVector,
)

@Composable
fun WalenjeNavigationBar(items: Map<Int, NavItem>, selectedItem: Int) {
  var selectedNavItem by remember { mutableIntStateOf(selectedItem) }
  NavigationBar(containerColor = Color.Transparent) {
    Row(horizontalArrangement = Arrangement.Center) {
      items.forEach { item ->
        NavigationBarItem(
          selected = selectedNavItem == item.key,
          onClick = { selectedNavItem = item.key },
          icon = {
            AnimatedContent(targetState = item) {
              Icon(imageVector = item.value.icon, contentDescription = null)
            }
          },
          label = { Text(item.value.name) },
        )
      }
    }
  }
}
