import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("walenje-cmp-base")
  id("walenje-kmp")
  id("walenje-format-code")
  id("walenje-kotlinx")
  id("walenje-koin")
  id("walenje-logger")
  alias(libs.plugins.androidKmpLibrary)
}


kotlin {
  androidLibrary {
    namespace = "kambi.victor.walenje.common"
    compileSdk = libs.versions.compileSdk.get().toInt()
    minSdk = libs.versions.minSdk.get().toInt()
    androidResources.enable = true
    compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
  }

  listOf(iosArm64(), iosSimulatorArm64()).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "common"
      isStatic = true
    }
  }

  jvm { compilerOptions.jvmTarget.set(JvmTarget.JVM_17) }

  sourceSets {
    androidMain.dependencies { implementation(libs.biometric) }

    commonMain.dependencies {
      implementation(projects.core.designsystem)
      implementation(projects.core.authentication)
      implementation(projects.core.ui)

      implementation(libs.compose.navigation)

      implementation(projects.feature.onboarding)
      implementation(projects.feature.home)
      implementation(projects.feature.analytics)
      implementation(projects.feature.profile)
    }

    jvmMain.dependencies {
      implementation(compose.desktop.currentOs)
      implementation(libs.kotlinx.coroutines.swing)
    }
  }
}

compose.desktop {
  application {
    mainClass = "kambi.victor.walenje.MainKt"

    nativeDistributions {
      packageName = "kambi.victor.walenje"
      packageVersion = "1.0.0"
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
    }
  }
}
