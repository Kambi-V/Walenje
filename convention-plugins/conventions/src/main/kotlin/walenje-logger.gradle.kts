import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins { id("walenje-kmp") }

javaToolchains { version = JavaVersion.VERSION_21 }

kotlin { sourceSets { commonMain { dependencies { api(libs.kotlin.logging) } } } }
