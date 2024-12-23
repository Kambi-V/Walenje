package kambi.victor.walenje.feature.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kambi.victor.walenje.core.designsystem.icons.WalenjeIcons
import kambi.victor.walenje.core.ui.SetPin
import kambi.victor.walenje.feature.welcome.view_models.SetPinScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetPinScreen(
  onNavigateBack: () -> Unit,
  viewModel: SetPinScreenViewModel = koinViewModel(),
  onNavigateToHomeScreen: () -> Unit,
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {},
        modifier = Modifier.padding(horizontal = 8.dp),
        navigationIcon = {
          IconButton(
            onClick = { onNavigateBack() },
            modifier = Modifier.clip(CircleShape).size(40.dp),
            colors =
              IconButtonDefaults.iconButtonColors()
                .copy(containerColor = MaterialTheme.colorScheme.surfaceVariant),
          ) {
            Icon(imageVector = WalenjeIcons.ArrowLeft, contentDescription = null)
          }
        },
      )
    }
  ) { paddingValues ->
    Column(
      modifier =
        Modifier.fillMaxSize().padding(paddingValues).padding(top = 128.dp, bottom = 32.dp),
      verticalArrangement = Arrangement.Bottom,
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
      ) {
        Text("Pin: ${viewModel.pin.collectAsState().value}")
        Text("Confirm Pin: ${viewModel.confirmPin.collectAsState().value}")
      }
      if (viewModel.pin.collectAsState().value.length == 6) {
        SetPin(
          title = "Confirm new Pin",
          onSuccess = { pin ->
            logger.info { "In Screen >>>>>>>>>>>>>>> $pin" }
            viewModel.setConfirmPin(pin)
            if (viewModel.confirmPin()) { onNavigateToHomeScreen()}
          },
        )
      } else {
        SetPin(
          onSuccess = { pin ->
            logger.info { "In Screen >>>>>>>>>>>>>>> $pin" }
            viewModel.setPin(pin)
          }
        )
      }
    }
  }
}
