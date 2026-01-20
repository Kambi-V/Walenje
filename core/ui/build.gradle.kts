plugins {
    id("walenje-kmp-library")
    id("walenje-koin")
    id("walenje-logger")
}

kotlin {
    androidLibrary {
        namespace = "kambi.victor.walenje.core.ui"
    }

    sourceSets { commonMain.dependencies { api(projects.core.designsystem) } }
}
