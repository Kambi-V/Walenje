plugins {
  id("walenje-cmp-base")
  id("walenje-kmp-library")
}

kotlin {
  androidLibrary { namespace = "kambi.victor.walenje.core.designsystem" }

  sourceSets {
    commonMain.dependencies {
      api(libs.components.resources)
      api(libs.graphics.shapes)
    }
  }
}

compose.resources { generateResClass = always }
