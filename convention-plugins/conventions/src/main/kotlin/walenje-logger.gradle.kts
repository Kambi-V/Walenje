import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins { id("walenje-kmp") }

kotlin {
  sourceSets {
    val commonMain by getting { dependencies { api(libs.kotlin.logging) } }
  }
}
