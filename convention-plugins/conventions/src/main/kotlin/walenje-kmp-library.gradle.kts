import org.gradle.accessors.dm.LibrariesForLibs

plugins {
  com.android.kotlin.multiplatform.library
  id("walenje-cmp-base")
  id("walenje-format-code")
}

val libs = the<LibrariesForLibs>()

kotlin {
  jvm()
  iosArm64()
  iosSimulatorArm64()
  androidLibrary {
    compileSdk = libs.versions.compileSdk.get().toInt()
    minSdk = libs.versions.minSdk.get().toInt()
    androidResources.enable = true
  }
}
