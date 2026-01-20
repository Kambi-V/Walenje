plugins {
    id("walenje-cmp-base")
    id("walenje-kmp-library")
    id("walenje-logger")
}

kotlin {
    androidLibrary {
        namespace = "kambi.victor.walenje.core.authentication"
    }
    sourceSets { androidMain.dependencies { implementation(libs.biometric) } }
}
