plugins {
  id("walenje-application-feature")
  id("walenje-koin")
  id("walenje-logger")
}

kotlin {
  androidLibrary {
    namespace = "kambi.victor.walenje.feature.home"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    minSdk = libs.versions.android.minSdk.get().toInt()
  }

  sourceSets {
    commonMain.dependencies {
      api(projects.core.ui)
      api(projects.core.utils)
      api(projects.core.authentication)
    }
  }
}
