import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `android-library`
  `kotlin-composecompiler`
  id("walenje.compose-multiplatform")
  id("walenje.code-formatter")
}

kotlin {
  jvm("desktop")

  androidTarget {
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_21)
    }
  }
  sourceSets {
    androidMain.dependencies {
      implementation(compose.preview)
    }
  }
}

dependencies {
  debugImplementation(compose.uiTooling)
}

configureAndroidLibrary()