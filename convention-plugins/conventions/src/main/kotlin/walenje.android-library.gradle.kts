import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `android-library`
  `kotlin-composecompiler`
  id("walenje.compose-multiplatform")
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