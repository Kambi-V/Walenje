import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
  id("walenje-kmp")
  `kotlinx-serialization`
}

kotlin {
  sourceSets { commonMain.dependencies { implementation(libs.kotlinx.serialization.json) } }
}
