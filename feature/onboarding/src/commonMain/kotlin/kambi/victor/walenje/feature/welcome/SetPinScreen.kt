package kambi.victor.walenje.feature.welcome

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import kambi.victor.walenje.core.designsystem.TextType
import kambi.victor.walenje.core.designsystem.WalenjeText
import kambi.victor.walenje.core.designsystem.confirm
import kambi.victor.walenje.core.designsystem.icons.WalenjeIcons
import kambi.victor.walenje.core.designsystem.reject
import kambi.victor.walenje.core.ui.NumberPad
import kambi.victor.walenje.feature.welcome.PinState.Indeterminate
import kambi.victor.walenje.feature.welcome.PinState.Input
import kambi.victor.walenje.feature.welcome.PinState.Success
import kambi.victor.walenje.feature.welcome.view_models.SetPinScreenViewModel
import kotlinx.coroutines.delay
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
  var isConfirmPin by remember { mutableStateOf(false) }
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()
  val haptics = LocalHapticFeedback.current

  LaunchedEffect(pin, confirmPin) {
    if (pin.length == 4) {
      viewModel.setEvent(SetPinContract.Event.SetPin(pin))
      delay(500) // this is to show the animation
      isConfirmPin = true
    }
    if (confirmPin.length == 4) {
      viewModel.setEvent(SetPinContract.Event.SetConfirmPin(confirmPin))
    }
  }

  LaunchedEffect(Unit) {
    viewModel.effect.collect { effect ->
      when (effect) {
        SetPinContract.Effect.NavigateToBackScreen -> onNavigateBack()
        SetPinContract.Effect.NavigateToNextScreen -> {
          onNavigateToHomeScreen()
        }
        SetPinContract.Effect.PinConfigured -> {
          log.i { "The pin is configured" }
        }
        SetPinContract.Effect.PinMatched -> {
          //          delay(2000) // to show the pin success animation
          haptics.confirm()
          onNavigateToHomeScreen()
        }
        SetPinContract.Effect.PinMismatched -> {
          log.i { "The pins mismatched" }
          haptics.reject()
          scope.launch { snackbarHostState.showSnackbar(message = "Try entering a new Pin") }
          pin = ""
          confirmPin = ""
          isConfirmPin = false
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
      modifier = Modifier.fillMaxSize().padding(paddingValues).padding(bottom = 32.dp),
      verticalArrangement = Arrangement.SpaceBetween,
    ) {
      Column {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
          Icon(
            imageVector = WalenjeIcons.LockKey,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
          )
        }
        PinScreenTitle(isConfirmPin = isConfirmPin)
        Box(
          modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
          contentAlignment = Alignment.Center,
        ) {
          if (isConfirmPin) {
            PinVisualization(pinInput = confirmPin, valid = pin == confirmPin)
          } else {
            PinVisualization(pinInput = pin)
          }
        }
      }
      NumberPad { input ->
        log.i { input }
        if (input == "del") {
          if (isConfirmPin) confirmPin = confirmPin.dropLast(1) else pin = pin.dropLast(1)
        } else {
          if (isConfirmPin) confirmPin += input else pin += input
        }
      }
    }
  }
}

@Composable
internal fun PinScreenTitle(isConfirmPin: Boolean = false) {
  val confirmPinText = buildAnnotatedString {
    append("If you forget your passcode, you’ll need to")
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append(" logout") }
    append(" or")
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append(" reinstall") }
    append(" the app")
  }
  Column(
    modifier = Modifier.padding(top = 128.dp).padding(horizontal = 32.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    WalenjeText(
      if (isConfirmPin) "Re-enter your PIN" else "Enter your PIN",
      textType = TextType.TitleBase,
      fontWeight = FontWeight.SemiBold,
    )
    Spacer(Modifier.height(8.dp))
    if (!isConfirmPin) {
      WalenjeText(
        "Please enter any 4 digits that you will use to unlock your Walenje app",
        textType = TextType.LabelBase,
        textAlign = TextAlign.Center,
      )
    } else {
      WalenjeText(confirmPinText, textType = TextType.LabelBase, textAlign = TextAlign.Center)
    }
  }
}

@Composable
fun PinVisualization(pins: Int = 4, pinInput: String = "", valid: Boolean = false) {
  Row(horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
    for (index in 0 until pins) {
      val state =
        when {
          index < pinInput.length -> Input
          valid -> Success
          else -> Indeterminate
        }
      Pin(
        event =
          when (state) {
            Input -> PinEvent.ProvideInput
            Indeterminate -> PinEvent.ClearInput
            Success -> PinEvent.ValidInput
          }
      )
    }
  }
}

/**
 * States: Indeterminate, Input, Success
 *
 * Events: provideInput, clearInput, validInput
 *
 * Transitions:
 *
 * Indeterminate + provideInput -> Input
 *
 * Input + clearInput -> Indeterminate
 *
 * Input + validInput -> Success
 *
 * Current PinState: Next PinState:
 */
@Composable
fun Pin(event: PinEvent) {
  var currentState by remember { mutableStateOf(Indeterminate) }
  val iconSize = 20.dp
  LaunchedEffect(event) {
    val nextState = pinFSM(currentState, event)
    if (currentState != nextState) {
      currentState = nextState
    }
  }

  Box(
    modifier = Modifier.size(height = 40.dp, width = 24.dp),
    contentAlignment = Alignment.Center,
  ) {
    AnimatedContent(
      targetState = currentState,
      transitionSpec = {
        when (initialState to targetState) {
          Indeterminate to Input ->
            slideInVertically { it } + fadeIn() togetherWith slideOutVertically { -it } + fadeOut()
          Input to Success -> fadeIn() + scaleIn(initialScale = 1.2f) togetherWith fadeOut()
          Input to Indeterminate ->
            slideInVertically { -it } + fadeIn() togetherWith slideOutVertically { it } + fadeOut()
          else -> fadeIn() togetherWith fadeOut()
        }
      },
    ) { state ->
      val icon =
        when (state) {
          Indeterminate -> {
            WalenjeIcons.CircleOutline
          }
          Input -> {
            WalenjeIcons.Star
          }
          Success -> {
            WalenjeIcons.Circle
          }
        }
      Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(iconSize))
    }
  }
}

fun pinFSM(state: PinState, event: PinEvent): PinState =
  when (state) {
    Indeterminate ->
      when (event) {
        PinEvent.ProvideInput -> Input
        PinEvent.ClearInput -> Indeterminate
        PinEvent.ValidInput -> Indeterminate
      }
    Input ->
      when (event) {
        PinEvent.ProvideInput -> Input
        PinEvent.ClearInput -> Indeterminate
        PinEvent.ValidInput -> Success
      }
    Success -> Indeterminate
  }

enum class PinState {
  Indeterminate,
  Input,
  Success,
}

enum class PinEvent {
  ProvideInput,
  ClearInput,
  ValidInput,
}
