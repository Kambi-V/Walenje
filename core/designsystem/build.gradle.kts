plugins {
    id("walenje-cmp-base")
    id("walenje-kmp-library")
}

kotlin {
    androidLibrary { namespace = "kambi.victor.walenje.core.designsystem" }

    sourceSets { commonMain.dependencies { api(libs.components.resources) } }
}

compose.resources { generateResClass = always }
