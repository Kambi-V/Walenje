rootProject.name = "convention-plugins"

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

gradle.settingsEvaluated {
    if (JavaVersion.current() < JavaVersion.VERSION_21) {
        throw GradleException("This build requires JDK 21. Current JDK is ${JavaVersion.current()}")
    }
}

include("conventions")
