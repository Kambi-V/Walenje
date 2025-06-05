package kambi.victor.walenje.feature.welcome

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
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
  val haptics = LocalHapticFeedback.current

  var pin by remember { mutableStateOf("") }
  var confirmPin by remember { mutableStateOf("") }
  var isConfirmPin by remember { mutableStateOf(false) }
  var isPinMismatch by remember { mutableStateOf(false) }

  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  LaunchedEffect(pin, confirmPin) {
    if (pin.length == 4) {
      viewModel.setEvent(SetPinContract.Event.SetPin(pin))
      delay(300) // this is to show the animation
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
          logger.i { "The pin is configured" }
        }
        SetPinContract.Effect.ConfirmPinConfigured -> {
          //          log.i { "The confirm pin is configured" }
        }
        SetPinContract.Effect.PinMatched -> {
          delay(400) // animate before transitioning to the homescreen
          haptics.confirm()
          onNavigateToHomeScreen()
        }
        SetPinContract.Effect.PinMismatched -> {
          isPinMismatch = true
          //          log.i { "The pins mismatched" }
          haptics.reject()
          scope.launch { snackbarHostState.showSnackbar(message = "Try entering a new Pin") }
          pin = ""
          confirmPin = ""
          isConfirmPin = false
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

        val pinMatched by derivedStateOf { pin == confirmPin && confirmPin.length == 4 }

        Box(
          modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
          contentAlignment = Alignment.Center,
        ) {
          PinVisualization(
            pinInput = if (isConfirmPin) confirmPin else pin,
            triggerSuccess = isConfirmPin && pinMatched,
            showError = isPinMismatch,
          )
        }
      }
      NumberPad { input ->
        if (isPinMismatch) isPinMismatch = false
        var targetInput = if (isConfirmPin) confirmPin else pin
        val updatedPin =
          when (input) {
            "del" -> targetInput.dropLast(1)
            else -> (targetInput + input).takeIf { it.length <= 4 } ?: targetInput
          }

        if (isConfirmPin) {
          confirmPin = updatedPin
        } else {
          pin = updatedPin
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
fun PinVisualization(
  pins: Int = 4,
  pinInput: String = "",
  triggerSuccess: Boolean = false,
  showError: Boolean = false,
) {
  val successStates = remember { List(pins) { mutableStateOf(false) } }

  val shake = remember { Animatable(0f) }

  LaunchedEffect(triggerSuccess) {
    if (triggerSuccess) {
      successStates.forEachIndexed { _, state ->
        state.value = true
        delay(100)
      }
    }
  }

  LaunchedEffect(showError) {
    if (showError) {
      shake.snapTo(0f)
      shake.animateTo(
        targetValue = 0f,
        animationSpec =
          keyframes {
            durationMillis = 500
            val intensity = 16f
            0f at 0
            -intensity at 50
            intensity at 100
            -intensity at 150
            intensity at 200
            -intensity / 2 at 250
            intensity / 2 at 300
            -intensity / 4 at 350
            intensity / 4 at 400
            0f at 500
          },
      )
    }
  }

  Row(
    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    modifier = Modifier.graphicsLayer { translationX = shake.value },
  ) {
    for (index in 0 until pins) {
      val filled = index < pinInput.length
      PinBubble(
        filled = filled,
        success = successStates[index].value,
        failure = showError,
        animate = triggerSuccess,
      )
    }
  }
}

@Composable
fun PinBubble(filled: Boolean, success: Boolean, failure: Boolean, animate: Boolean) {
  val scale = remember { Animatable(1f) }
  LaunchedEffect(success) {
    if (success && animate) {
      scale.snapTo(1f)
      scale.animateTo(
        targetValue = 1.2f,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
      )
      scale.animateTo(1f, animationSpec = tween(150))
    }
  }

  val targetIcon =
    when {
      success -> WalenjeIcons.Circle // ✅ Green filled circle
      filled -> WalenjeIcons.Star // ✅ Input progress
      else -> WalenjeIcons.CircleOutline // Default state
    }

  val tintColor =
    when {
      success -> MaterialTheme.colorScheme.secondaryContainer
      filled -> MaterialTheme.colorScheme.onSurface
      failure -> MaterialTheme.colorScheme.error
      else -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
    }

  AnimatedContent(
    targetState = targetIcon,
    transitionSpec = {
      slideInVertically { it } + fadeIn() togetherWith slideOutVertically { -it } + fadeOut()
    },
    contentAlignment = Alignment.Center,
  ) { icon ->
    Icon(
      imageVector = icon,
      contentDescription = null,
      modifier = Modifier.size(24.dp).scale(scale.value),
      tint = tintColor,
    )
  }
}
