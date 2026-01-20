package kambi.victor.walenje

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.singleWindowApplication
import kambi.victor.walenje.core.authentication.BiometricPromptManager

private val biometrics by lazy { BiometricPromptManager() }

fun main() = application {
  Window(onCloseRequest = ::exitApplication, title = "WalenjeApp") {
    singleWindowApplication { App(biometrics) }
  }
}
