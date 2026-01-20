pluginManagement {
    includeBuild("convention-plugins")

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Walenje"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app:common", ":app:android")

include(":core:authentication", ":core:designsystem", ":core:ui", ":core:utils")

include(":feature:onboarding", ":feature:home", ":feature:analytics", ":feature:profile")
