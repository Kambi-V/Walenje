import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("walenje-kmp")
    `kotlin-composecompiler`
    org.jetbrains.compose
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.foundation)
//            implementation("org.jetbrains.compose.material3:material3:1.9.0-beta03")
            implementation(libs.material3)
            implementation(libs.runtime)
            implementation(libs.ui)
            implementation(libs.compose.lifecycle)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel)
        }
    }
}
