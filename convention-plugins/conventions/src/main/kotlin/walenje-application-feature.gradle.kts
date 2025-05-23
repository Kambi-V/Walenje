import org.gradle.accessors.dm.LibrariesForLibs

plugins {
  id("walenje-kmp-library")
  id("walenje-cmp-base")
  id("walenje-kotlinx")
}

val libs = the<LibrariesForLibs>()

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(project(":core:designsystem"))
      implementation(libs.compose.navigation)
    }
  }
}
