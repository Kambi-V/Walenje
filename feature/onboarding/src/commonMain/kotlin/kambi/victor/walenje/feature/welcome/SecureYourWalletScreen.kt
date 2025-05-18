package kambi.victor.walenje.feature.welcome

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kambi.victor.walenje.core.designsystem.WalenjeText
import kambi.victor.walenje.core.designsystem.icons.WalenjeIcons
import kotlin.math.abs
import kotlinx.coroutines.launch

@Composable
fun SecureYourWalletScreen(onNavigateToNext: () -> Unit) {
  val coroutineScope = rememberCoroutineScope()

  val translationY = remember { Animatable(0f) }
  var columnHeight by remember { mutableFloatStateOf(0f) }
  var totalDrag by remember { mutableFloatStateOf(0f) }

  val draggableState = rememberDraggableState { dragAmount ->
    coroutineScope.launch {
      totalDrag += dragAmount
      translationY.snapTo(translationY.value + dragAmount)
    }
  }

  val decay = rememberSplineBasedDecay<Float>()

  Scaffold { paddingValues ->
    Column(
      modifier =
        Modifier.onGloballyPositioned { coordinates ->
            columnHeight = coordinates.size.height.toFloat()
          }
          .graphicsLayer { this.translationY = translationY.value }
          .draggable(
            draggableState,
            Orientation.Vertical,
            onDragStopped = { velocity ->
              coroutineScope.launch {
                val decayTarget = decay.calculateTargetValue(translationY.value, velocity)
                val dragDistance = abs(totalDrag)
                val isFlickUp = velocity < 0 && decayTarget < -columnHeight * 0.5f
                val isSlowDragUp = totalDrag < 0 && dragDistance > columnHeight * 0.3f

                if (isFlickUp || isSlowDragUp) {
                  onNavigateToNext()
                  translationY.animateDecay(initialVelocity = velocity, animationSpec = decay)
                } else {
                  translationY.animateTo(0f)
                }

                totalDrag = 0f // reset
              }
            },
          )
          .fillMaxSize()
          .padding(paddingValues)
          .padding(start = 16.dp, top = 64.dp, end = 16.dp, bottom = 40.dp)
    ) {
      Column {
        ProvideTextStyle(
          MaterialTheme.typography.displayLarge.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 68.sp,
          )
        ) {
          Text(
            text =
              buildAnnotatedString {
                append("SECURE\n")
                append("YOUR\n")
                append("WALLET\n")
              }
          )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
          ProvideTextStyle(
            MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
          ) {
            Text(text = "We need to be sure that it’s you that’s accessing your wallet")
          }
        }
      }
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Button(
          onClick = { onNavigateToNext() },
          modifier = Modifier.fillMaxWidth(),
          shape = MaterialTheme.shapes.medium,
        ) {
          Icon(WalenjeIcons.Lock, contentDescription = null)
          Spacer(Modifier.padding(4.dp))
          WalenjeText("Proceed to secure Wallet", fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}

@Composable
fun SecureYourWalletScreen2(onNavigateBack: () -> Unit, onNavigateToNext: () -> Unit) {
  val coroutineScope = rememberCoroutineScope()

  val translationY = remember { Animatable(0f) }
  var columnHeight by remember { mutableFloatStateOf(0f) }
  var totalDrag by remember { mutableFloatStateOf(0f) }

  val draggableState = rememberDraggableState { dragAmount ->
    coroutineScope.launch {
      totalDrag += dragAmount
      translationY.snapTo(translationY.value + dragAmount)
    }
  }

  val decay = rememberSplineBasedDecay<Float>()

  Scaffold { paddingValues ->
    Column(
      modifier =
        Modifier.onGloballyPositioned { coordinates ->
            columnHeight = coordinates.size.height.toFloat()
          }
          .graphicsLayer { this.translationY = translationY.value }
          .draggable(
            draggableState,
            Orientation.Vertical,
            onDragStopped = { velocity ->
              coroutineScope.launch {
                val decayTarget = decay.calculateTargetValue(translationY.value, velocity)
                val dragDistance = abs(totalDrag)
                val isFlickUp = velocity < 0 && decayTarget < -columnHeight * 0.5f
                val isSlowDragUp = totalDrag < 0 && dragDistance > columnHeight * 0.3f

                if (isFlickUp || isSlowDragUp) {
                  onNavigateToNext()
                  translationY.animateDecay(initialVelocity = velocity, animationSpec = decay)
                } else {
                  translationY.animateTo(0f)
                }

                totalDrag = 0f // reset
              }
            },
          )
          .fillMaxSize()
          .padding(paddingValues)
          .padding(vertical = 64.dp)
    ) {
      Column(modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp, bottom = 64.dp)) {
        ProvideTextStyle(
          MaterialTheme.typography.displayLarge.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 68.sp,
          )
        ) {
          Text(
            text =
              buildAnnotatedString {
                append("SECURE\n")
                append("YOUR\n")
                append("WALLET\n")
              }
          )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
          ProvideTextStyle(
            MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
          ) {
            Text(text = "We need to be sure that it’s you that’s accessing your wallet")
          }
        }
      }

      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        SwipeUpChevronAnimation()
        WalenjeText("Swipe up to start")
      }
    }
  }
}

@Composable
fun SwipeUpChevronAnimation() {
  val infiniteTransition = rememberInfiniteTransition(label = "translate")
  val translate =
    infiniteTransition.animateFloat(
      initialValue = 50f,
      targetValue = -300f,
      animationSpec =
        infiniteRepeatable(
          animation = tween(durationMillis = 3000),
          repeatMode = RepeatMode.Restart,
        ),
      label = "arrow",
    )

  val alpha =
    infiniteTransition.animateFloat(
      initialValue = 0.2f,
      targetValue = 0f, // will go back to 0 via peak at center
      animationSpec =
        infiniteRepeatable(
          animation =
            keyframes {
              durationMillis = 3000
              0.2f at 0 // start
              1f at 1500 // middle
              0f at 3000 // end
            },
          repeatMode = RepeatMode.Restart,
        ),
      label = "alpha",
    )

  Box(
    Modifier.graphicsLayer {
      translationY = translate.value
      this.alpha = alpha.value
    }
  ) {
    Icon(imageVector = WalenjeIcons.ChevronUp, contentDescription = null)
  }
}
