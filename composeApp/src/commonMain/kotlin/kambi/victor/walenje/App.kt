package kambi.victor.walenje

import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import kambi.victor.walenje.core.authentication.Biometrics
import kambi.victor.walenje.core.designsystem.theme.WalenjeTheme
import kambi.victor.walenje.navigation.WalenjeNavGraph

@Composable
fun App(biometrics: Biometrics) {
  /* Apply Surface to make the icons visible with edgeToEdge */
  WalenjeTheme { Surface { WalenjeNavGraph(biometrics = biometrics) } }
}
