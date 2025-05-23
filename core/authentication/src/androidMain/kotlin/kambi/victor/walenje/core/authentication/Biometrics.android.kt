package kambi.victor.walenje.core.authentication

import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlin.coroutines.resume
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.suspendCancellableCoroutine

actual class BiometricPromptManager(private val activity: FragmentActivity) : Biometrics {
  private val executor = ContextCompat.getMainExecutor(activity)
  private lateinit var biometricPrompt: BiometricPrompt
  private lateinit var promptInfo: PromptInfo

  private fun buildBiometricPrompt(
    title: String = "Walenje App",
    description: String = "",
  ): PromptInfo {
    val authenticators = BIOMETRIC_STRONG

    promptInfo =
      PromptInfo.Builder()
        .setTitle(title)
        .setDescription(description)
        .setNegativeButtonText("Cancel")
        .setAllowedAuthenticators(authenticators)
        .build()
    return promptInfo
  }

  private fun createBiometricPrompt(onResult: (AuthenticationResult) -> Unit): BiometricPrompt {
    return BiometricPrompt(
      activity,
      executor,
      object : AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
          super.onAuthenticationSucceeded(result)
//          logger.i { "Authentication succeeded" }
          onResult(AuthenticationResult.Success)
        }

        override fun onAuthenticationFailed() {
          super.onAuthenticationFailed()
//          logger.i { "Authentication failed" }
          onResult(AuthenticationResult.Failure)
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
          super.onAuthenticationError(errorCode, errString)
//          logger.i { "Authentication error" }
          onResult(AuthenticationResult.Error("$errorCode,$errString"))
        }
      },
    )
  }

  override suspend fun authenticate(): AuthenticationResult = coroutineScope {
    var result: AuthenticationResult
    do {
      result = suspendCancellableCoroutine { continuation ->
//        logger.i { "Starting authentication" }
        biometricPrompt = createBiometricPrompt { authResult -> continuation.resume(authResult) }
        biometricPrompt.authenticate(buildBiometricPrompt())
      }
    } while (result == AuthenticationResult.Failure)
    result
  }
}
