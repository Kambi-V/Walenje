plugins {
  id("walenje.android-library")
  id("walenje.koin")
}

android { namespace = "kambi.victor.walenje.core.ui" }

kotlin { sourceSets { commonMain.dependencies { api(projects.core.designsystem) } } }
