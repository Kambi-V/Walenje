plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.androidKmpLibrary) apply false
    id("org.jetbrains.kotlin.android") version "2.3.0" apply false
    alias(libs.plugins.ktlint)
}

dependencies { ktlintRuleset(libs.compose.lints) }
