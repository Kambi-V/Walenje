plugins {
  id("walenje-cmp-base")
  id("walenje-kmp-library")
  id("walenje-logger")
}

kotlin {
  androidLibrary {
    namespace = "kambi.victor.walenje.core.authentication"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    minSdk = libs.versions.android.minSdk.get().toInt()
  }
  sourceSets { androidMain.dependencies { implementation(libs.biometric) } }
}
