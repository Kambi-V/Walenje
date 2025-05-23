import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
  id("walenje-kmp")
  `kotlin-composecompiler`
  org.jetbrains.compose
}

kotlin {
  sourceSets {
    androidMain.dependencies { implementation(compose.preview) }
    commonMain.dependencies {
      implementation(compose.components.uiToolingPreview)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.runtime)
      implementation(compose.ui)
      implementation(libs.compose.lifecycle)
      implementation(libs.androidx.lifecycle.runtime.compose)
      implementation(libs.androidx.lifecycle.viewmodel)
    }
  }
}
