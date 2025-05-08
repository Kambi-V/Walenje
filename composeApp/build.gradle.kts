import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("walenje.android-application")
  id("walenje.compose-multiplatform")
  id("walenje.kotlin-multiplatform")
  id("walenje.desktop-application")
  id("walenje.kotlinx")
  id("walenje.koin")
  id("walenje.logger")
  alias(libs.plugins.composeHotReload)
}

kotlin {
  androidTarget { compilerOptions { jvmTarget.set(JvmTarget.JVM_21) } }

  sourceSets {
    commonMain.dependencies {
      implementation(projects.core.designsystem)
      implementation(projects.core.authentication)
      // Navigation
      implementation(libs.compose.navigation)
      // access features
      implementation(projects.feature.onboarding)
      implementation(projects.feature.home)
      implementation(projects.feature.analytics)
      implementation(projects.feature.profile)
      // Biometrics
      implementation(libs.biometric)
    }
  }
}

android {
  namespace = "kambi.victor.walenje"

  defaultConfig {
    applicationId = "kambi.victor.walenje"
    versionCode = 1
    versionName = "1.0"
  }
}

compose.desktop {
  application {
    mainClass = "kambi.victor.walenje.MainKt"

    nativeDistributions {
      packageName = "kambi.victor.walenje"
      packageVersion = "1.0.0"
    }
  }
}
