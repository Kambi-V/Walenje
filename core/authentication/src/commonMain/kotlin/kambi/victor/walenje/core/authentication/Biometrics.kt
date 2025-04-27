package kambi.victor.walenje.core.authentication

interface Biometrics {
  suspend fun authenticate(): AuthenticationResult
}

sealed class AuthenticationResult {
  data object Success : AuthenticationResult()

  data object Failure : AuthenticationResult()

  data object AttemptExhausted : AuthenticationResult()

  data class Error(val message: String) : AuthenticationResult()
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class BiometricPromptManager : Biometrics
