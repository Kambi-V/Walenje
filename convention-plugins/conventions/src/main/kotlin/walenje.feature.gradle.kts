import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("walenje.android-library")
  id("walenje.compose-multiplatform")
  id("walenje.desktop-application")
}

val libs = the<LibrariesForLibs>()

kotlin {
  androidTarget { compilerOptions { jvmTarget.set(JvmTarget.JVM_21) } }

  sourceSets {
    commonMain.dependencies {
      implementation(project(":core:designsystem"))
      implementation(libs.compose.navigation)
    }
  }
}
