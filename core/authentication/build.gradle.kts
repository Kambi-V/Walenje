plugins {
    id("walenje.compose-multiplatform")
    id("walenje.android-library")
    id("walenje.logger")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.biometric)
        }
    }
}