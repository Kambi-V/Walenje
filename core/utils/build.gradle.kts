plugins {
  id("walenje-kmp-library")
  id("walenje-kotlinx")
  id("walenje-logger")
}

kotlin {
  androidLibrary {
    namespace = "kambi.victor.walenje.core.utils"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    minSdk = libs.versions.android.minSdk.get().toInt()
  }
}
