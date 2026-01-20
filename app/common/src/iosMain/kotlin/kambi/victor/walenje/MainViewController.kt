package kambi.victor.walenje

import androidx.compose.ui.window.ComposeUIViewController
import kambi.victor.walenje.core.authentication.BiometricPromptManager

val biometrics = BiometricPromptManager()

@Suppress("ktlint:standard:function-naming")
fun MainViewController() = ComposeUIViewController { App(biometrics) }
