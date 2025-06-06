package kambi.victor.walenje.feature.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kambi.victor.walenje.core.authentication.AuthenticationResult
import kambi.victor.walenje.core.authentication.Biometrics
import kotlinx.coroutines.launch

@Composable
fun SecureWalletScreen(
  onNavigateBack: () -> Unit,
  onNavigateToSetPin: () -> Unit,
  configureBiometrics: () -> Unit,
  onNavigateToNext: () -> Unit,
  biometrics: Biometrics,
) {
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

      HorizontalDivider()
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Button(onClick = { onNavigateToSetPin() }, modifier = Modifier.fillMaxWidth()) {
          Text("Use Pin")
        }
        Text("or")
        Button(
          onClick = {
            scope.launch {
              biometrics.authenticate().let { result ->
                //                log.i { "Biometrics result: $result" }

                when (result) {
                  AuthenticationResult.AttemptExhausted -> {
                    //                    log.i { "Attempts exhausted" }
                  }
                  is AuthenticationResult.Error -> {
                    //                    log.i { result.message }
                  }
                  AuthenticationResult.Failure -> {
                    //                    log.i { "In Ui Authentication failed" }
                  }
                  AuthenticationResult.Success -> {
                    onNavigateToNext()
                  }
                }
              }
            }
          },
          modifier = Modifier.fillMaxWidth(),
        ) {
          Text("Use Biometrics")
        }
      }
    }
  }
}
