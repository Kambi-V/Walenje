import org.gradle.accessors.dm.LibrariesForLibs

plugins {
  com.android.kotlin.multiplatform.library
  id("walenje-cmp-base")
  id("walenje-format-code")
}

val libs = the<LibrariesForLibs>()

kotlin {
  jvm()
  androidLibrary {
    experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    minSdk = libs.versions.android.minSdk.get().toInt()
  }
}