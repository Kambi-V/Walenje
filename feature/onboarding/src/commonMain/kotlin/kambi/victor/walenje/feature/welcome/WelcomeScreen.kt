package kambi.victor.walenje.feature.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kambi.victor.walenje.core.designsystem.TextType
import kambi.victor.walenje.core.designsystem.WalenjeTextStyle
import kambi.victor.walenje.core.designsystem.confirm
import kambi.victor.walenje.core.designsystem.icons.WalenjeIcons

@Composable
fun Welcome(onNavigateToSecureWallet: () -> Unit) {
  val haptic = LocalHapticFeedback.current

  Scaffold(contentWindowInsets = ScaffoldDefaults.contentWindowInsets) { paddingValues ->
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
                append("CREATE\n")
                append("YOUR\n")
                append("PRIVATE\n")
                append("WALLET\n")
              }
          )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
          ProvideTextStyle(
            WalenjeTextStyle(TextType.TextMedium).copy(fontWeight = FontWeight.Medium)
          ) {
            Text(
              text =
                buildAnnotatedString {
                  append("Secure your money \n")
                  append("and keep track of your \n")
                  append("finances\n")
                }
            )
          }
        }
      }

      HorizontalDivider()
      Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Box(
          modifier =
            Modifier.clip(CircleShape)
              .clickable {
                haptic.confirm()
                onNavigateToSecureWallet()
              }
              .background(MaterialTheme.colorScheme.primary)
              .size(70.dp),
          contentAlignment = Alignment.Center,
        ) {
          Icon(imageVector = WalenjeIcons.ArrowChevronRight, contentDescription = null)
        }
      }
    }
  }
}
