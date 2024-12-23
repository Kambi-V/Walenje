plugins {
  id("walenje.feature")
  id("walenje.koin")
  id("walenje.logger")
  //  id("walenje.desktop-application")
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      api(projects.core.ui)
    }
  }
}
