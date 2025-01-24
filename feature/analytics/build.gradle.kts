plugins {
  id("walenje.feature")
  id("walenje.koin")
  id("walenje.logger")
  id("walenje.code-formatter")
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      api(projects.core.ui)
      api(projects.core.utils)
      api(projects.core.authentication)
    }
  }
}
