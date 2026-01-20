package kambi.victor.walenje.core.authentication

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
actual class BiometricPromptManager : Biometrics {
  override suspend fun authenticate(): AuthenticationResult = AuthenticationResult.Success
}
