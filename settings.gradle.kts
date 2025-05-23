rootProject.name = "Walenje"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")



pluginManagement {
  includeBuild("convention-plugins")

  repositories {
    google {
      mavenContent {
        includeGroupAndSubgroups("androidx")
        includeGroupAndSubgroups("com.android")
        includeGroupAndSubgroups("com.google")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositories {
    google {
      mavenContent {
        includeGroupAndSubgroups("androidx")
        includeGroupAndSubgroups("com.android")
        includeGroupAndSubgroups("com.google")
      }
    }
    mavenCentral()
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")

include(":core:authentication", ":core:designsystem", ":core:ui", ":core:utils")

include(":feature:onboarding", ":feature:home", ":feature:analytics", ":feature:profile")