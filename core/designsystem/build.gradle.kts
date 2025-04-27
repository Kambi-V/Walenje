plugins { id("walenje.android-library") }

android { namespace = "kambi.victor.walenje.core.designsystem" }

kotlin {
  sourceSets {
    commonMain.dependencies {
      compileOnly(compose.components.resources)
      implementation(compose.materialIconsExtended)
    }
  }
}

compose.resources { generateResClass = auto }
