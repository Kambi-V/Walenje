import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("walenje.android-application")
    id("walenje.compose-multiplatform")
    id("walenje.kotlin-multiplatform")
    id("walenje.desktop-application")
    id("walenje.kotlin")
//    alias(libs.plugins.walenjeAndroidApplication)
//    alias(libs.plugins.walenjeComposeMultiplatform)
//    alias(libs.plugins.walenjeKotlinMultiplatform)
//    alias(libs.plugins.walenjeDesktopApplication)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    sourceSets {
        val desktopMain by getting
        androidMain.dependencies {  }
        commonMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
        desktopMain.dependencies {  }
    }
}

android {
    namespace = "kambi.victor.walenje"

    defaultConfig {
        applicationId = "kambi.victor.walenje"
        versionCode = 1
        versionName = "1.0"
    }
}

compose.desktop {
    application {
        mainClass = "kambi.victor.walenje.MainKt"

        nativeDistributions {
            packageName = "kambi.victor.walenje"
            packageVersion = "1.0.0"
        }
    }
}
