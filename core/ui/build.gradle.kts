plugins {
  id("walenje.android-library")
  id("walenje.koin")
  id("walenje.logger")
}

android { namespace = "kambi.victor.walenje.core.ui" }

kotlin {
  sourceSets {
    commonMain.dependencies {
      api(projects.core.designsystem)
    }
  }
}
