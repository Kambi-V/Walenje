package kambi.victor.walenje.feature.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Welcome( onNavigateToInitial: ()-> Unit) {
  Scaffold {
    Column(modifier = Modifier.padding(it)) {
      Text("This is the Welcome Screen")
      Button(onClick = { onNavigateToInitial() }) { Text("Navigate to Initial") }
    }
  }
}