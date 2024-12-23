import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()
plugins {
    id("walenje.kotlin-multiplatform")
}

kotlin {
    sourceSets{
        commonMain.dependencies{
            implementation(libs.kotlin.logging)
        }
    }
}

