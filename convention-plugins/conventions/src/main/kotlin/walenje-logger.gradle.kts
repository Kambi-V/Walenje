import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins { id("walenje-kmp") }

kotlin { sourceSets { commonMain { dependencies { api(libs.kotlin.logging) } } } }
