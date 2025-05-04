import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
  id("org.jetbrains.compose")
  id("walenje.kotlin-multiplatform")
  `kotlin-composecompiler`
}

kotlin {
  sourceSets {
    androidMain.dependencies { implementation(compose.preview) }
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.ui)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)
      implementation(libs.androidx.lifecycle.viewmodel)
      implementation(libs.androidx.lifecycle.runtime.compose)
      implementation(libs.compose.lifecycle)
    }
  }
}
