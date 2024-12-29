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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import kambi.victor.walenje.core.designsystem.icons.WalenjeIcons
import kambi.victor.walenje.core.designsystem.medium
import kambi.victor.walenje.core.ui.SetPin
import kambi.victor.walenje.core.utils.mvi.ResourceUiState
import kambi.victor.walenje.feature.welcome.view_models.SetPinScreenViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetPinScreen(
  onNavigateBack: () -> Unit,
  viewModel: SetPinScreenViewModel = koinViewModel(),
  onNavigateToHomeScreen: () -> Unit,
) {
  var pin by remember { mutableStateOf("") }
  var confirmPin by remember { mutableStateOf("") }
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()
  val haptics = LocalHapticFeedback.current

  LaunchedEffect(Unit) {
    viewModel.state.collect {
      pin =
        when (it.pin) {
          is ResourceUiState.Success -> it.pin.data
          else -> ""
        }
      confirmPin =
        when (it.confirmPin) {
          is ResourceUiState.Success -> it.confirmPin.data
          else -> ""
        }
    }
  }
  LaunchedEffect(Unit) {
    viewModel.effect.collect { effect ->
      when (effect) {
        SetPinContract.Effect.NavigateToBackScreen -> onNavigateBack()
        SetPinContract.Effect.NavigateToNextScreen -> onNavigateToHomeScreen()
        SetPinContract.Effect.PinConfigured -> {
          log.i { "The pin is configured" }
        }
        SetPinContract.Effect.PinMatched -> onNavigateToHomeScreen()
        SetPinContract.Effect.PinMismatched -> {
          log.i { "The pins mismatched" }
          scope.launch {
            snackbarHostState.showSnackbar(message = "Try entering a new Pin")
            haptics.medium()
          }
        }
        SetPinContract.Effect.ConfirmPinConfigured -> {
          log.i { "The confirm pin is configured" }
        }
      }
    }
  }

  Scaffold(
    snackbarHost = { SnackbarHost(snackbarHostState) },
    topBar = {
      TopAppBar(
        title = {},
        modifier = Modifier.padding(horizontal = 8.dp),
        navigationIcon = {
          IconButton(
            onClick = { scope.launch { viewModel.setEvent(SetPinContract.Event.NavigateBack) } },
            modifier = Modifier.clip(CircleShape).size(40.dp),
            colors =
              IconButtonDefaults.iconButtonColors()
                .copy(containerColor = MaterialTheme.colorScheme.surfaceVariant),
          ) {
            Icon(imageVector = WalenjeIcons.ArrowLeft, contentDescription = null)
          }
        },
      )
    },
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
        Text("Pin: $pin")
        Text("Confirm Pin: $confirmPin")
      }
      if (pin.isNotEmpty()) {
        SetPin(
          title = "Confirm new Pin",
          onSuccess = { pin ->
            log.i { "In Screen >>>>>>>>>>>>>>> $pin" }
            scope.launch { viewModel.setEvent(SetPinContract.Event.SetConfirmPin(pin)) }
          },
        )
      } else {
        SetPin(
          onSuccess = { pin ->
            log.i { "In Screen >>>>>>>>>>>>>>> $pin" }
            scope.launch { viewModel.setEvent(SetPinContract.Event.SetPin(pin)) }
          }
        )
      }
    }
  }
}
