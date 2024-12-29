package kambi.victor.walenje.core.authentication

import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class BiometricPromptManager(private val activity: FragmentActivity) : Biometrics {
  private val executor = ContextCompat.getMainExecutor(activity)
  private lateinit var biometricPrompt: BiometricPrompt
  private lateinit var promptInfo: PromptInfo

  fun showBiometricPrompt(title: String, description: String): PromptInfo {
    val authenticators = BIOMETRIC_STRONG

    promptInfo =
      PromptInfo.Builder()
        .setTitle(title)
        .setDescription(description)
        .setNegativeButtonText("Cancel")
        .setAllowedAuthenticators(authenticators)
        .build()
    logger.i { "Prompt is built" }
    return promptInfo
  }

  override suspend fun authenticate(): AuthenticationResult = suspendCoroutine { continuation ->
    logger.i { "In authentication" }
    showBiometricPrompt("Walenje App", "")
    biometricPrompt =
      BiometricPrompt(
        activity,
        executor,
        object : AuthenticationCallback() {
          override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            continuation.resume(AuthenticationResult.Success)
          }

          override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            continuation.resume(AuthenticationResult.Failure)
          }

          override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            continuation.resume(AuthenticationResult.Error(errString.toString()))
          }
        },
      )
    biometricPrompt.authenticate(showBiometricPrompt("Walenje App", ""))
  }
}
