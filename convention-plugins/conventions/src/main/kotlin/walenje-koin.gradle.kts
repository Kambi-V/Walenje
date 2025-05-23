import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()
plugins {
    id("walenje-kmp")
}

kotlin {
    sourceSets{
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
        commonMain.dependencies{
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.bundles.koin)
        }
    }
}
