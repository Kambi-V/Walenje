plugins {
  id("walenje-kmp-library")
  id("walenje-koin")
  id("walenje-logger")
}

kotlin {
  androidLibrary {
    namespace = "kambi.victor.walenje.core.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    minSdk = libs.versions.android.minSdk.get().toInt()
  }

  sourceSets { commonMain.dependencies { api(projects.core.designsystem) } }
}
