package kambi.victor.walenje

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kambi.victor.walenje.core.authentication.BiometricPromptManager

val manager = BiometricPromptManager()
private val biometrics by lazy { BiometricPromptManager() }

fun main() = application {
  Window(onCloseRequest = ::exitApplication, title = "WalenjeApp") { App(biometrics) }
}
