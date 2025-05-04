package kambi.victor.walenje.feature.welcome

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kambi.victor.walenje.core.designsystem.WalenjeText
import kambi.victor.walenje.core.designsystem.icons.WalenjeIcons
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SecureYourWalletScreen(onNavigateBack: () -> Unit, onNavigateToNext: () -> Unit) {
  val scope = rememberCoroutineScope()
  Scaffold { paddingValues ->
    Column(
      modifier =
        Modifier.fillMaxSize().padding(paddingValues).padding(vertical = 64.dp, horizontal = 16.dp)
    ) {
      Column(modifier = Modifier.padding(top = 16.dp, bottom = 64.dp)) {
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
      initialValue = 1f,
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
    Icon(imageVector = vectorResource(WalenjeIcons.ChevronUp), contentDescription = null)
  }
}
