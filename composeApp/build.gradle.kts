import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.android.application")
  id("walenje-cmp-base")
  id("walenje-kmp")
  id("walenje-format-code")
  id("walenje-kotlinx")
  id("walenje-koin")
  id("walenje-logger")
}

composeCompiler { featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups) }

kotlin {
  androidTarget { compilerOptions { jvmTarget.set(JvmTarget.JVM_21) } }
  jvm()

  sourceSets {
    androidMain.dependencies {
      implementation(libs.androidx.activity.compose)
      implementation(libs.biometric)
    }

    commonMain.dependencies {
      implementation(projects.core.designsystem)
      implementation(projects.core.authentication)

      implementation(libs.compose.navigation)

      implementation(projects.feature.onboarding)
      implementation(projects.feature.home)
      implementation(projects.feature.analytics)
      implementation(projects.feature.profile)

      implementation(compose.preview)
    }

    val jvmMain by getting
    jvmMain.dependencies {
      implementation(compose.desktop.currentOs)
      implementation(libs.kotlinx.coroutines.swing)
    }
  }
}

android {
  namespace = "kambi.victor.walenje"

  defaultConfig {
    applicationId = "kambi.victor.walenje"
    versionCode = 1
    versionName = "1.0"

    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
  }
  compileSdk = libs.versions.android.compileSdk.get().toInt()
  //  packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
  buildTypes {
    release { isMinifyEnabled = false }
    debug { isMinifyEnabled = false }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
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
